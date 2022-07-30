package com.ulsan.model.dao;

import static com.ulsan.common.JDBC.close;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ulsan.model.vo.ResLike;

public class RestaurantLikeDao {
	
	public List<ResLike> selectAll(Connection conn, int userNum) {
		List<ResLike> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT RL.LIKE_NO, RL.USER_NO, RL.RES_NO, R.RES_NAME, "
					   + "R.RES_TYPE, R.RES_ADD, R.RES_PHONE, R.RES_MENU "
					   + "FROM TBL_RES_LIKE RL "
					   + "LEFT JOIN TBL_RESTAURANT R ON RL.RES_NO = R.RES_NO "
					   + "WHERE RL.USER_NO = ?"
					   + "ORDER BY LIKE_NO";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				int likeNo = rs.getInt("LIKE_NO");
				int userNo = rs.getInt("USER_NO");
				int resNo = rs.getInt("RES_NO");
				String resname = rs.getString("RES_NAME"); 
				String restype = rs.getString("RES_TYPE");
				String resadd = rs.getString("RES_ADD"); 
				String resphone = rs.getString("RES_PHONE");
				String resmenu = rs.getString("RES_MENU"); 
				ResLike resLikeInfo = new ResLike(likeNo, userNo, resNo, resname, 
									  restype, resadd, resphone, resmenu);
				list.add(resLikeInfo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int resLikeInsert(Connection conn, int userNo, int resNo) {
		try {
			String sql = "INSERT INTO TBL_RES_LIKE(LIKE_NO, USER_NO, RES_NO) "
					   + "VALUES((SELECT NVL(MAX(LIKE_NO), 0) + 1 "
					   + "FROM TBL_RES_LIKE), ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, resNo);
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int resLikeDeleteOne(Connection conn, int likeNo) {
		try {
			String sql = "DELETE FROM TBL_RES_LIKE WHERE LIKE_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, likeNo);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int resLikeDeletAll(Connection conn) {
		try {
			String sql = "DELETE FROM TBL_RES_LIKE";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}