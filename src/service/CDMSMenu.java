/*
 * CDMSMenu.java
 * - 메인 화면 메뉴 출력과 사용자의 메뉴 선택 키보드 입력 받는 클래스
 */
package service;                                        	//CDMSMenu.java 가 속한 패키지:Service

import java.util.Scanner;                                 	//키보드 입력을 위한 Scanner 라이브러리
//--CDMSMenu.java class : 메인 화면 베뉴 출력, 사용자의 매뉴 선택 키보드 입력--------------- 
public class CDMSMenu {
	private final String INVALID_INPUT = "INVALID_INPUT";	//유효하지 않은 입력 위한 상수 
	private String selectedMenuNumber;                    	//키보드로 입력된 선텍 메뉴 번호를 위한 변수 

//----showCDMSMenu(): return void, 메인 메뉴 화면 출력-------------------------------	
	private void showCDMSMenu() {
		System.out.println();
		System.out.println("================");
		System.out.println(" 회원관리프로그램 	"); 
		System.out.println("================");
		System.out.println("1. 회원 추가   	");	//1번 선택: 새로운 회원 추가 기능		
		System.out.println("2. 회원 목록   	");	//2번 선택: 전체 회원 목록 보기 기능
		System.out.println("3. 회원 수정   	");	//3번 선택: 이름으로 검색한 특정 회원의 정보 수정 기능
		System.out.println("4. 회원 삭제   	");	//4번 선택: 이름으로 검색한 특정 회원의 정보 삭제 기능
		System.out.println("5. 종료       	");	//5번 선택: 프로그램 종료
		System.out.print  ("번호 입력 : ");	 	//입력 프롬프트
	}
//----showCDMSMenu(): return void, 메인 메뉴 화면 출력-------------------------------	
	public String selectMenu(Scanner scan) {
		showCDMSMenu();                           	//메인메뉴출력 클래스
		selectedMenuNumber = scan.nextLine();		//scanner로 엔터키 칠때까지 입력한 1줄 받기
		if (selectedMenuNumber.equals("5")){		//입력된 값이 "5"이면
			return selectedMenuNumber;            	//"5" 리턴
		}else if (selectedMenuNumber.equals("4")){	//입력된 값이 "4"이면
			return selectedMenuNumber;            	//"4" 리턴
		}else if (selectedMenuNumber.equals("3")){	//입력된 값이 "3"이면
			return selectedMenuNumber;            	//"3" 리턴
		}else if (selectedMenuNumber.equals("2")){	//입력된 값이 "2"이면
			return selectedMenuNumber;            	//"2" 리턴
		}else if (selectedMenuNumber.equals("1")){	//입력된 값이 "1"이면
			return selectedMenuNumber;            	//"1" 리턴
		}else {                                   	//1~5이외의 입력 값이면
			return INVALID_INPUT;                 	//유효하지 않은 입력임을 리턴
		} //else end
	}//selectMenu() end
//----showCDMSMenu(): return void END---------------------------------------------	
}
//--CDMSMenu class END------------------------------------------------------------
