package com.multitreading.loadtesting.controllers;

import com.multitreading.loadtesting.interfaces.LoadTestingControllerBehavior;
import com.multitreading.loadtesting.services.LoadTestingService;
import com.multitreading.loadtesting.singletones.MenuFactoryProperties;

public class LoadTestingController implements LoadTestingControllerBehavior{

	public final MenuFactoryProperties MENUFACTORY_URLS = MenuFactoryProperties.MENUFACTORY_URLS;
	
	public final LoadTestingService loadTestingService;
	
	public LoadTestingController(LoadTestingService loadTestingService) {
		
		this.loadTestingService = loadTestingService;
		
	}
	
	@Override
	public void runLoadTesting() {
		
		loadTestingService.runIngredientsLoadTesting(MENUFACTORY_URLS.getIngredientCreateUrl());
		
		loadTestingService.runRecipesLoadTesting(MENUFACTORY_URLS.getRecipeCreateUrl());
		
		loadTestingService.runMenusLoadTesting(MENUFACTORY_URLS.getMenuCreateUrl());
		
	}

}
