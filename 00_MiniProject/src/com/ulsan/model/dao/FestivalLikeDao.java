package com.ulsan.model.dao;

import static com.ulsan.common.JDBC.close;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ulsan.model.vo.FesLike;

public class FestivalLikeDao {
	
	public List<FesLike> selectAll(Connection conn, int userNum) {
		List<FesLike> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT FL.LIKE_NO, FL.USER_NO, FL.FES_NO, "
					   + "F.FES_NAME, F.FES_PLACE, F.FES_START, F.FES_END, "
					   + "F.FES_PHONE, F.FES_ADD "
					   + "FROM TBL_FES_LIKE FL "
					   + "LEFT JOIN TBL_FESTIVAL F ON FL.FES_NO = F.FES_NO "
					   + "WHERE FL.USER_NO = ?"
					   + "ORDER BY LIKE_NO";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				int likeNo = rs.getInt("LIKE_NO");
				int userNo = rs.getInt("USER_NO");
				int fesNo = rs.getInt("FES_NO");
				String fesName	= rs.getString("FES_NAME");
				String fesPlace = rs.getString("FES_PLACE");
				Date   fesStart = rs.getDate("FES_START");
				Date   fesEnd 	= rs.getDate("FES_END");
				String fesPhone = rs.getString("FES_PHONE");
				String fesAdd = rs.getString("FES_ADD");
				FesLike fesLikeInfo = new FesLike (likeNo, userNo, fesNo, 
									  fesName, fesPlace, fesStart, fesEnd,
									  fesPhone, fesAdd);
				list.add(fesLikeInfo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int fesLikeInsert(Connection conn, int userNo, int fesNo) {
		try {
			String sql = "INSERT INTO TBL_FES_LIKE(LIKE_NO, USER_NO, FES_NO) "
					   + "VALUES((SELECT NVL(MAX(LIKE_NO), 0) + 1 "
					   + "FROM TBL_FES_LIKE), ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, fesNo);
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int fesLikeDeleteOne(Connection conn, int likeNo) {
		try {
			String sql = "DELETE FROM TBL_FES_LIKE WHERE LIKE_NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, likeNo);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int fesLikeDeletAll(Connection conn) {
		try {
			String sql = "DELETE FROM TBL_FES_LIKE";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}