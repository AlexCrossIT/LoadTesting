package com.multitreading.loadtesting.factories;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.config.JavaConfiguration;
import com.multitreading.loadtesting.configurator.JavaBeanConfigurator;
import com.multitreading.loadtesting.interfaces.ApplicationContextBehavior;
import com.multitreading.loadtesting.interfaces.BeanConfiguratorBehavior;
import com.multitreading.loadtesting.interfaces.BeanFactoryBehavior;
import com.multitreading.loadtesting.interfaces.ConfigurationBehavior;

public class BeanFactory implements BeanFactoryBehavior{
	
	private final BeanConfiguratorBehavior beanConfiguratorBehavior;
	
	private final ConfigurationBehavior configurationBehavior;
	
	private final ApplicationContextBehavior applicationContextBehavior;
	
	public BeanFactory(ApplicationContextBehavior applicationContextBehavior) {
		
		this.configurationBehavior = new JavaConfiguration();
		this.beanConfiguratorBehavior = new JavaBeanConfigurator(configurationBehavior.getPackageToScan(),
																 configurationBehavior.getInterfaceToImplementations());
		this.applicationContextBehavior = applicationContextBehavior;
	}
	
	public <T> T getBean(Class<T> clazz, Annotation[] annotations) {
		
		Class<? extends T> implementationClass = clazz;
		
		if(clazz.isInterface()) {
			
			implementationClass = beanConfiguratorBehavior.getImplementationClass(clazz, annotations);
			
		}
		
		T bean = null;
		
		try {
			bean = implementationClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.out.println("Can't create new instance of class " + implementationClass.toString());
			e.printStackTrace();
		}
		
		for(Field field: Arrays.stream(implementationClass.getDeclaredFields())
			  .filter(field -> field.isAnnotationPresent(Inject.class))
			  .collect(Collectors.toList())) {
			
			field.setAccessible(true);
			try {
				Annotation[] fieldAnnotations = field.getAnnotations();
				field.set(bean, applicationContextBehavior.getBean(field.getType(), fieldAnnotations));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		return bean;
		
	}
	
}
