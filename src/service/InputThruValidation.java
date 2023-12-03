/*
 * InputThruValidation.java
 * 키보드로 입력받는 각 회원 정보 항목들의 유효성을 검사하여 형식에 맞는 데이터가 입력되도록 유도한다
 * - 이름 name: 1자이상 문자열 
 * - 전화번호 phoneNumber: 숫자로만 이루어진 문자열
 * - 시도 주소 sidoId: 행안부 시도주소 분류에 의한 17개 시도중 하나를 선택, 선택 입력은 1~17 사이 숫자만 허용
 * 					, 리턴은 2자리 숫자로된 행안부 공식 시도 코드 문자열로 리턴     	
 * - 상세 주소 address: 문자열, 공백허용
 * - 회원 그룹 moimId: 회원 그룹 4개중 하나 선택, 1~4 숫자만 허용
 * - 숫자인지 판별 isNumeric: 숫자로만 이루어진 문자열 인지 판별, 숫자이면 true, 아니면 false 리턴 
 */
package service;         				//InputThruValidation.java 가 속한 패키지:Service

import java.util.Scanner;				//키보드 입력 받기위한 Scanner 라이브러리

//---InputThruValidation.java class --------------------------------------------
public class InputThruValidation {
	
//----filterName(): return String,  이름은 공백 입력 안됨-------------------------	
	public static String filterName(Scanner scan) {
		String newName;		       	  	//키보드 입렫받은 이름 문자열
		while(true){	           	   	//유효한 입력 있을 때 까지 무한 반족
			System.out.print("이름: "); 	//입력 프롬프트
			newName = scan.nextLine();	//scanner로 엔터키 칠때까지 입력한 1줄 받기
			if(newName.isEmpty()) {   	//공백, 아무것도 안 썼으면 다시 입력
				System.out.println("이름은 1자 이상 입력해 주세요. 다시 입력하세요.");
				continue;             	//while 나가지 말고 반복
			}
			break;                    	//입력 있으면 while 나가기
		}
		return newName;   	           	//입력 받은 이름 문자열 리턴
	}
//----filterName(): return String END---------------------------------------	
	
//----filterPhoneNumber(): return String, 전화번호는 숫자만 허용-------------------	
	public static String filterPhoneNumber(Scanner scan) {
		String newPhoneNumber;         	  		//키보드 입렫받은 이름 문자열
		while(true) {              	    		//유효한 입력 있을 때 까지 무한 반족
			System.out.print("전화번호(ex:01012345678): "); //입력 프롬프트
			newPhoneNumber = scan.nextLine();	//scanner로 엔터키 칠때까지 입력한 1줄 받기
			if(!(isNumeric(newPhoneNumber))) {	//공백이거나 숫자 아닌 것이 들어오면 다시 입력
				System.out.println("전화번호는 숫자로 입력해 주세요. 다시 입력하세요.");
				continue;                    	//while 나가지 말고 반복
			}
			break;                            	//유효한 입력 있으면 while 나가기
		}
		return newPhoneNumber;    	           	//입력 받은 전화번호 문자열 리턴
	}
//----filterPhoneNumber(): return String END---------------------------------
	
//----filterDidoId(): return String 시도 주소 id를 1~17 번호로 입력----------------
//----               공백 안되고, 1~17 사이 숫자만 허용, 아니면 반복--------------------
	public static String filterSidoId(Scanner scan) {
		String newSidoNumber="";    			//1~17 사이의 숫자 선택을 위한 변수
		String newSidoCode="";      			//행안부 전국 시도 분류 코드 위한 변수
		while(true) {         	              	//유효한 입력 있을 때 까지 무한 반복
			System.out.println("다음중 주소(시도)를 선택해 주세요."); //1~17 시도 메뉴 출력
			System.out.println("  1. 서울 | 2. 부산 | 3. 대구 | 4. 인천 | 5. 광주 | 6. 대전 | 7. 울산 | 8. 세종 | 9. 경기");
			System.out.println(" 10. 강원 |11. 충북 |12. 충남 |13. 전북 |14. 전남 |15. 경북 |16. 경남 |17. 제주 ");
			System.out.print("(ex: 서울 -> 1 ) : "); //입력 프롬프트
			newSidoNumber = scan.nextLine();	//scanner로 엔터키 칠때까지 입력한 1줄 받기
			if (newSidoNumber.isEmpty()) {  	//공백 안됨
				continue;                   	//공백시 다시 입력
			}
			else if(!(isNumeric(newSidoNumber))){ 	//숫자만 허용
				continue;                    	//숫자 아니면 다시 입력
			}else if(!(Integer.parseInt(newSidoNumber)>0 && Integer.parseInt(newSidoNumber)<18)) {
				continue;                    	//1~17 사이 번호가 아니면, 다시 입력
			} else {
				break;                       	//유효한 입력이면 while 나가기
			}
		}
		if(newSidoNumber.equals("1")) newSidoCode ="11";	//행안부 시도 표준 코드: 서울 11
		if(newSidoNumber.equals("2")) newSidoCode ="21";	//행안부 시도 표준 코드: 부산 21
		if(newSidoNumber.equals("3")) newSidoCode ="22";	//행안부 시도 표준 코드: 대구 22
		if(newSidoNumber.equals("4")) newSidoCode ="23";	//행안부 시도 표준 코드: 인천 23
		if(newSidoNumber.equals("5")) newSidoCode ="24";	//행안부 시도 표준 코드: 광주 24
		if(newSidoNumber.equals("6")) newSidoCode ="25";	//행안부 시도 표준 코드: 대전 25
		if(newSidoNumber.equals("7")) newSidoCode ="26";	//행안부 시도 표준 코드: 울산 26
		if(newSidoNumber.equals("8")) newSidoCode ="29";	//행안부 시도 표준 코드: 세종 29
		if(newSidoNumber.equals("9")) newSidoCode ="31";	//행안부 시도 표준 코드: 경기 31
		if(newSidoNumber.equals("10")) newSidoCode ="32";	//행안부 시도 표준 코드: 강원 32
		if(newSidoNumber.equals("11")) newSidoCode ="33";	//행안부 시도 표준 코드: 충북 33
		if(newSidoNumber.equals("12")) newSidoCode ="34";	//행안부 시도 표준 코드: 충남 34
		if(newSidoNumber.equals("13")) newSidoCode ="35";	//행안부 시도 표준 코드: 전북 35
		if(newSidoNumber.equals("14")) newSidoCode ="36";	//행안부 시도 표준 코드: 전남 36
		if(newSidoNumber.equals("15")) newSidoCode ="37";	//행안부 시도 표준 코드: 경북 37
		if(newSidoNumber.equals("16")) newSidoCode ="38";	//행안부 시도 표준 코드: 경남 39
		if(newSidoNumber.equals("17")) newSidoCode ="39";	//행안부 시도 표준 코드: 제주 39

		return newSidoCode;                               	//행안부 시도 표준 코드 리턴
	}
//----filterSidoId(): return String END-------------------------------------------
	
//----filetrAddress(): return String, 회원 상세주소를 문자열로 자유입력, 공백 허용------------
	public static String filterAddress(Scanner scan) {
		System.out.print("상세 주소: ");       	//입력 프롬프트
		String newAddress = scan.nextLine();	//scanner로 엔터키 칠때까지 입력한 1줄 받기
		return newAddress;                  	//회원 상세 주소 문자열 리턴
	}
//----filetrAddress(): return String END------------------------------------------

//----filterMoimId(): return String, 회원 그룹 id를 1~4의 숫자로 입력--------------------
//----              공백 안되고 1~4 숫자만 허용, 아니면 반복 -------------------------------
	public static String filterMoimId(Scanner scan) {
		String newMoimId="";             	   	//회원 그룹id 저장 위한 변수
		while(true) {              	   	    	//유효한 입력 있을 때 까지 무한 반복				
			System.out.println("다음중 회원 그룹을 선택해 주세요.");	//1~4 회원 그룹 메뉴 출력
			System.out.println("  1. 가족 | 2. 친구 | 3. 회사 | 4. 기타");
			System.out.print("(ex: 가족 -> 1 ) : "); //입력 프롬프트
			newMoimId = scan.nextLine();      	//scanner로 엔터키 칠때까지 입력한 1줄 받기
			if (newMoimId.isEmpty()) {        	//공백 안됨
				continue;                     	//공백시 다시 입력
			}
			else if(!(isNumeric(newMoimId))){	//숫자만 허용
				continue;                    	//숫자 아니면 다시 입력
			}else if(!(Integer.parseInt(newMoimId)>0 && Integer.parseInt(newMoimId)<5)) {
				continue;                    	//1~4 사이의 숫자 아니면, 다시 입력
			}else {
				break;                       	//유효한 입력이면 while 나가기
			}
		}
		return newMoimId; 	                  	//회원 그룹 id 문자열 리턴
	}
//----filterMoimId(): return String, END------------------------------------------
	
//----isNumeric(): return boolean, 파라미터 문자열이 숫자로만 이루어졌으면 true, 아니면 false---	
	public static boolean isNumeric(String input) {
	    try {
	        Double.parseDouble(input);       	//입력 문자열을 더블형으로 파싱
	        return true;                    	//성공하면 숫자만 있음, true 리턴
	    }
	    catch (NumberFormatException e) {    	//예외발생, 숫자아니면
	        return false;                    	//false 리턴
	    }
	}
//----isNumeric(): return boolean END----------------------------------------------	

}
//---InputThruValidation.java class END------------------------------------------------
