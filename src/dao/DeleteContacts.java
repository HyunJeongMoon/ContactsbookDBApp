/*
 * DeleteContacts.java
 * 1) 회원 "이름" 입력 받아서 같은 이름(글자) 들어있는 회원들을 검색한다
 * 2) 삭제하고자 하는 회원의 일련번호를 받아서 해당회원의 새로운 정보를 삭제한다
 */
package dao;                                         	//DeleteContacts.java 속한 패키지: dao

import java.sql.Connection;                           	//DB Connection 라이브러리
import java.sql.PreparedStatement;                     	//SQL 쿼리 실행위한 라이브러리
import java.sql.SQLException;                         	//예외처리위한 라이브러리
import java.util.ArrayList;                           	//여러명 회원정보들을 담을 ArrayList 라이브러리
import dto.ContactDto;                                 	//1명 회원정보 담을 dto 클래스 
import util.DbConnection;                              	//DB 연결 클래스
import java.util.Scanner;                              	//키보드 입력 Scanner 라이브러리
import service.InputThruValidation;                        	//각 항목 유효성 검사 거치는 입력 클래스
//--DeleteContacts.java: 회원 이름 입력받아 검색하고, 검색결과중 삭제하고자 하는 회원의 번호를 선택하면 삭제 DELETE--
public class DeleteContacts {
//----removeContact() method: return void----------------- -----------------------------------	
	public void removeContact(Scanner scan) {
		System.out.println("삭제할 회윈의 이름을 입력하세요");           	//이름 입력 안내 메시지
		String searchName = InputThruValidation.filterName(scan);  		//이름 유효성 검사
		//----이름 searchName으로 DB 검색한 결과를 받아 ArrayList에 담는다
		ArrayList<ContactDto> contactList =	SelectContacts.selectByName(searchName);
		//---- CONTACT 테이블서 회원 이름 NAME에 searchName을 가진 모든 회원정보를 조회
		//---- 이때, 시도주소id와 회원그룹id는 각각 SIDO 테이블과 MOIM 테이블에서 이름으로 조회, 변환
		if(contactList.size()==0) {                            		//ArrayList가 비었을 경우
			System.out.println("해당하는 회원 정보가 없습니다.");        	//해당 회원 없음 메시지 출력
		} else {                                               		//해당 이름 회원 있는 경우
			//----모두 몇 명이 검색되었는지 안내 메시지
			System.out.println("총 "+contactList.size()+"개의 목록이 검색되었습니다.");
			//----어떤 회원의 정보를 삭제할 것인지 출력 일련번호를 선택하도록 안내
			System.out.println("아래 목록중 삭제할 회원의 번호를 입력하세요.");
			for(int i=0; i<contactList.size();i++) {              	//모든 검색 결과를 출력
				System.out.println(contactList.get(i));         	//DB에서 꺼내온 회원정보 1인/1행 출력
			}
			System.out.print("번호: ");                             	//삭제할 회원의 일련번호 입력 프롬프트
			//----DB에서 수행시킬 SQL 쿼리문 저장을 위한 StringBuilder sql 변수
			StringBuilder sql = new StringBuilder();
			Connection conn = DbConnection.getConnction(); 		//connection 해주는 클래스 호출
			PreparedStatement pstmt= null;		              	//SQL 쿼리 실행위한 PreparedStatement 변수       
			sql.append("DELETE                	");          	//DELETE
			sql.append("  FROM CONTACTS c      	");           	//CONTACT 테이블
			sql.append(" WHERE c.CONTACTID = ?	");           	//CONTACTID
			String selectIndex = scan.nextLine();             	//삭제활 회원의 일련번호 키보드 입력
			if (InputThruValidation.isNumeric(selectIndex)) {     	//입력값 유효성 검사: 숫자가 아니거나?
				if ((Integer.parseInt(selectIndex)>=1) &&      	//범위 밖의 숫자인지?
					(Integer.parseInt(selectIndex)<=contactList.size())) {//범위 넘는 잘못된 숫자거나?
					try {	
						//----StringBuilder 타입의 sql을 String으로 변환해서 PreparedStatement에 넣고 DB와 연결
						 pstmt = conn.prepareStatement(sql.toString());
						//쿼리?에 바인딩:contactId
						 pstmt.setString(1, contactList.get(Integer.parseInt(selectIndex)-1).getContactId());
						//----결과를 받지 않는 (DELETE)이므로 executeUpdate() 실행
						 pstmt.executeUpdate();
					} catch (SQLException e) {        	  	       	//DB 연동 예외처리
						e.printStackTrace();                    	//예외 발생시 trace 출력
					} finally{                       	           	//예외 처리 끝에
						DbConnection.close(conn, pstmt);        	//DB 연동 관련 close 실행
					}
					System.out.println("수정이 완료되었습니다.");      	//UPDATE 완료 메시지
				}else {
					System.out.println("잘못된 입력입니다");		      	//잘못된 숫자일때 UPDATE 안하고 리턴
				}
			}else {
					System.out.println("잘못된 입력입니다");  		   	//숫자가 아닌 입력시 UPDATE 안하고 리턴
			}
		}	
		
	}
//----removeContact() method: return void END-------------- -----------------------------------	
}
//--DeleteContacts.java class END---------------------------------------------------------------
