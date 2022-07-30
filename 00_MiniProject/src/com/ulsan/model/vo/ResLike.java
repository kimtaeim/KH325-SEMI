package com.ulsan.model.vo;

public class ResLike {
	
	private int likeNo;
	private int userNo;
	private int resNo;
	
	private String resname;
	private String restype;
	private String resadd;
	private String resphone;
	private String resmenu;
	
	public ResLike() {
		super();
	}
	
	public ResLike(int likeNo, int userNo, int resNo) {
		super();
		this.likeNo = likeNo;
		this.userNo = userNo;
		this.resNo = resNo;
	}
	
	public ResLike(int likeNo, int userNo, int resNo, String resname, 
			String restype, String resadd, String resphone, String resmenu) {
		super();
		this.likeNo = likeNo;
		this.userNo = userNo;
		this.resNo = resNo;
		this.resname = resname;
		this.restype = restype;
		this.resadd = resadd;
		this.resphone = resphone;
		this.resmenu = resmenu;
	}
	
	public String toString() {
		return "ResLike [맛집 찜번호 : " + likeNo + ", 회원 번호 : " + userNo 
			   + ", 식당 번호 : " + resNo + ", 식당 이름 : " + resname 
			   + ", 업태 : " + restype + ", 식당 주소 : " + resadd 
			   + ", 식당 연락처 : " + resphone + ", 식당 메뉴 : " + resmenu + "]";
	}
	
	public String resLikeInfo() {
		return "맛집 이름 : " + resname + ", 업태 : " + restype + ", 식당 주소 : " 
			   + resadd + ", 식당 연락처 : " + resphone + ", 식당 메뉴 : " + resmenu;
	}
	
	public String getResname() {
		return resname;
	}

	public void setResname(String resname) {
		this.resname = resname;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getResadd() {
		return resadd;
	}

	public void setResadd(String resadd) {
		this.resadd = resadd;
	}

	public String getResphone() {
		return resphone;
	}

	public void setResphone(String resphone) {
		this.resphone = resphone;
	}

	public String getResmenu() {
		return resmenu;
	}

	public void setResmenu(String resmenu) {
		this.resmenu = resmenu;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getResNo() {
		return resNo;
	}

	public void setResNo(int resNo) {
		this.resNo = resNo;
	}
}