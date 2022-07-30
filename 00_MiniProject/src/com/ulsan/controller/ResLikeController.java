package com.ulsan.controller;

import java.util.List;
import com.ulsan.model.service.ResLikeService;
import com.ulsan.model.vo.ResLike;

public class ResLikeController {
	
	ResLikeService resLikeService = new ResLikeService();
	
	public List<ResLike> getResLikeAll(int userNo) {
		return resLikeService.selectAll(userNo);
	}
	
	public boolean setResLike(int userNo, int resNo) {
		int result = resLikeService.resLikeInsert(userNo, resNo);
		return result > 0 ? true : false;
	}
	
	public boolean deleteResLikeOne(int likeNo) {
		int result = resLikeService.resLikeDeletOne(likeNo);
		return result > 0 ? true : false;
	}
	
	public boolean deleteResLikeAll() {
		int result = resLikeService.resLikeDeleteAll();
		return result > 0 ? true : false;
	}
}