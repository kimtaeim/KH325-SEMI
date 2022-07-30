package com.ulsan.model.vo;

public class Restaurant {
	private int resNo;
	private String resName;
	private String resType;
	private String resSigun;
	private String resAdd;
	private String resPhone;
	private String resMenu;
	
	private int userNo;
	private String searchType;
	private String keyword;
	
	public Restaurant() {
		super();
	}

	public Restaurant(int resNo, String resName, String resType, 
			String resSigun, String resAdd, String resPhone, String resMenu) {
		super();
		this.resNo = resNo;
		this.resName = resName;
		this.resType = resType;
		this.resSigun = resSigun;
		this.resAdd = resAdd;
		this.resPhone = resPhone;
		this.resMenu = resMenu;
	}

	@Override
	public String toString() {
		return "식당 번호 : " + resNo + ", 식당 이름 : " + resName 
				+ ", 업태 : " + resType + ", 시군명 : " + resSigun + ", 식당 주소 : " 
				+ resAdd + ", 식당 연락처 : " + resPhone + ", 식당 메뉴 : " + resMenu;
	}

	public int getResNo() {
		return resNo;
	}

	public void setResNo(int resNo) {
		this.resNo = resNo;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getResSigun() {
		return resSigun;
	}

	public void setResSigun(String resSigun) {
		this.resSigun = resSigun;
	}

	public String getResAdd() {
		return resAdd;
	}

	public void setResAdd(String resAdd) {
		this.resAdd = resAdd;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	public String getResMenu() {
		return resMenu;
	}

	public void setResMenu(String resMenu) {
		this.resMenu = resMenu;
	}

	public int getUser_no() {
		return userNo;
	}

	public void setUser_no(int user_no) {
		this.userNo = user_no;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}