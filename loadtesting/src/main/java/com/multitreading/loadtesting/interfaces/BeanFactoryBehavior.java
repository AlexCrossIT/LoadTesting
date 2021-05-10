package com.multitreading.loadtesting.interfaces;

import java.lang.annotation.Annotation;

public interface BeanFactoryBehavior {

	<T> T getBean(Class<T> clazz, Annotation[] annotations);
	
}
