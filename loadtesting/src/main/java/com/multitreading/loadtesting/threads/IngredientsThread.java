package com.multitreading.loadtesting.threads;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import com.multitreading.loadtesting.exceptions.LoadTestingException;

public class IngredientsThread implements Runnable{

	private final String url;
	
	private final int ingredientsQuantity;
	
	public IngredientsThread(String url, int ingredientsQuantity) {
		
		this.url = url;
		this.ingredientsQuantity = ingredientsQuantity;
		
	}
	
	@Override
	public void run() {
			
		Instant startInstant = Instant.now();
		
		String threadName = Thread.currentThread().getName();
				
		System.out.println(threadName + " has started.");
		
		byte[] ingredientData = getIngredientData();
		
		for(int i = 1; i <= ingredientsQuantity; i++) {
			sendIngredientData(ingredientData);			
		}
		
		Instant finishInstant = Instant.now();
		
		System.out.println(threadName + " has finished. Duration - " + Duration.between(startInstant, finishInstant).toMinutes() + " minutes.");
		
	}
	
	private byte[] getIngredientData(){
		
		Map<String, String> ingredientData = new HashMap<>();
		ingredientData.put("ingredientId", "");
		ingredientData.put("ingredientName", "Ingredient number 1");
		
		StringJoiner stringJoiner = new StringJoiner("&");
		
		try {
			
			for(Map.Entry<String, String> entry: ingredientData.entrySet()) {
				
					stringJoiner.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
									+ URLEncoder.encode(entry.getValue(), "UTF-8"));
					
			}
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return stringJoiner.toString().getBytes(StandardCharsets.UTF_8);
		
	}
	
	private void sendIngredientData(byte[] ingredientData) {
		
		HttpURLConnection httpConnection = null;
					
		try {
			httpConnection = (HttpURLConnection) new URL(url).openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		httpConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
		httpConnection.setConnectTimeout(30000);
		httpConnection.setReadTimeout(30000);
		httpConnection.setFixedLengthStreamingMode(ingredientData.length);
		httpConnection.setDoOutput(true);
					
		try (OutputStream outputStream = httpConnection.getOutputStream()){
			outputStream.write(ingredientData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
			int httpResponseCode = httpConnection.getResponseCode();
			
			if(httpResponseCode != HttpURLConnection.HTTP_OK && httpResponseCode != HttpURLConnection.HTTP_MOVED_TEMP) {
				throw new LoadTestingException("Cannot create ingredient. Response code: " + httpConnection.getResponseCode());				
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LoadTestingException e) {
			System.out.println(e.getMessage());
		}
				
				
	}

}
