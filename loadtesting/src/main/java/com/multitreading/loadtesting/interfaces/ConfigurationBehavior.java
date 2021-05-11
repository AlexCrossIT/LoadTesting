package com.multitreading.loadtesting.interfaces;

import java.util.Map;

public interface ConfigurationBehavior {

	String getPackageToScan();
	
	String getIngredientCreateUrl();
	
	Map<Class, Class> getInterfaceToImplementations();
	
}
