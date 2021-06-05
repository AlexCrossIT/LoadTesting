package com.multitreading.loadtesting.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.multitreading.loadtesting.callable.RecipesCallable;
import com.multitreading.loadtesting.interfaces.RecipesLoadTestingServiceBehavior;

public class RecipesLoadTestingService implements RecipesLoadTestingServiceBehavior{

	@Override
	public void createRecipes(String url, int threadsQuantity, int recipesQuantity) {
		
		List<FutureTask<String>> futureTaskList = new ArrayList<>();
		
		int ingredientQuantityPerThread = recipesQuantity / threadsQuantity;
		
		for(int i = 1; i <= threadsQuantity; i++) {
			
			if(i == threadsQuantity) {
				
				int lastThreadRecipeQuantity = recipesQuantity - ingredientQuantityPerThread * (threadsQuantity - 1); 
				
				FutureTask<String> futureTask = new FutureTask<>(new RecipesCallable(url, lastThreadRecipeQuantity));
				Thread thread = new Thread(futureTask);
				thread.setDaemon(true);
				thread.start();
				
				futureTaskList.add(futureTask);
				
			} else {
				
				FutureTask<String> futureTask = new FutureTask<>(new RecipesCallable(url, recipesQuantity));
				Thread thread = new Thread(futureTask);
				thread.setDaemon(true);
				thread.start();
				
				futureTaskList.add(futureTask);
				
			}
			
		}
		
		for(FutureTask<String> futureTask: futureTaskList) {
			
			try {
				System.out.println(futureTask.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			
		}
		
	}



}
