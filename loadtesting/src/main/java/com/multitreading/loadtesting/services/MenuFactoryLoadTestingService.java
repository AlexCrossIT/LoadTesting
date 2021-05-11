package com.multitreading.loadtesting.services;

import com.multitreading.loadtesting.annotations.Inject;
import com.multitreading.loadtesting.interfaces.ConfigurationBehavior;
import com.multitreading.loadtesting.interfaces.IngredientsLoadTestingServiceBehavior;
import com.multitreading.loadtesting.interfaces.MenusLoadTestingServiceBehavior;
import com.multitreading.loadtesting.interfaces.RecipesLoadTestingServiceBehavior;

public class MenuFactoryLoadTestingService extends Service{

	@Inject private IngredientsLoadTestingServiceBehavior ingredientsLoadTestingServiceBehavior;
	@Inject private RecipesLoadTestingServiceBehavior recipesLoadTestingServiceBehavior;
	@Inject private MenusLoadTestingServiceBehavior menusLoadTestingServiceBehavior;
	@Inject private ConfigurationBehavior configurationBehavior;
	
	@Override
	public void runIngredientsLoadTesting() {
		
		ingredientsLoadTestingServiceBehavior.createIngredients(configurationBehavior.getIngredientCreateUrl(), 8, 8000);
		
	}

	@Override
	public void runRecipesLoadTesting() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runMenusLoadTesting() {
		// TODO Auto-generated method stub
		
	}

	
}
