package com.ulsan.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ulsan.model.vo.Restaurant;

public class ApiRestaurant {
	public static String key = "DrszgCozJHG41Y9S22XMOKIU5X7mDWkETO%2BR6oUhQNnzdB1Euu7UNEV3XgDLxZoKKgoMS%2FTfkaT%2BgQ8UrteOAA%3D%3D";
	public static String RESTAURANT_XML_URL  = "http://apis.data.go.kr/6310000/ulsanrestaurant/getulsanrestaurantList";
	
	public static void main(String[] args) {
		ApiRestaurant.callRestaurantListByXML();
	}
	
	public static List<Restaurant> callRestaurantListByXML() {
		List<Restaurant> list1 = new ArrayList<>();
		int page = 1; //총 page는 23까지 있다 24부터 null //오류 page : 8, 14, 15
		int numOfRows = 1;
		int cnt = 1; //resNo 번호 줄려고 만든거임
		while(true) {
			if(page == 73 || page == 132 || page == 134 || page == 144) {
				page++;
				continue;
			}
		// 1.URL을 가공하는 코드
		System.out.println();
		System.out.println("pageNumber : " + page);
		StringBuilder urlBuilder = new StringBuilder(RESTAURANT_XML_URL);

		urlBuilder.append("?" + "serviceKey=" + key); /* Service Key */
//		urlBuilder.append("&" + "perPage=" + "1"); /* 페이지 크기(기본20) */
//		urlBuilder.append("&" + "pageIndex=" + "1"); /* 시작 페이지(기본1) */
		urlBuilder.append("&" + "pageNo=" + page);
		urlBuilder.append("&" + "numOfRows=" + numOfRows);
		System.out.println(urlBuilder);
		try {
			// 2. URL을 http 객체를 통해 요청하는 코드
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
//		    conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/xml");

			int code = conn.getResponseCode();
			System.out.println("Response code: " + code);
			if (code < 200 || code >= 300) {
				System.out.println("페이지가 잘못되었습니다.");
				return null;
			}
			// 3. 해석부
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(conn.getInputStream());
			doc.getDocumentElement().normalize();

			System.out.println("Root Element : " + doc.getDocumentElement().getNodeName()); // XML의 최상위 tag값을 가져온다.
			System.out.println("======================="+page+"페이지 시작==============================");
			
			NodeList nList = doc.getElementsByTagName("list");
//			System.out.println(nList.getLength()); // 10?
			for(int j = 0; j < nList.getLength(); j++) {
				Node node = nList.item(j);
				System.out.println("\nCurrent Element : " + node.getNodeName());
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					System.out.println("RES_NAME : "  + eElement.getElementsByTagName("company").item(0).getTextContent());
					System.out.println("RES_TYPE : "  + eElement.getElementsByTagName("foodType").item(0).getTextContent());
					System.out.println("RES_SIGUN : " + eElement.getElementsByTagName("city").item(0).getTextContent());
					System.out.println("RES_ADD : "   + eElement.getElementsByTagName("address").item(0).getTextContent());
					System.out.println("RES_PHONE : " + eElement.getElementsByTagName("phoneNumber").item(0).getTextContent());
					System.out.println("RES_MENU : "  + eElement.getElementsByTagName("mainMenu").item(0).getTextContent());
				
				String resName =  eElement.getElementsByTagName("company").item(0).getTextContent();
				String resType = eElement.getElementsByTagName("foodType").item(0).getTextContent();
				String resSigun = eElement.getElementsByTagName("city").item(0).getTextContent();
				String resAdd = eElement.getElementsByTagName("address").item(0).getTextContent();
				String resPhone = eElement.getElementsByTagName("phoneNumber").item(0).getTextContent();
				String resMenu = eElement.getElementsByTagName("mainMenu").item(0).getTextContent();
				
				Restaurant res = new Restaurant(cnt++, resName, resType, resSigun, resAdd, resPhone, resMenu);
				list1.add(res);
				}
			}
			page++;
			if(page == 229) {
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}return list1;
	}
}