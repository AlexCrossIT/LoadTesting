package com.multitreading.loadtesting.servlets;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.multitreading.loadtesting.contexts.ApplicationContext;
import com.multitreading.loadtesting.factories.BeanFactory;
import com.multitreading.loadtesting.interfaces.ApplicationContextBehavior;
import com.multitreading.loadtesting.interfaces.BeanFactoryBehavior;

public class LoadTestingHttpServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

		ApplicationContextBehavior applicationContextBehavior = new ApplicationContext();
		BeanFactoryBehavior beanFactoryBehavior = new BeanFactory(applicationContextBehavior);
		applicationContextBehavior.setBeanFactory(beanFactoryBehavior);
		
		Annotation[] annotations = new Annotation[0];
		
		Class servletClass = this.getClass();

		Object bean = null;
		try {
			bean = servletClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(bean == null) {
			throw new RuntimeException("Cannot create new instance of " + servletClass + " class.");
		}
		
		bean = applicationContextBehavior.getBean(servletClass, annotations);
			
		Field[] beanFields = bean.getClass().getDeclaredFields();
		Field[] servletFields = servletClass.getDeclaredFields();
		
		for(Field servletField: Arrays.stream(servletFields)
			  .collect(Collectors.toList())) {
				   
				Field beanField = Arrays.stream(beanFields).filter(field-> field.equals(servletField)).findFirst().get();
   
				servletField.setAccessible(true);
				beanField.setAccessible(true);
   				 				
   				try {
   					servletField.set(this, beanField.get(bean));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
   					
	   		};
		
    }
	
}
