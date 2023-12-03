/*
 * ContactDataManagementSystem.java
 * - 회원 연락처 관리 시스템 
 * - main()
 * - 1.회원 추가: 키보드로 회원정보 입력 받아 DB에 추가
 * - 2.회원 목록: DB에 저장된 모든 회원 정보 출력
 * - 3.회원 수정: 이름으로 검색하여 선택한 해당 회원의 정보를 새로 입력 받아 수정
 * - 4.회원 삭제: 이름을로 검색하여 선택한 해당 회안의 정보를 삭제
 * - 5.종료: 프로그램 종료
 */ 
package controller;                                	//ContactDataManagementSystem.java 속한 패키지:controller
import java.util.Scanner;                          	//키보드입력 Scanner 라이브러리                                	                      
import service.CDMSMenu;                          	//메인 메뉴 출력
import dao.InsertNewContact;                       	//회원 추가 클래스
import service.ViewAllContacts;                    	//회원 목록 클래스
import dao.EditContacts;                           	//회원 수정 클래스
import dao.DeleteContacts;                        	//회원 삭제 클래스
//--ContactDataManagementSystem.java class, 회원 연락처 관리 시스템 main()-----------------------------------
public class ContactDataManagementSystem {
//----main() method: return void ---------------------------------------------------------------------
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);             		//키보드 메뉴 번호 선택 입력 받는 scanner
		String selectedMenu;                               		//1~5 사이 선택한 메뉴 번호
		
		while(true) {                                      		//5 종료 선택하기 전까지 무한 반복	
			selectedMenu = new CDMSMenu().selectMenu(scan);		//메인 메뉴 화면 출력
			if(selectedMenu.equals("5")) {                 		//5: 종료 선택
				System.out.println("종료되었습니다");            	//종료전 메시지 출력
				scan.close();                               	//키보드 입력 scanner close
				return;                                     	//프로그램 종료
			}else if(selectedMenu.equals("INVALID_INPUT")){		//1~5 숫자 이외의 입력은 무시
				System.out.println("번호를 선택해 주세요.");      	//유효한 메뉴 번호 선택 유도
			}else if(selectedMenu.equals("1")) {           		//1: 회원 입력 선택
				new InsertNewContact().insertNew(scan);      	//회원 추가 클래스
			}else if(selectedMenu.equals("2")) {           		//2: 회원 몰록 선택
				new ViewAllContacts().viewAllContacts();      	//회원 목록 클래스 			
			}else if(selectedMenu.equals("3")) {           		//3: 회원 수정 선택
				new EditContacts().editContact(scan);       	//회원 수정 클래스
			}else if(selectedMenu.equals("4")) {           		//4. 회원 삭제 선택
				new DeleteContacts().removeContact(scan);      	//회원 삭제 클래스
			}
		}
	}
//----main() method: return void END------------------------------------------------------------------
}
//--ContactDataManagementSystem.java class END---------------------------------------------------------
