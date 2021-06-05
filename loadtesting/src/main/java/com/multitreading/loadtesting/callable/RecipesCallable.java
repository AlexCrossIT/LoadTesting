package com.multitreading.loadtesting.callable;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Callable;

import com.multitreading.loadtesting.exceptions.LoadTestingException;

public class RecipesCallable implements Callable<String>{

	private final String url;
	
	private final int recipesQuantity;
	
	public RecipesCallable(String url, int recipesQuantity) {
		
		this.url = url;
		this.recipesQuantity = recipesQuantity;
		
	}
	
	@Override
	public String call() throws Exception {
		
		
		Instant startInstant = Instant.now();
		
		String threadName = Thread.currentThread().getName();
				
		System.out.println(threadName + " has started.");
		
		for(int i = 1; i <= recipesQuantity; i++) {
			byte[] recipeData = getRecipetData();
			sendRecipeData(recipeData);			
		}
		
		Instant finishInstant = Instant.now();
		
		System.out.println(threadName + " has finished. Duration - " + Duration.between(startInstant, finishInstant).toMinutes() + " minutes.");
		
		return "Well done, " + threadName;
		
	}
	
	private byte[] getRecipetData(){
		
		String jsonString = "{"
							+ System.lineSeparator() + "\"recipeId\": \"\","
							+ System.lineSeparator() + "\"recipeName\": \"" + UUID.randomUUID().toString() + "\","
							+ System.lineSeparator() + "\"ingredients\": [{"
											   + System.lineSeparator() + "\"ingredientId\": \"000267c9-a2db-4c08-8c50-b771ed14f61a\","
											   + System.lineSeparator() + "\"ingredientQuantity\": \"4\""
											   + System.lineSeparator() + "}]"
							+ "}";
		
		
		return jsonString.getBytes(StandardCharsets.UTF_8);
		
	}
	
	private void sendRecipeData(byte[] recipeData) {
		
		HttpURLConnection httpConnection = null;
					
		try {
			httpConnection = (HttpURLConnection) new URL(url).openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		httpConnection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
		httpConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
		httpConnection.setConnectTimeout(30000);
		httpConnection.setReadTimeout(30000);
		httpConnection.setFixedLengthStreamingMode(recipeData.length);
		httpConnection.setDoOutput(true);
					
		try (OutputStream outputStream = httpConnection.getOutputStream()){
			outputStream.write(recipeData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
			int httpResponseCode = httpConnection.getResponseCode();
			
			if(httpResponseCode != HttpURLConnection.HTTP_OK && httpResponseCode != HttpURLConnection.HTTP_MOVED_TEMP) {
				throw new LoadTestingException("Cannot create recipe. Response code: " + httpConnection.getResponseCode());				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LoadTestingException e) {
			System.out.println(e.getMessage());
		}
				
				
	}

}
