package com.multitreading.loadtesting.interfaces;

import java.lang.annotation.Annotation;

public interface BeanConfiguratorBehavior {

	<T> Class<? extends T> getImplementationClass(Class<T> interfaceClass, Annotation[] annotations);
	
}
