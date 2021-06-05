package com.multitreading.loadtesting.interfaces;

import java.util.Map;

public interface ConfigurationBehavior {

	String getPackageToScan();
	
	String getIngredientCreateUrl();
	
	String getRecipeCreateUrl();
	
	String getMenuCreateUrl();
	
	Map<Class, Class> getInterfaceToImplementations();
	
}
