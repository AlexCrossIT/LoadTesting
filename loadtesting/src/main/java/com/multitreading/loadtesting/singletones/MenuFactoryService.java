package com.multitreading.loadtesting.singletones;

public enum MenuFactoryService {

	MENUFACTORY_URLS;
	
	String ingredientCreateUrl;
	String recipeCreateUrl;
	String menuCreateUrl;
	
	public String getIngredientCreateUrl() {
		return ingredientCreateUrl;
	}
	
	public void setIngredientCreateUrl(String ingredientCreateUrl) {
		this.ingredientCreateUrl = ingredientCreateUrl;
	}
	
	public String getRecipeCreateUrl() {
		return recipeCreateUrl;
	}
	
	public void setRecipeCreateUrl(String recipeCreateUrl) {
		this.recipeCreateUrl = recipeCreateUrl;
	}
	
	public String getMenuCreateUrl() {
		return menuCreateUrl;
	}
	
	public void setMenuCreateUrl(String menuCreateUrl) {
		this.menuCreateUrl = menuCreateUrl;
	}
	
		
}
