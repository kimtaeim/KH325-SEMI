package com.ulsan.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ulsan.model.vo.Restaurant;
import static com.ulsan.common.RestaurantCommon.*;

public class RestaurantDao {

	// 맛집 전체 조회
	public List<Restaurant> selectAll(Connection conn) {
		List<Restaurant> list = new ArrayList<>();
		
		try {
			String sql = "SELECT RES_NO, AREA_NAME, RES_NAME, RES_TYPE, RES_SIGUN, "
					   + "RES_ADD, RES_PHONE, RES_MENU FROM TBL_RESTAURANT";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				int resNo       = rs.getInt   (1);
				String resName  = rs.getString(2);
				String resType  = rs.getString(3);
				String resSigun = rs.getString(4);
				String resAdd   = rs.getString(5);
				String resPhone = rs.getString(6);
				String resMenu  = rs.getString(7);
				Restaurant r = new Restaurant(resNo, resName, resType, 
							   resSigun, resAdd, resPhone, resMenu);
				list.add(r);
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 맛집 검색
	public List<Restaurant> search(Connection conn, int searchType, String keyword){
		List<Restaurant> list = new ArrayList<>();
		String type = "";
		try {
			if(searchType == SEARCH_TYPE_LOCAL) {
				type = " FROM TBL_RESTAURANT WHERE RES_ADD LIKE ?";
			} else if (searchType == SEARCH_TYPE_MENU) {
				type = " FROM TBL_RESTAURANT WHERE RES_MENU LIKE ?";
			} else if (searchType == SEARCH_TYPE_NAME) {
				type = " FROM TBL_RESTAURANT WHERE RES_NAME LIKE ?";
			} else if (searchType == SEARCH_TYPE_THEME) {
				type = " FROM TBL_RESTAURANT WHERE RES_TYPE LIKE ?";
			}
			String sql = "SELECT RES_NO, RES_NAME, RES_TYPE, RES_SIGUN, "
					   + "RES_ADD, RES_PHONE, RES_MENU " + type;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int resNo       = rs.getInt   (1);
				String resName  = rs.getString(2);
				String resType  = rs.getString(3);
				String resSigun = rs.getString(4);
				String resAdd   = rs.getString(5);
				String resPhone = rs.getString(6);
				String resMenu  = rs.getString(7);
				Restaurant r = new Restaurant(resNo, resName, resType, 
							   resSigun, resAdd, resPhone, resMenu);
				list.add(r);
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertRestaurantData(Connection conn, Restaurant res) {
		try {
			String sql = "INSERT INTO TBL_RESTAURANT VALUES"
					   + "(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt    (1, res.getResNo());
			pstmt.setString (2, res.getResName());
			pstmt.setString (3, res.getResType());
			pstmt.setString (4, res.getResSigun());
			pstmt.setString (5, res.getResAdd());
			pstmt.setString (6, res.getResPhone());
			pstmt.setString (7, res.getResMenu());
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}