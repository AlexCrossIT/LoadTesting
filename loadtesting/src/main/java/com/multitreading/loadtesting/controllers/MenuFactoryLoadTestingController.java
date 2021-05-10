package com.multitreading.loadtesting.controllers;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.factories.BeanFactory;
import com.multitreading.loadtesting.interfaces.LoadTestingServiceBehavior;
import com.multitreading.loadtesting.singletones.MenuFactoryProperties;

public class MenuFactoryLoadTestingController extends Controller{

	private final MenuFactoryProperties MENUFACTORY_URLS = MenuFactoryProperties.MENUFACTORY_URLS;
	
	@Inject(name = "MenuFactoryLoadTestingService")
	private LoadTestingServiceBehavior loadTestingServiceBehavior;
		
	@Override
	public void runLoadTesting() {
		
		loadTestingServiceBehavior.runIngredientsLoadTesting(MENUFACTORY_URLS.getIngredientCreateUrl());
		
		loadTestingServiceBehavior.runRecipesLoadTesting(MENUFACTORY_URLS.getRecipeCreateUrl());
		
		loadTestingServiceBehavior.runMenusLoadTesting(MENUFACTORY_URLS.getMenuCreateUrl());
	
	}

}
