package com.ulsan.model.service;

import static com.ulsan.common.JDBC.close;
import static com.ulsan.common.JDBC.commit;
import static com.ulsan.common.JDBC.openConnection;
import static com.ulsan.common.JDBC.rollback;
import java.sql.Connection;
import java.util.List;
import com.ulsan.model.dao.RestaurantDao;
import com.ulsan.model.vo.Restaurant;

public class RestaurantService {
	private RestaurantDao resDao = new RestaurantDao();
	private Connection conn = null;
	
	public RestaurantService() {
		conn = openConnection();
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	protected void finalize() throws Throwable {
		close(conn);
	}
	
	public int insertRestaurantData(Restaurant res) {
		int result = resDao.insertRestaurantData(getConnection(), res);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public List<Restaurant> selectAll(){
		Connection conn = getConnection();
		return resDao.selectAll(conn);
	}

	public List<Restaurant> search(int searychType, String keyword) {
		Connection conn = getConnection();
		return resDao.search(conn, searychType, keyword);
	}
}