package com.ulsan.model.vo;

import java.sql.Date;

public class FesLike {
	
	private int likeNo;
	private int userNo;
	private int fesNo;
	
	private String fesName;
	private String fesPlace;
	private Date   fesStart;
	private Date   fesEnd;
	private String fesPhone;
	private String fesAdd;
	
	public FesLike() {
		super();
	}

	public FesLike(int likeNo, int userNo, int fesNo, String fesName, 
				   String fesPlace, Date fesStart, Date fesEnd, 
				   String fesPhone, String fesAdd) {
		super();
		this.likeNo = likeNo;
		this.userNo = userNo;
		this.fesNo = fesNo;
		this.fesName = fesName;
		this.fesPlace = fesPlace;
		this.fesStart = fesStart;
		this.fesEnd = fesEnd;
		this.fesPhone = fesPhone;
		this.fesAdd = fesAdd;
	}

	@Override
	public String toString() {
		return "FesLike [축제 찜번호 : " + likeNo + ", 회원 번호 : " + userNo 
			   + ", 축제 번호 : " + fesNo + ", 축제 이름 : " + fesName 
			   + ", 축제 장소 : " + fesPlace + ", 축제 시작일 : " + fesStart 
			   + ", 축제 종료일 : " + fesEnd + ", 축제 연락처 : " + fesPhone
			   + ", 축제 주소 : " + fesAdd + "]";
	}
	
	public String fesLikeInfo() {
		return "축제 이름 : " + fesName + ", 축제 장소 : " + fesPlace 
			   + ", 축제 시작일 : " + fesStart + ", 축제 종료일 : " + fesEnd 
			   + ", 축제 연락처 : " + fesPhone + ", 축제 주소 : " + fesAdd;
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

	public String getFesPhone() {
		return fesPhone;
	}

	public void setFesPhone(String fesPhone) {
		this.fesPhone = fesPhone;
	}

	public String getFesAdd() {
		return fesAdd;
	}

	public void setFesAdd(String fesAdd) {
		this.fesAdd = fesAdd;
	}
}