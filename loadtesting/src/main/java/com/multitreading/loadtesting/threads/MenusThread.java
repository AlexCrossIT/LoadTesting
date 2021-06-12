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
import java.util.UUID;

import com.multitreading.loadtesting.exceptions.LoadTestingException;

public class MenusThread implements Runnable{

	private final String url;
	
	private final int menusQuantity;
	
	public MenusThread(String url, int menusQuantity) {
		
		this.url = url;
		this.menusQuantity = menusQuantity;
		
	}
	
	@Override
	public void run() {
			
		Instant startInstant = Instant.now();
		
		String threadName = Thread.currentThread().getName();
				
		System.out.println(threadName + " has started.");
				
		for(int i = 1; i <= menusQuantity; i++) {
			byte[] menuData = getMenuData();
			sendMenuData(menuData);			
		}
		
		Instant finishInstant = Instant.now();
		
		System.out.println(threadName + " has finished. Duration - " + Duration.between(startInstant, finishInstant).toMinutes() + " minutes.");
		
	}
	
	private byte[] getMenuData(){
		
		Map<String, String> menuData = new HashMap<>();
		
		menuData.put("menuId", "");
		menuData.put("menuName", UUID.randomUUID().toString());
		menuData.put("recipes[0]", "recipeId=000267c9-a2db-4c08-8c50-b771ed14f61a");
		menuData.put("recipes[0]", "recipeName=Test recipe");
		
		StringJoiner stringJoiner = new StringJoiner("&");
		
		try {
			
			for(Map.Entry<String, String> entry: menuData.entrySet()) {
				
					stringJoiner.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
									+ URLEncoder.encode(entry.getValue(), "UTF-8"));
					
			}
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return stringJoiner.toString().getBytes(StandardCharsets.UTF_8);
				
	}
	
	private void sendMenuData(byte[] menuData) {
		
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
		httpConnection.setFixedLengthStreamingMode(menuData.length);
		httpConnection.setDoOutput(true);
					
		try (OutputStream outputStream = httpConnection.getOutputStream()){
			
			outputStream.write(menuData);
			
			int httpResponseCode = httpConnection.getResponseCode();
			
			if(httpResponseCode != HttpURLConnection.HTTP_OK && httpResponseCode != HttpURLConnection.HTTP_MOVED_TEMP) {
				throw new LoadTestingException("Cannot create menu. Response code: " + httpConnection.getResponseCode());				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LoadTestingException e) {
			System.out.println(e.getMessage());
		} finally {
			httpConnection.disconnect();
		}				
				
	}
	
}
