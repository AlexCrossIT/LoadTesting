package com.multitreading.loadtesting.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import com.multitreading.loadtesting.interfaces.IngredientsLoadTestingServiceBehavior;

public class IngredientsLoadTestingService implements IngredientsLoadTestingServiceBehavior{

	@Override
	public void createIngredients(String url, int treadsQuantity, int ingredientsQuantity) {
		
		HttpURLConnection httpConnection = null;
		OutputStream outputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
		
		Map<String, String> ingredientData = new HashMap<>();
		ingredientData.put("ingredientId", "1");
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
		
		byte[] content = stringJoiner.toString().getBytes(StandardCharsets.UTF_8);
		int contentLength = content.length;
		
		try {
			httpConnection = (HttpURLConnection) new URL(url).openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
			httpConnection.setConnectTimeout(30000);
			httpConnection.setReadTimeout(30000);
			httpConnection.setFixedLengthStreamingMode(contentLength);
			httpConnection.setDoOutput(true);
						
			httpConnection.getOutputStream().write(content);
			
			if(HttpURLConnection.HTTP_OK == httpConnection.getResponseCode()) {
				
				inputStreamReader = new InputStreamReader(httpConnection.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader);
				
				String line = null;
				
				while((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
				
		} finally {
			
			try {
				inputStreamReader.close();
				bufferedReader.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
			
	}

}
