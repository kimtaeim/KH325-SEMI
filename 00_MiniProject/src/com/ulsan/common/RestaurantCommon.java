package com.ulsan.common;

public class RestaurantCommon {
	public static final int SEARCH_TYPE_LOCAL = 1;
	public static final int SEARCH_TYPE_MENU = 2;
	public static final int SEARCH_TYPE_NAME = 3;
	public static final int SEARCH_TYPE_THEME = 4;
	
	public RestaurantCommon() {
		super();
	}

	public static int getSearchTypeLocal() {
		return SEARCH_TYPE_LOCAL;
	}

	public static int getSearchTypeMenu() {
		return SEARCH_TYPE_MENU;
	}

	public static int getSearchTypeName() {
		return SEARCH_TYPE_NAME;
	}

	public static int getSearchTypeTheme() {
		return SEARCH_TYPE_THEME;
	}
}
