/*
 * InputContactData.java
 * 회원 1명의 정보를 키보드로 입력 받기 위한 클래스
 * 회원 1명다 키보드 입력 받을 정보: 5항목
 * 1. 새이름 newName
 * 2. 새시도주소id newSidoId
 * 3. 새상세주소	newAddress
 * 4. 새전화번호	newPhoneNumber
 * 5. 색회원그룹id	newMoiId
 */
package service;                           	//InputContactData.java 가 속한 패키지:Service

import java.util.Scanner;                  	//키보드 입력을 받기위한 Scanner 라이브러이
import dto.ContactDto;                     	//DTO-회원정보 저장을 위한 ContactDto클래스 
//--InputContactData.java class : 키보드로 회원 정보 5개 항목 입력 받는 class---------------
public class InputContactData {
	private static String 	newName;		//키보드로 입력 받을 새이름 위한 변수
	private static String 	newSidoId;		//키보드로 입력 받을 새시도주소id 위한 변수
	private static String 	newAddress;		//키보드로 입력 받을 새상세주소 위한 변수
	private static String 	newPhoneNumber;	//키보드로 입력 받을 새전화번호 위한 변수
	private static String	newMoimId;		//키보드로 입력 받을 새회원그룸id 위한 변수
	
//----getNewContact(): return ContactDto, 키보드로 새로운 회원정보 5항목을 입력 받기---------
	public static ContactDto getNewContact(Scanner scan) {
		
		System.out.println("등록할 회원 정보를 입력하세요");       	    	//새로운 회원 정보 입력 안내 출력
		newName = InputThruValidation.filterName(scan);            		//새이름 입력
		newPhoneNumber=InputThruValidation.filterPhoneNumber(scan);		//새전화번호 입력
		newSidoId = InputThruValidation.filterSidoId(scan);        		//새시도주소id 입력
		newAddress = InputThruValidation.filterAddress(scan);     		//새상세주소 입력
		newMoimId = InputThruValidation.filterMoimId(scan);       		//새회원그룹id 입력
		
		// 위 입력 받은 5개 회원 정보 항목을 ContactDto 에 담아서 리턴
		return new ContactDto(newName,newPhoneNumber,newSidoId,newAddress,newMoimId);
	}
//----getNewContact(): return ContactDto END--------------------------------------
	
//----getNewContactOfName(): return ContactDto,이름 주어진 회원의 정보 수정을 위한 입력 받기--
	public static ContactDto getNewContactOfName(String name,Scanner scan) {
		
		System.out.println("선택한 "+name+"의 새로운 회원 정보를 입력하세요"); //이름 주어진 회원 새로운 정보 입력 안내
		newName = InputThruValidation.filterName(scan);                	//새이름 입력
		newPhoneNumber=InputThruValidation.filterPhoneNumber(scan);	   	//새전화번호 입력
		newSidoId = InputThruValidation.filterSidoId(scan);           	//새시도주소id 입력
		newAddress = InputThruValidation.filterAddress(scan);  	     	//새상세주소 입력
		newMoimId = InputThruValidation.filterMoimId(scan);            	//새회원그룹id 입력
		
		// 위 입력 받은 5개 회원 정보 항목을 ContactDto 에 담아서 리턴
		return new ContactDto(newName,newPhoneNumber,newSidoId,newAddress,newMoimId);
	}
//----getNewContactOfName(): return ContactDto END--------------------------------------
}
//--InputContactData() class END--------------------------------------------------------
