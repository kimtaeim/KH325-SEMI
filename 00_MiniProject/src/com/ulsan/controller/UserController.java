package com.ulsan.controller;

import com.ulsan.model.service.UserService;
import com.ulsan.model.vo.User;

public class UserController {
	private UserService userService = new UserService();
	private static User loginUser = null;
	
	public boolean login(String userId, String userPw) {
		User user = userService.selectByID(userId);
		if(user == null) {
			return false;
		}
		
		if(user.getUserPw().equals(userPw) == true) {
			loginUser = user;
			return true;
		}else {
			return false;
		}
	}
	
	public static void logout() {
		loginUser = null;
	}
	
	public static User getLoginUser() {
		return loginUser;
	}
}