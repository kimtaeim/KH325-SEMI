package com.ulsan.model.service;

import static com.ulsan.common.JDBC.commit;
import static com.ulsan.common.JDBC.openConnection;
import static com.ulsan.common.JDBC.rollback;
import java.sql.Connection;
import java.util.List;
import com.ulsan.model.dao.RestaurantLikeDao;
import com.ulsan.model.vo.ResLike;

public class ResLikeService {
	private RestaurantLikeDao resLikeDao = new RestaurantLikeDao();
	private Connection conn = null;
	
	public ResLikeService() {
		conn = openConnection();
	}
	
	public List<ResLike> selectAll(int userNo) {
		return resLikeDao.selectAll(conn, userNo);
	}
	
	public int resLikeInsert(int userNo, int resNo) {
		int result = resLikeDao.resLikeInsert(conn, userNo, resNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int resLikeDeletOne(int likeNo) {
		int result = resLikeDao.resLikeDeleteOne(conn, likeNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int resLikeDeleteAll() {
		int result = resLikeDao.resLikeDeletAll(conn);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
}