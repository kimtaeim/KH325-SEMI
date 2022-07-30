package com.ulsan.model.service;

import static com.ulsan.common.JDBC.commit;
import static com.ulsan.common.JDBC.openConnection;
import static com.ulsan.common.JDBC.rollback;
import java.sql.Connection;
import java.util.List;
import com.ulsan.model.dao.FestivalLikeDao;
import com.ulsan.model.vo.FesLike;

public class FesLikeService {
	private FestivalLikeDao fesLikeDao = new FestivalLikeDao();
	private Connection conn = null;
	
	public FesLikeService() {
		conn = openConnection();
	}
	
	public List<FesLike> selectAll(int userNo) {
		return fesLikeDao.selectAll(conn, userNo);
	}
	
	public int fesLikeInsert(int userNo, int fesNo) {
		int result = fesLikeDao.fesLikeInsert(conn, userNo, fesNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int fesLikeDeletOne(int likeNo) {
		int result = fesLikeDao.fesLikeDeleteOne(conn, likeNo);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int fesLikeDeleteAll() {
		int result = fesLikeDao.fesLikeDeletAll(conn);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
}