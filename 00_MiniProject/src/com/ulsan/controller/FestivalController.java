package com.ulsan.controller;

import java.util.List;
import com.ulsan.api.ApiFestival;
import com.ulsan.model.service.FestivalService;
import com.ulsan.model.vo.Festival;

public class FestivalController {
	
	FestivalService fesService = new FestivalService();
	
	public void getDataFestival() {
		List<Festival> list = ApiFestival.callFestivalListByXML();
		
		for (int i = 0; i < list.size(); i++) {
			fesService.insertFestivalData(list.get(i));
		}
	}
	
	public List<Festival> selectAll() {
		return fesService.selectAll();
	}
	
	public List<Festival> search(int searchType, String keyword) {
		return fesService.search(searchType, keyword);
	}
}