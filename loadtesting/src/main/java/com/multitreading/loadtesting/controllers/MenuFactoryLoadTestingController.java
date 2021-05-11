package com.multitreading.loadtesting.controllers;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.factories.BeanFactory;
import com.multitreading.loadtesting.interfaces.LoadTestingServiceBehavior;
import com.multitreading.loadtesting.singletones.MenuFactoryProperties;

public class MenuFactoryLoadTestingController extends Controller{
	
	@Inject(name = "MenuFactoryLoadTestingService")
	private LoadTestingServiceBehavior loadTestingServiceBehavior;
		
	@Override
	public void runLoadTesting() {
		
		loadTestingServiceBehavior.runIngredientsLoadTesting();
		
		loadTestingServiceBehavior.runRecipesLoadTesting();
		
		loadTestingServiceBehavior.runMenusLoadTesting();
	
	}

}
