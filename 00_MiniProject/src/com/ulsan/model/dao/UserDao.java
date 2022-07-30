package com.ulsan.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ulsan.model.vo.User;

public class UserDao {

	public User selectOne(Connection conn, int userNo) {
		try {
			String sql = "SELECT USER_NO, USER_ID, USER_PW FROM TBL_USER "
					   + "FROM USERS WHERE USER_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, userNo);

			ResultSet rs = pstmt.executeQuery(); 
			rs.next(); 
			userNo 	      = rs.getInt(1);
			String userId = rs.getString(2);
			String userPw = rs.getString(3);

			User user = new User(userNo, userId, userPw);
			
			pstmt.close();
			rs.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User selectByID(Connection conn, String userId) {
		User user = null;
		try {
			String sql = "SELECT USER_NO, USER_ID, USER_PW FROM TBL_USER "
					   + " WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int userNo 	  = rs.getInt(1);
				userId 		  = rs.getString(2);
				String userPw = rs.getString(3);
				
				user = new User(userNo, userId, userPw);
			}
			pstmt.close();
			rs.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int insert(Connection conn, User user) {
		try {
			String sql = "INSERT INTO TBL_USER VALUES(SEQ_USER_NO.NEXTVAL, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
		}
		return -1;
	}
}