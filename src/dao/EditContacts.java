/* 회원 정보 수정하기: EditContacts.java
* 1) 회원 "이름" 입력 받아서 같은 이름(글자) 들어있는 회원들을 검색한다
* 2) 수정하고자 하는 회원의 일련번호를 받아서 해당회원의 새로운 정보를 입력받는다
* 3) 새로 입력받은 회원 정보가 이미 있는 정보가 아니면 회원정보 DB Contacts 테이블에 UPDATE 한다.
* 4) 이미 있는 정보와 중복되면 메시지 내고 메인으로 돌아간다
*/
package dao;                                         	//SelectContacts.java 속한 패키지: dao

import java.sql.Connection;                           	//DB Connection 라이브러리
import java.sql.PreparedStatement;                     	//SQL 쿼리 실행위한 라이브러리
import java.sql.SQLException;                         	//예외처리위한 라이브러리
import java.util.ArrayList;                           	//여러명 회원정보들을 담을 ArrayList 라이브러리

import dto.ContactDto;                                 	//1명 회원정보 담을 dto 클래스 
import util.DbConnection;                              	//DB 연결 클래스
import java.util.Scanner;                              	//키보드 입력 Scanner 라이브러리
import service.InputContactData;                       	//새로운 회원정보 입력 클래스                             	
import service.InputThruValidation;                        	//각 항목 유효성 검사 거치는 입력 클래스
//--EditContacts.java: 수정할 회원의 이름을 입력받아 검색하고, 해당 회원의 정보를 수정 UPDATE-------------------
public class EditContacts {
//----editContact() method: return void, 수정할 회원의 이름을 입력받아 검색하고, 해당 회원의 정보를 수정 UPDATE--	
	public void editContact(Scanner scan) {
		System.out.println("수정할 회원의 이름을 입력하세요");        	//이름 입력 안내 메시지
		String searchName = InputThruValidation.filterName(scan);	//이름 유효성 검사
		//----이름 searchName으로 DB 검색한 결과를 받아 ArrayList에 담는다
		ArrayList<ContactDto> contactList =	SelectContacts.selectByName(searchName);
		if(contactList.size()==0) {                          	//ArrayList가 비었을 경우
			System.out.println("해당하는 회원 정보가 없습니다.");     	//해당 회원 없음 메시지 출력
		}else {                                               	//해당 이름 회원 있는 경우
			//----모두 몇 명이 검색되었는지 안내 메시지
			System.out.println("총 "+contactList.size()+"개의 목록이 검색되었습니다.");
			//----어떤 회원의 정보를 수정할 것인지 출력 일련번호를 선택하도록 안내
			System.out.println("아래 목록중 수정할 회원의 번호를 입력하세요.");
			for(int i=0; i<contactList.size();i++) {           	//모든 검색 결과를 출력
				System.out.println(contactList.get(i));       	//DB에서 꺼내온 회원정보 1인/1행 출력
			}
			System.out.print("번호: ");                        	//수정할 회원의 일련번호 입력 프롬프트
			String selectIndex = scan.nextLine();             	//수정활 회원의 일련번호 키보드 입력
			if (InputThruValidation.isNumeric(selectIndex)) {     	//입력값 유효성 검사: 숫자가 아니거나?
				if ((Integer.parseInt(selectIndex)>=1) &&      	//범위 밖의 숫자인지?
					(Integer.parseInt(selectIndex)<=contactList.size())) {//범위 넘는 잘못된 숫자거나?
					//----유효한 선택 일렵번호인 경우 새로 넣은 회원정보 유효성검사입력클래스로 입력 받는다
					ContactDto newContact = InputContactData.getNewContactOfName(contactList.get(Integer.parseInt(selectIndex)-1).getName(),scan);
					//----새로 입력 받은 정보와 완전히 일치하는 회원이 DB에 존재하는 지 검사
					if(CheckRedundancy.isRedundantContact(newContact.getName(), newContact.getPhoneNumber(), newContact.getSidoId(), newContact.getAddress(), newContact.getMoimId())) {
						System.out.println("이미 있는 회원 정보 입니다. ");	//중복된 회원 있음 알림
						return;                                     	//회원정보수정 안하고 전단계로 리턴
					}
					//----DB에서 수행시킬 SQL 쿼리문 저장을 위한 StringBuilder sql 변수
					StringBuilder sql = new StringBuilder();
					//---- CONTACT 테이블서 회원 이름 NAME에 searchName을 가진 모든 회원정보를 조회
					//---- 이때, 시도주소id와 회원그룹id는 각각 SIDO 테이블과 MOIM 테이블에서 이름으로 조회, 변환
					Connection conn = DbConnection.getConnction();	//connection 해주는 클래스 호출
					PreparedStatement pstmt= null;                	//SQL 쿼리 실행위한 PreparedStatement 변수       
					sql.append("UPDATE	CONTACTS      		"); //UPDATE CONTACTS 테이블
					sql.append("   SET	NAME =        	?	"); //1.newName
					sql.append("     ,	PHONENUMBER =	?	"); //2.newPhoneNumber
					sql.append("     ,	SIDOID =      	?	"); //3.newSido_id
					sql.append("     ,	ADDRESS =     	?	"); //4.newAddress
					sql.append("     ,	MOIMID =      	?	"); //5.newMoimId
					sql.append(" WHERE  CONTACTID =   	?	"); //6.contactList.get(Integer.parseInt(selectIndex)-1).getContact_id()
					try {
						//----StringBuilder 타입의 sql을 String으로 변환해서 PreparedStatement에 넣고 DB와 연결
						pstmt = conn.prepareStatement(sql.toString()); 
						pstmt.setString(1, newContact.getName());       	//쿼리?에 바인딩:name
						pstmt.setString(2, newContact.getPhoneNumber());	//쿼리?에 바인딩:phoneNumber
						pstmt.setString(3, newContact.getSidoId());     	//쿼리?에 바인딩:sidoId
						pstmt.setString(4, newContact.getAddress());    	//쿼리?에 바인딩:address
						pstmt.setInt(5, Integer.parseInt(newContact.getMoimId()));	//쿼리?에 바인딩:moimId
						pstmt.setString(6, contactList.get(Integer.parseInt(selectIndex)-1).getContactId());//쿼리?에 바인딩:contactId
						//----결과를 받지 않는 (INSERT)이므로 executeUpdate() 실행
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
		}//if	
	}	
//----editContact() method: return void end------------------------------------------------------	
}
//--EditContacts.java: 수정할 회원의 이름을 입력받아 검색하고, 해당 회원의 정보를 수정 UPDATE-------------------
