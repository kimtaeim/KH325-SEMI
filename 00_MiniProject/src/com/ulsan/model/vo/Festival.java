package com.ulsan.model.vo;

import java.sql.Date;

public class Festival {
	private int fesNo;
	private String fesName;
	private String fesPlace;
	private String fesTheme;
	private Date fesStart;
	private Date fesEnd;
	private String fesTime;
	private String fesFee;
	private String fesHost;
	private String fesPhone;
	private String fesAge;
	private String fesAdd;
	
//	private int userNo;
//	private String searchType;
//	private String keyword;
	
	public Festival() {
		super();
	}
	
	public Festival(int fesNo, String fesName, String fesPlace, 
					String fesTheme, Date fesStart, Date fesEnd, 
					String fesTime, String fesFee, String fesHost, 
					String fesPhone, String fesAge, String fesAdd) {
		super();
		this.fesNo = fesNo;
		this.fesName = fesName;
		this.fesPlace = fesPlace;
		this.fesTheme = fesTheme;
		this.fesStart = fesStart;
		this.fesEnd = fesEnd;
		this.fesTime = fesTime;
		this.fesFee = fesFee;
		this.fesHost = fesHost;
		this.fesPhone = fesPhone;
		this.fesAge = fesAge;
		this.fesAdd = fesAdd;
	}

	public String toString() {
		return "축제 번호 : " + fesNo + ", 축제 이름 : " + fesName + ", 축제 장소 : " 
				+ fesPlace + ", 축제 테마 : " + fesTheme + ", 축제 시작일 : " + fesStart 
				+ ", 축제 종료일 : " + fesEnd + ", 축제 시간 : " + fesTime + ", 이용요금 : " 
				+ fesFee + ", 주최기관 : " + fesHost + ", 연락처 : " + fesPhone
				+ ", 입장연령 : " + fesAge + ", 축제 주소 : " + fesAdd;
	}

	public int getFesNo() {
		return fesNo;
	}

	public void setFesNo(int fesNo) {
		this.fesNo = fesNo;
	}

	public String getFesName() {
		return fesName;
	}

	public void setFesName(String fesName) {
		this.fesName = fesName;
	}

	public String getFesPlace() {
		return fesPlace;
	}

	public void setFesPlace(String fesPlace) {
		this.fesPlace = fesPlace;
	}

	public String getFesTheme() {
		return fesTheme;
	}

	public void setFesTheme(String fesTheme) {
		this.fesTheme = fesTheme;
	}

	public Date getFesStart() {
		return fesStart;
	}

	public void setFesStart(Date fesStart) {
		this.fesStart = fesStart;
	}

	public Date getFesEnd() {
		return fesEnd;
	}

	public void setFesEnd(Date fesEnd) {
		this.fesEnd = fesEnd;
	}

	public String getFesTime() {
		return fesTime;
	}

	public void setFesTime(String fesTime) {
		this.fesTime = fesTime;
	}

	public String getFesFee() {
		return fesFee;
	}

	public void setFesFee(String fesFee) {
		this.fesFee = fesFee;
	}

	public String getFesHost() {
		return fesHost;
	}

	public void setFesHost(String fesHost) {
		this.fesHost = fesHost;
	}

	public String getFesPhone() {
		return fesPhone;
	}

	public void setFesPhone(String fesPhone) {
		this.fesPhone = fesPhone;
	}

	public String getFesAge() {
		return fesAge;
	}

	public void setFesAge(String fesAge) {
		this.fesAge = fesAge;
	}

	public String getFesAdd() {
		return fesAdd;
	}

	public void setFesAdd(String fesAdd) {
		this.fesAdd = fesAdd;
	}

//	public int getUser_no() {
//		return userNo;
//	}
//
//	public void setUser_no(int user_no) {
//		this.userNo = user_no;
//	}
//
//	public String getSearchType() {
//		return searchType;
//	}
//
//	public void setSearchType(String searchType) {
//		this.searchType = searchType;
//	}
//
//	public String getKeyword() {
//		return keyword;
//	}
//
//	public void setKeyword(String keyword) {
//		this.keyword = keyword;
//	}
//	
	
}