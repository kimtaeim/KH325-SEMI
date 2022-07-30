package com.ulsan.controller;

import java.util.List;
import com.ulsan.api.ApiRestaurant;
import com.ulsan.model.service.RestaurantService;
import com.ulsan.model.vo.Restaurant;

public class RestaurantController {
	
	RestaurantService resService = new RestaurantService();
	
	public void getDataRestaurant() {
		List<Restaurant> list = ApiRestaurant.callRestaurantListByXML();
		
		for (int i = 0; i < list.size(); i++) {
			resService.insertRestaurantData(list.get(i));
		}
	}
	
	public List<Restaurant> selectAll() {
		return resService.selectAll();
	}
	
	public List<Restaurant> search(int searchType, String keyword) {
		return resService.search(searchType, keyword);
	}
}