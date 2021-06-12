package com.multitreading.loadtesting.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.multitreading.loadtesting.interfaces.MenusLoadTestingServiceBehavior;
import com.multitreading.loadtesting.threads.MenusThread;

public class MenusLoadTestingService implements MenusLoadTestingServiceBehavior{

	@Override
	public void createMenus(String url, int menusQuantity) {
		
		int threadsQuantity = Runtime.getRuntime().availableProcessors();
		
		ExecutorService executorService = Executors.newFixedThreadPool(threadsQuantity);
		
		int ingredientQuantityPerThread = menusQuantity / threadsQuantity;
		
		for(int i = 1; i <= threadsQuantity; i++) {
			
			if(i == threadsQuantity) {
				
				int lastThreadIngredientQuantity = menusQuantity - ingredientQuantityPerThread * (threadsQuantity - 1); 
				
				executorService.execute(new Thread(new MenusThread(url, lastThreadIngredientQuantity)));
							
			} else {
								
				executorService.execute(new Thread(new MenusThread(url, ingredientQuantityPerThread)));
				
			}
			
		}
		
		executorService.shutdown();
		
	}

}
