package com.multitreading.loadtesting.services;


import com.multitreading.loadtesting.interfaces.IngredientsLoadTestingServiceBehavior;
import com.multitreading.loadtesting.threads.IngredientsThread;

public class LoadTestingService implements IngredientsLoadTestingServiceBehavior{

	@Override
	public void createIngredients(String url, int threadsQuantity, int ingredientsQuantity) {
		
		int ingredientQuantityPerThread = ingredientsQuantity / threadsQuantity;
		
		for(int i = 1; i <= threadsQuantity; i++) {
			
			if(i == threadsQuantity) {
				
				int lastThreadIngredientQuantity = ingredientsQuantity - ingredientQuantityPerThread * (threadsQuantity - 1); 
				
				Thread thread = new Thread(new IngredientsThread(url, lastThreadIngredientQuantity));
				thread.start();
				
			} else {
				
				Thread thread = new Thread(new IngredientsThread(url, ingredientQuantityPerThread));
				thread.start();
				
			}
			
		}
			
	}

}
