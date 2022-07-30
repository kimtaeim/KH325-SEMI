package com.ulsan.model.vo;

public class User {
	
	private int userNo;
	private String userId;
	private String userPw;

	public User() {
		super();
	}

	public User(int userNo, String userId, String userPw) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
	}

	public String toString() {
		return "User [회원 번호 : " + userNo + ", ID : " + userId + ", PW : " 
				+ userPw + "]";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
}