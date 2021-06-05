package com.multitreading.loadtesting.config;

import java.util.HashMap;
import java.util.Map;

import com.multitreading.loadtesting.controllers.MenuFactoryLoadTestingController;
import com.multitreading.loadtesting.interfaces.ConfigurationBehavior;
import com.multitreading.loadtesting.interfaces.LoadTestingControllerBehavior;

public class JavaConfiguration implements ConfigurationBehavior{

	@Override
	public String getPackageToScan() {
		
		return "com.multitreading.loadtesting";
	}
	
	public String getIngredientCreateUrl() {
		
		return "https://menufactory.herokuapp.com/ingredient-edit";
		
	}

	@Override
	public String getRecipeCreateUrl() {
		
		return "https://menufactory.herokuapp.com/recipe-edit";
		
	}

	@Override
	public String getMenuCreateUrl() {
		
		return "https://menufactory.herokuapp.com/menu-edit";
		
	}
	
	@Override
	public Map<Class, Class> getInterfaceToImplementations() {
	
		Map<Class, Class> compliance = new HashMap<>();
		
		compliance.put(LoadTestingControllerBehavior.class, MenuFactoryLoadTestingController.class);
		
		return compliance;
		
	}


}
