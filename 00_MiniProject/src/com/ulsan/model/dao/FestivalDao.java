package com.ulsan.model.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ulsan.model.vo.Festival;
import static com.ulsan.common.FestivalCommon.*;

public class FestivalDao {
	
	// 축제 전체 조회
	public List<Festival> selectAll(Connection conn) {
		List<Festival> list = new ArrayList<>();
		try {
			String sql = "SELECT FES_NO, FES_NAME, FES_PLACE, FES_THEME, FES_START, "
				   	   + "FES_END, FES_TIME, FES_FEE, FES_HOST, FES_PHONE, FES_AGE, "
				   	   + "FES_ADD FROM TBL_FESTIVAL";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				int    fesNo    = rs.getInt   (1);
				String fesName  = rs.getString(2);
				String fesPlace = rs.getString(3);
				String fesTheme = rs.getString(4);
				Date   fesStart = rs.getDate  (5);
				Date   fesEnd   = rs.getDate  (6);
				String fesTime  = rs.getString(7);
				String fesFee   = rs.getString(8);
				String fesHost  = rs.getString(9);
				String fesPhone = rs.getString(10);
				String fesAge   = rs.getString(11);
				String fesAdd   = rs.getString(12);
				Festival f = new Festival(fesNo, fesName, fesPlace, fesTheme, 
							 fesStart, fesEnd, fesTime, fesFee, fesHost,
							 fesPhone, fesAge, fesAdd);
				list.add(f);
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 축제 검색
	public List<Festival> search(Connection conn, int searchType, String keyword) {
		List<Festival> list = new ArrayList<>();
		String type = "";
		try {
			String sql = "SELECT FES_NO, FES_NAME, FES_PLACE, FES_THEME, FES_START, "
					+ "FES_END, FES_TIME, FES_FEE, FES_HOST, FES_PHONE, FES_AGE, "
					+ "FES_ADD ";
			if(searchType == SEARCH_TYPE_LOCAL) {
				type = " FROM TBL_FESTIVAL WHERE FES_ADD LIKE ?";
			} else if (searchType == SEARCH_TYPE_KEYWORD) {
				type = " FROM TBL_FESTIVAL WHERE FES_NAME LIKE ?";
			} else if (searchType == SEARCH_TYPE_THEME) {
				type = " FROM TBL_FESTIVAL WHERE FES_THEME LIKE ?";
			} else if (searchType == SEARCH_TYPE_DATE) {
				type = " FROM TBL_FESTIVAL WHERE ? BETWEEN FES_START AND FES_END";
			}
			sql += type;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(!(searchType == SEARCH_TYPE_DATE)) {
				pstmt.setString(1, "%" + keyword + "%");
			}else {
				pstmt.setString(1, keyword);
			}
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int    fesNo    = rs.getInt   (1);
				String fesName  = rs.getString(2);
				String fesPlace = rs.getString(3);
				String fesTheme = rs.getString(4);
				Date   fesStart = rs.getDate  (5);
				Date   fesEnd   = rs.getDate  (6);
				String fesTime  = rs.getString(7);
				String fesFee   = rs.getString(8);
				String fesHost  = rs.getString(9);
				String fesPhone = rs.getString(10);
				String fesAge   = rs.getString(11);
				String fesAdd   = rs.getString(12);
				Festival f = new Festival(fesNo, fesName, fesPlace, fesTheme, 
							 fesStart, fesEnd, fesTime, fesFee, fesHost, 
							 fesPhone, fesAge, fesAdd);
				list.add(f);
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertFestivalData(Connection conn, Festival fes) {
		try {
			String sql = "INSERT INTO TBL_FESTIVAL VALUES"
					   + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt     (1, fes.getFesNo());
			pstmt.setString  (2, fes.getFesName());
			pstmt.setString  (3, fes.getFesPlace());
			pstmt.setString  (4, fes.getFesTheme());
			pstmt.setDate    (5, (Date) fes.getFesStart());
			pstmt.setDate    (6, (Date) fes.getFesEnd());
			pstmt.setString  (7, fes.getFesTime());
			pstmt.setString  (8, fes.getFesFee());
			pstmt.setString  (9, fes.getFesHost());
			pstmt.setString (10, fes.getFesPhone());
			pstmt.setString (11, fes.getFesAge());
			pstmt.setString (12, fes.getFesAdd());

			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}