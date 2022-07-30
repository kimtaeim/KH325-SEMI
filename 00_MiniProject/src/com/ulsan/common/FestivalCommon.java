package com.ulsan.common;

public class FestivalCommon {
	public static final int SEARCH_TYPE_LOCAL = 1;
	public static final int SEARCH_TYPE_KEYWORD = 2;
	public static final int SEARCH_TYPE_THEME = 3;
	public static final int SEARCH_TYPE_DATE = 4;
	
	public FestivalCommon() {
		super();
	}

	public static int getSearchTypeLocal() {
		return SEARCH_TYPE_LOCAL;
	}

	public static int getSearchTypeKeyword() {
		return SEARCH_TYPE_KEYWORD;
	}

	public static int getSearchTypeTheme() {
		return SEARCH_TYPE_THEME;
	}

	public static int getSearchTypeDate() {
		return SEARCH_TYPE_DATE;
	}
}
