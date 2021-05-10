package com.multitreading.loadtesting.configurator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.interfaces.BeanConfiguratorBehavior;


public class JavaBeanConfigurator implements BeanConfiguratorBehavior{

	private final Reflections scanner;
	
	private final Map<Class, Class> interfaceToImplementation;
	
	public JavaBeanConfigurator(String packageToScanner, Map<Class, Class> interfaceToImplementation) {
		
		this.scanner = new Reflections(packageToScanner);
		this.interfaceToImplementation = interfaceToImplementation;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass, Annotation[] annotations) {
		
		return interfaceToImplementation.computeIfAbsent(interfaceClass, clazz -> {
			
			Set<Class<? extends T>> implementationClasses = scanner.getSubTypesOf(interfaceClass);
			
			if(implementationClasses.size() != 1) {
				Inject injectAnnotation = (Inject) Arrays.stream(annotations)
														 .filter(annotation -> annotation instanceof Inject)
														 .findFirst()
														 .orElseThrow(() -> new RuntimeException(String.format("Interface %class has %impCount implementations."
														 												 + " Add Inject annotation with \"name\" param.",
														 												 clazz, implementationClasses.size())));
				
				if(injectAnnotation == null || injectAnnotation.name().isEmpty()) {
					throw new RuntimeException(String.format("Interface %class has %impCount implementations."
															 + " Set \"name\" param for Inject annotation.",
															 clazz, implementationClasses.size()));
				}
				
				return implementationClasses.stream()
											.filter(implClass -> implClass.getName().contains(injectAnnotation.name()))
											.findFirst()
											.get();
			}
			
			return implementationClasses.stream()
										.findFirst()
										.get();
			
		});
		
	}

}
