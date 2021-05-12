package com.multitreading.loadtesting.services;


import com.multitreading.loadtesting.interfaces.IngredientsLoadTestingServiceBehavior;
import com.multitreading.loadtesting.threads.IngredientsThread;


public class IngredientsLoadTestingService implements IngredientsLoadTestingServiceBehavior{

	@Override
	public void createIngredients(String url, int threadsQuantity, int ingredientsQuantity) {
		
		int ingredientsQuantityPerThread = ingredientsQuantity / threadsQuantity;
		
		for(int i = 1; i <= threadsQuantity; i++) {
			
			if(i == threadsQuantity) {
				
				int lastThreadIngredientsQuantity = ingredientsQuantity - ingredientsQuantityPerThread * (threadsQuantity - 1);
				
				Thread thread = new Thread(new IngredientsThread(url, lastThreadIngredientsQuantity));
				thread.run();
				
			} else {
				
				Thread thread = new Thread(new IngredientsThread(url, ingredientsQuantityPerThread));
				thread.run();
				
			}
			
		}
			
	}

}
