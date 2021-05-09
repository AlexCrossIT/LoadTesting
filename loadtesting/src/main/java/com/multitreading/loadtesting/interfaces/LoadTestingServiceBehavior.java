package com.multitreading.loadtesting.interfaces;

public interface LoadTestingServiceBehavior {

	public void runIngredientsLoadTesting(String url);
	
	public void runRecipesLoadTesting(String url);
	
	public void runMenusLoadTesting(String url);
		
}
