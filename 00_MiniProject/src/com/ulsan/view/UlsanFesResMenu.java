package com.ulsan.view;

import java.util.List;
import com.ulsan.common.Util;
import com.ulsan.controller.FesLikeController;
import com.ulsan.controller.FestivalController;
import com.ulsan.controller.ResLikeController;
import com.ulsan.controller.RestaurantController;
import com.ulsan.controller.UserController;
import com.ulsan.model.vo.FesLike;
import com.ulsan.model.vo.Festival;
import com.ulsan.model.vo.ResLike;
import com.ulsan.model.vo.Restaurant;
import com.ulsan.model.vo.User;

public class UlsanFesResMenu {
	
	private UserController userController = new UserController();
	private RestaurantController restaurantcontroller = new RestaurantController();
	private FestivalController festivalController = new FestivalController();
	private ResLikeController resLikeController = new ResLikeController();
	private FesLikeController fesLikeController = new FesLikeController();
	
	public void mainMenu() {
		boolean isLogin = false;
		String menu = "■■■■■■■■■■■■■■■■■■ 울산의 축제와 맛집 ■■■■■■■■■■■■■■■■■■\n"
					+ "1. 내 찜 관리\n"
					+ "2. 맛집검색\n" 
					+ "3. 축제검색\n"
					+ "9. 로그아웃\n"
					+ "0. 프로그램 끝내기\n"
					+ "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"
					+ "선택 : "; 
		
		while(true) {
			if(isLogin == false) {
				loginMenu();
				isLogin = true;
			}
			System.out.print(menu);
			int choice = Util.readIntForConsol();
			switch(choice) {
				case 1 :
					LikeMenu();
					break;
				case 2 :
					RestaurantSearchMenu();
					break;
				case 3 :
					FestivalSearchMenu();
					break;
				case 9 :
					while(true) {
						System.out.print("정말로 로그아웃 하시겠습니까?(y/n) : ");
						String logOut = Util.readStrForConsol().toUpperCase();
						if(logOut.toUpperCase().equals("Y")) {
							isLogin = false;
							UserController.logout();
							System.out.println("로그아웃 되었습니다.\n");
							break;
						}else if(logOut.toUpperCase().equals("N")){
							System.out.println("돌아갑니다!");
							break;
						}else if (!logOut.toUpperCase().equals("Y") && !logOut.toUpperCase().equals("N")) {
							System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
						}
					}
					break;
				case 0 :
					while(true) {
						System.out.print("정말로 종료 하시겠습니까?(y/n) : ");
						String close = Util.readStrForConsol().toUpperCase();
						if(close.toUpperCase().equals("Y")) {
							System.out.println("종료합니다.");
							return;
						}else if(close.toUpperCase().equals("N")) {
							System.out.println("돌아갑니다!");
							break;
						}else if (!close.toUpperCase().equals("Y") && !close.toUpperCase().equals("N")) {
							System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
						}
					}
					break;
				default: 
					System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void loginMenu() {
		while(true) {
			System.out.println("■■■■■■■■■■■■■■■■■■ 로그인 ■■■■■■■■■■■■■■■■■■\n");
			System.out.print("ID : ");
			String userId = Util.readStrForConsol();
			System.out.print("PW : ");
			String userPw = Util.readStrForConsol();
			boolean result = userController.login(userId, userPw);
			if(result) {
				System.out.println("로그인에 성공하였습니다!");
				break;
			}else {
				System.out.println("로그인에 실패하였습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void RestaurantSearchMenu() {
		String resMenu = "■■■■■■■■■■■■■■■■■■■■■ 울산의 맛집 ■■■■■■■■■■■■■■■■■■■■■\n"
						 + "1. 지역별 검색\n"
						 + "2. 메뉴별 검색\n"
						 + "3. 식당명 검색\n"
						 + "4. 업태별 검색 (한식, 분식, 일식, 경양식 등)\n"
						 + "0. 이전 메뉴로 돌아가기\n"
						 + "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"
						 + "선택 : ";
		while(true) {
			List<Restaurant> list = null;
			User user = UserController.getLoginUser();
			System.out.println(resMenu);
			int choice = Util.readIntForConsol();

			if(choice > 4) {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				continue;
			}else if(choice == 0) {
				break;
			}
			
			System.out.print("검색어를 입력해주세요 : ");
			String keyword = Util.readStrForConsol();
			list = restaurantcontroller.search(choice, keyword);
			
			if (list.size() > 0) {
				for (Restaurant restaurant : list) {
					System.out.println(restaurant.toString());
				} 		
			} else {
				System.out.println("찾으시는 맛집이 없습니다.");
			}
			
			if(choice == 1) {
				while(true) {
					System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□\n");
					System.out.print("같은 지역의 축제 보기 (y/n) : ");
					String yn = Util.readStrForConsol();
					
					if(yn.toUpperCase().equals("Y")) {
						List<Festival> sameAreaList = null;
						sameAreaList = festivalController.search(choice, keyword);
						
						if(sameAreaList.size() > 0 ) {
							for (Festival festival : sameAreaList) {
								System.out.println(festival.toString());
							}
							break;
						}else {
							System.out.println("같은 지역의 축제가 없습니다.");
						}
					}else if (yn.toUpperCase().equals("N")) {
						break;
					}else if (!yn.toUpperCase().equals("Y") && !yn.toUpperCase().equals("N")) {
						System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
					}
				}
			}
			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
			System.out.println("9. 맛집 찜하기");
			System.out.println("0. 맛집 검색으로 돌아가기");
			System.out.print("선택 : ");
			int like = Util.readIntForConsol();
			if(like == 9) {
				while(true) {
					int userNo = user.getUserNo();
					System.out.print("찜할 식당 번호를 입력해주세요 : ");
					int resLikeNo = Util.readIntForConsol();
					
					boolean result = resLikeController.setResLike(userNo, resLikeNo);
					if (result == true) {
						System.out.println(resLikeNo + "번의 맛집 찜♥");
						System.out.println("1. 더 찜하기");
						System.out.println("0. 맛집 검색으로 돌아가기");
						System.out.print("선택 : ");
						int choice1 = Util.readIntForConsol();
						if(choice1 == 0) {
							break;
						}
					}else {
						System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
					}
				}
			} 
		}
	}
	
	public void FestivalSearchMenu() {
		String fesMenu = "■■■■■■■■■■■■■■■■■■■■■ 울산의 축제 ■■■■■■■■■■■■■■■■■■■■■\n"
					   + "1. 지역별 검색\n"
					   + "2. 키워드 검색\n"
					   + "3. 테마별 검색\n"
					   + "4. 일정별 검색\n"
					   + "0. 이전 메뉴로 돌아가기\n"
					   + "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"
					   + "선택 : ";
		while(true) {
			List<Festival> list = null;
			User user = UserController.getLoginUser();
			System.out.println(fesMenu);
			int choice = Util.readIntForConsol();

			if(choice > 4) {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
				continue;
			}else if(choice == 0) {
				break;
			}
			
			System.out.print("검색어를 입력해주세요 : ");
			String keyword = Util.readStrForConsol();

			list = festivalController.search(choice, keyword);
			
			if (list.size() > 0) {
				for (Festival festival : list) {
					System.out.println(festival.toString());
				} 
			} else {
				System.out.println("찾으시는 축제가 없습니다.");
			}
			
			if(choice == 1) {
				while(true) {
					System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
					System.out.print("같은 지역의 맛집 보기 (y/n) : ");
					String yn = Util.readStrForConsol();
					
					if(yn.toUpperCase().equals("Y")) {
						List<Restaurant> sameAreaList = null;
						sameAreaList = restaurantcontroller.search(choice, keyword);
						
						if(sameAreaList.size() > 0 ) {
							for (Restaurant restaurant : sameAreaList) {
								System.out.println(restaurant.toString());
							}
							break;
						}else {
							System.out.println("같은 지역의 맛집이 없습니다.");
						}
					}else if (yn.toUpperCase().equals("N")) {
						break;
					}else if (!yn.toUpperCase().equals("Y") && !yn.toUpperCase().equals("N")) {
						System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
					}
				}
			}
			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
			System.out.println("9. 축제 찜하기");
			System.out.println("0. 축제 검색으로 돌아가기");
			System.out.print("선택 : ");
			int like = Util.readIntForConsol();
			if(like == 9) {
				while(true) {
					int userNo = user.getUserNo();
					System.out.print("찜할 축제 번호를 입력해주세요 : ");
					int fesLikeNo = Util.readIntForConsol();
					
					boolean result = fesLikeController.setFesLike(userNo, fesLikeNo);
					if (result == true) {
						System.out.println(fesLikeNo + "번의 축제 찜♥");
						System.out.println("1. 더 찜하기");
						System.out.println("0. 축제 검색으로 돌아가기");
						System.out.print("선택 : ");
						int choice1 = Util.readIntForConsol();
						if(choice1 == 0) {
							break;
						}
					}else {
						System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
					}
				}
			} 
		}
	}
	
	public void LikeMenu() {
		String likeMenu = "■■■■■■■■■■■■■■■■■■■■■■■ 찜하기 ■■■■■■■■■■■■■■■■■■■■■■\n"
						+ "1. 맛집 찜\n"
						+ "2. 축제 찜\n"
						+ "0. 이전 메뉴로 돌아가기\n"
						+ "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"
						+ "선택 : ";
		while(true) {
			System.out.println(likeMenu);
			int choice = Util.readIntForConsol();
			
			switch (choice) {
				case 1 :
					resLikeMenu();
					break;
				case 2 :
					fesLikeMenu();
					break;
				case 0 : 
					return;
				default : 
					System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void resLikeMenu() {
		User user = UserController.getLoginUser();
		String resLikeMenu = "■■■■■■■■■■■■■■■■■■■■■■ 맛집 찜 ■■■■■■■■■■■■■■■■■■■■■■\n"
						   + "1. 맛집 찜 해제\n"
						   + "2. 내 맛집 찜목록 전체보기\n"
						   + "3. 내 맛집 찜목록 전체삭제\n"
						   + "0. 이전 메뉴로 돌아가기\n"
						   + "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"
						   + "선택 : ";
		List<ResLike> list = null;
		while(true) {
			System.out.println(resLikeMenu);
			int choice = Util.readIntForConsol();
			
			switch (choice) {
			case 1 : 
				while(true) {
					System.out.print("찜 해제할 맛집을 선택해주세요 : ");
					int resDisLike = Util.readIntForConsol();
					
					boolean resLikedeleteOne = resLikeController.deleteResLikeOne(list.get(resDisLike-1).getLikeNo());
					if (resLikedeleteOne == true) {
						System.out.println(resDisLike + "번의 맛집 찜 해제가 완료되었습니다.");
						System.out.println("1. 더 삭제하기");
						System.out.println("0. 찜 관리로 돌아가기");
						System.out.print("선택 : ");
						int choice1 = Util.readIntForConsol();
						if(choice1 == 0) {
							break;
						}
					}else {
						System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
					}
				}
				break;
			case 2 :
				list = resLikeController.getResLikeAll(user.getUserNo());
				if(list.isEmpty()) {
					System.out.println("찜한 맛집이 없습니다.");
					break;
				}
				int idx = 1;
				for (ResLike resLike : list) {
					System.out.println(idx +"번 "+ resLike.resLikeInfo());
					idx++;
				}
				break;
			case 3 :
				boolean resLikeDeleteAll = resLikeController.deleteResLikeAll();
				if (resLikeDeleteAll == true) {
					System.out.println("찜한 맛집이 전체 삭제되었습니다.");
					break;
				} else {
					System.out.println("삭제할 맛집이 없습니다.");
					break;
				}
			case 0 :
				return;
			default : 
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void fesLikeMenu() {
		User user = UserController.getLoginUser();
		String fesLikeMenu = "■■■■■■■■■■■■■■■■■■■■■■ 축제 찜 ■■■■■■■■■■■■■■■■■■■■■■\n"
						   + "1. 축제 찜 해제\n"
						   + "2. 내 축제 찜목록 전체보기\n"
						   + "3. 내 축제 찜목록 전체삭제\n"
						   + "0. 이전 메뉴로 돌아가기\n"
						   + "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n"
						   + "선택 : ";
		
		List<FesLike> list = null;
		while(true) {
			System.out.println(fesLikeMenu);
			int choice = Util.readIntForConsol();
			
			switch (choice) {
			case 1 : 
				while(true) {
					System.out.print("찜 해제할 축제를 선택해주세요 : ");
					int fesDisLike = Util.readIntForConsol();
					
					boolean fesLikedeleteOne = fesLikeController.deleteFesLikeOne(list.get(fesDisLike-1).getLikeNo());
					if (fesLikedeleteOne == true) {
						System.out.println(fesDisLike + "번의 축제 찜 해제가 완료되었습니다.");
						System.out.println("1. 더 삭제하기");
						System.out.println("0. 찜 관리로 돌아가기");
						System.out.print("선택 : ");
						int choice1 = Util.readIntForConsol();
						if(choice1 == 0) {
							break;
						}
					}else {
						System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
					}
				}
				break;
			case 2 :
				list = fesLikeController.getFesLikeAll(user.getUserNo());
				if(list.isEmpty()) {
					System.out.println("찜한 축제가 없습니다.");
					break;
				}
				int idx = 1;
				for (FesLike fesLike : list) {
					System.out.println(idx +"번 "+ fesLike.fesLikeInfo());
					idx++;
				}
				break;
			case 3 :
				boolean fesLikeDeleteAll = fesLikeController.deleteFesLikeAll();
				if (fesLikeDeleteAll == true) {
					System.out.println("찜한 축제가 전체 삭제되었습니다.");
					break;
				} else {
					System.out.println("삭제할 축제가 없습니다.");
					break;
				}
			case 0 :
				return;
			default : 
				System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			}
		}
	}
}