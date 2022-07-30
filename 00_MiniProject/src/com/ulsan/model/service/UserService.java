package com.ulsan.model.service;

import static com.ulsan.common.JDBC.commit;

import static com.ulsan.common.JDBC.rollback;
import static com.ulsan.common.JDBC.openConnection;
import java.sql.Connection;
import com.ulsan.model.dao.UserDao;
import com.ulsan.model.vo.User;

public class UserService {
	private UserDao userDao = new UserDao();
	private Connection conn = null;
	
	public UserService() {
		conn = openConnection();
	}
	
	public User selectOne(int userNo) {
		return userDao.selectOne(conn, userNo);
	}
	
	public User selectByID(String userId) {
		return userDao.selectByID(conn, userId);
	}
	
	public int insert(User user) {
		int result = userDao.insert(conn, user);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
}