package com.ulsan.controller;

import java.util.List;
import com.ulsan.model.service.FesLikeService;
import com.ulsan.model.vo.FesLike;

public class FesLikeController {
	
	FesLikeService fesLikeService = new FesLikeService();
	
	public List<FesLike> getFesLikeAll(int userNo) {
		return fesLikeService.selectAll(userNo);
	}
	
	public boolean setFesLike(int userNo, int fesNo) {
		int result = fesLikeService.fesLikeInsert(userNo, fesNo);
		return result > 0 ? true : false;
	}
	
	public boolean deleteFesLikeOne(int likeNo) {
		int result = fesLikeService.fesLikeDeletOne(likeNo);
		return result > 0 ? true : false;
	}
	
	public boolean deleteFesLikeAll() {
		int result = fesLikeService.fesLikeDeleteAll();
		return result > 0 ? true : false;
	}
}