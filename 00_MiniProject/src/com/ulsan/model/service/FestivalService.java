package com.ulsan.model.service;

import static com.ulsan.common.JDBC.commit;
import static com.ulsan.common.JDBC.rollback;
import static com.ulsan.common.JDBC.close;
import static com.ulsan.common.JDBC.openConnection;
import java.sql.Connection;
import java.util.List;
import com.ulsan.model.dao.FestivalDao;
import com.ulsan.model.vo.Festival;

public class FestivalService {
	private FestivalDao fesDao = new FestivalDao();
	private Connection conn = null;
	
	public FestivalService() {
		conn = openConnection();
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	protected void finalize() throws Throwable {
		close(conn);
	}
	
	public int insertFestivalData(Festival fes) {
		int result = fesDao.insertFestivalData(getConnection(), fes);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
	
	public List<Festival> selectAll() {
		Connection conn = getConnection();
		return fesDao.selectAll(conn);
	}
	
	public List<Festival> search(int searchType, String keyword) {
		Connection conn = getConnection();
		return fesDao.search(conn, searchType, keyword);
	}
}