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
import com.ulsan.model.vo.Festival;

public class ApiFestival {
	
	public static String key = "PCNxso2TMGeSrjPMsBxvOscmyUmaYllFFoSb%2BN%2BjMnDf4gSitSdIZjC3290UCja4ki92iGwbCXZv6utvCT0IAg%3D%3D";
	public static String FESTIVAL_XML_URL = "http://apis.data.go.kr/6310000/ulsanperformanceeventinfo/getUlsanperformanceeventinfoList";

	public static void main(String[] args) {
		System.out.println(ApiFestival.callFestivalListByXML());
	}

	public static List<Festival> callFestivalListByXML() {
		List<Festival> list = new ArrayList<>();
		int page = 1;
		int cnt = 1; //fesNo 번호 줄려고 만든거임
		int numOfRows = 1;
		while(true) {
			if(page == 39 ||page == 91 || page == 122 ||page == 152 || page == 156 ||page == 164 ||
					page == 220 ||page ==229 ||page == 230 ||page == 260 || page == 266 || page == 282 ||
					page == 283 ||page == 284 || page == 310) {
				page++;
				continue;
				}
		// 1.URL을 가공하는 코드
		System.out.println();
		System.out.println("pageNumber : " + page);
		StringBuilder urlBuilder = new StringBuilder(FESTIVAL_XML_URL);

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
				System.out.println("FES_NAME : "  + eElement.getElementsByTagName("eventNm").item(0).getTextContent());
				System.out.println("FES_PLACE : "  + eElement.getElementsByTagName("place").item(0).getTextContent());
				System.out.println("FES_THEME : " + eElement.getElementsByTagName("eventContent").item(0).getTextContent());
				System.out.println("FES_START : "   + eElement.getElementsByTagName("eventStartDate").item(0).getTextContent());
				System.out.println("FES_END : " + eElement.getElementsByTagName("eventEndDate").item(0).getTextContent());
				System.out.println("FES_TIME : "  + eElement.getElementsByTagName("eventTime").item(0).getTextContent());
				System.out.println("FES_FEE : "  + eElement.getElementsByTagName("rateInfo").item(0).getTextContent());
				System.out.println("FES_HOST : "  + eElement.getElementsByTagName("hoseInstitution").item(0).getTextContent());
				System.out.println("FES_PHONE : "  + eElement.getElementsByTagName("phoneNum").item(0).getTextContent());
				System.out.println("FES_AGE : "  + eElement.getElementsByTagName("age").item(0).getTextContent());
				System.out.println("FES_ADD : "  + eElement.getElementsByTagName("roadNameAddress").item(0).getTextContent());

				String fesName =  eElement.getElementsByTagName("eventNm").item(0).getTextContent();
				String fesPlace = eElement.getElementsByTagName("place").item(0).getTextContent();
				String fesTheme = eElement.getElementsByTagName("eventContent").item(0).getTextContent();
				String fesStart = eElement.getElementsByTagName("eventStartDate").item(0).getTextContent();
				String fesEnd = eElement.getElementsByTagName("eventEndDate").item(0).getTextContent();
				String fesTime = eElement.getElementsByTagName("eventTime").item(0).getTextContent();
				String fesFee = eElement.getElementsByTagName("rateInfo").item(0).getTextContent();
				String fesHost = eElement.getElementsByTagName("hoseInstitution").item(0).getTextContent();
				String fesPhone = eElement.getElementsByTagName("phoneNum").item(0).getTextContent();
				String fesAge = eElement.getElementsByTagName("age").item(0).getTextContent();
				String fesAdd = eElement.getElementsByTagName("roadNameAddress").item(0).getTextContent();
				
				// 문자열 -> Date        
				java.sql.Date fesStart_date = java.sql.Date.valueOf(fesStart);
				java.sql.Date fesEnd_date = java.sql.Date.valueOf(fesEnd);
				
				Festival fes = new Festival(cnt++, fesName, fesPlace, fesTheme, fesStart_date, fesEnd_date, fesTime, fesFee, fesHost, fesPhone, fesAge, fesAdd);						
				list.add(fes);
			}
		}
		page++;
		if(page == 313) {
			break;
		}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}