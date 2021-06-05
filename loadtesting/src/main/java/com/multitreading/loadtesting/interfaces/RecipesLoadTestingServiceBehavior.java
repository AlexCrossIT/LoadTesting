package com.multitreading.loadtesting.interfaces;

public interface RecipesLoadTestingServiceBehavior {

	public void createRecipes(String url, int threadsQuantity, int recipesQuantity);
	
}
