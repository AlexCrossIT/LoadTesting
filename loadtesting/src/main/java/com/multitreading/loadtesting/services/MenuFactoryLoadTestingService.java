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
		
		//ingredientsLoadTestingServiceBehavior.createIngredients(configurationBehavior.getIngredientCreateUrl(), 8, 13000);
		
	}

	@Override
	public void runRecipesLoadTesting() {
		
		//recipesLoadTestingServiceBehavior.createRecipes(configurationBehavior.getRecipeCreateUrl(), 8, 10000);
		
	}

	@Override
	public void runMenusLoadTesting() {
		
		menusLoadTestingServiceBehavior.createMenus(configurationBehavior.getMenuCreateUrl(), 10000);
		
	}

	
}
