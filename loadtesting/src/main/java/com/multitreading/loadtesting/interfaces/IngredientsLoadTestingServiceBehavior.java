package com.multitreading.loadtesting.interfaces;

public interface IngredientsLoadTestingServiceBehavior {

	public void createIngredients(String url, int threadsQuantity, int ingredientsQuantity);
	
}
