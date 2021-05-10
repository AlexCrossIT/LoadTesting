package com.multitreading.loadtesting.interfaces;

import java.lang.annotation.Annotation;

public interface ApplicationContextBehavior {

	void initializeApplicationContext();
	
	<T> T getBean(Class<T> clazz, Annotation[] annotations);
	
	void setBeanFactory(BeanFactoryBehavior beanFactoryBehavior);
	
}
