package com.multitreading.loadtesting.contexts;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.multitreading.loadtesting.factories.BeanFactory;
import com.multitreading.loadtesting.interfaces.ApplicationContextBehavior;
import com.multitreading.loadtesting.interfaces.BeanFactoryBehavior;

public class ApplicationContext implements ApplicationContextBehavior{

	private BeanFactoryBehavior beanFactoryBehavior;
	
	private final Map<Class, Object> beanCache = new ConcurrentHashMap<>();
	
	public ApplicationContext() {
		
	}
	
	@Override
	public void initializeApplicationContext() {
		
		ApplicationContextBehavior applicationContextBehavior = new ApplicationContext();
		BeanFactoryBehavior beanFactoryBehavior = new BeanFactory(applicationContextBehavior);
		applicationContextBehavior.setBeanFactory(beanFactoryBehavior);
		
		beanCache.put(ApplicationContextBehavior.class, applicationContextBehavior);
		beanCache.put(BeanFactoryBehavior.class, beanFactoryBehavior);
				
	}
	
	public void setBeanFactory(BeanFactoryBehavior beanFactoryBehavior) {
		this.beanFactoryBehavior = beanFactoryBehavior;
	}

	@Override
	public <T> T getBean(Class<T> clazz, Annotation[] annotations) {
		
		if(beanCache.containsKey(clazz)) {
			return (T) beanCache.get(clazz);
		}
				
		T bean = beanFactoryBehavior.getBean(clazz, annotations);
		
		beanCache.put(clazz, bean);
		
		return bean;
		
	}

}
