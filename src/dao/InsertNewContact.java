/*
 * InsertNewContact.java
 * 이름,전화번호,시도주소id,상세주소,회원그룹id 입력 받아서 DB CONTACTS 테이블에 insert
 */
package dao;                                         	//InsertNewContacts.java 속한 패키지: dao

import java.sql.Connection;                           	//DB Connection 라이브러리
import java.sql.PreparedStatement;                     	//SQL 쿼리 실행위한 라이브러리
import java.sql.SQLException;                         	//예외처리위한 라이브러리
import java.util.Scanner;                              	//키보드입력 Scanner 라이브러리

import dto.ContactDto;                                 	//1명 회원정보 담을 dto 클래스 
import util.DbConnection;                              	//DB Connection 라이브러리
import service.InputContactData;                       	//입력유효성 체크하면서 입력받기 클래스                       	
//--InsertNewContact.java class -------------------------------------------------------------
public class InsertNewContact {
//----insertNew() method: return void, 회원정보 (5항목) 넘겨받아 DB INSERT SQL 쿼리로 DB 삽입		
	public void insertNew(Scanner scan) {
		//----넘겨받은 회원정보를 ContactDto 타입으로 받는다	
		ContactDto newContact = InputContactData.getNewContact(scan);
		//----DB에 있는 회원정보와 5항목(이름,전화번호,시도주소id,상세주소,회원그룹id)이 모두 일치하는 row 존재 할경우,	
		if(CheckRedundancy.isRedundantContact(newContact.getName(), newContact.getPhoneNumber(), newContact.getSidoId(), newContact.getAddress(), newContact.getMoimId())) {
			System.out.println("이미 있는 회원 정보 입니다.");		//에러메시지 출력하고
			return;            	   	            	    	//회원정보 추가를 하지않고 전 단계로 돌아간다
		}
		Connection conn = DbConnection.getConnction(); 		//connection 해주는 클래스 호출
		PreparedStatement pstmt= null;           		  	//SQL 쿼리 실행위한 PreparedStatement 변수       
		//----DB에서 수행시킬 SQL 쿼리문 저장을 위한 StringBuilder sql 변수
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO contacts VALUES	");	//INSERT INTO CONTACTS 테이블
		sql.append("(	contact_seq.nextval    	");	//(회원 일련번호, SEQUENCE CONTACTS_SEQ 자동부여
		sql.append(", 	?                      	");	//1.newName
		sql.append(",	?                      	");	//2.newPhoneNumber
		sql.append(",	?                      	");	//3.newSido_id
		sql.append(",	?                      	");	//4.newAddress
		sql.append(",	?                      	");	//5.newMoimId
		sql.append(")	                       	");	//)
			
		try {
			//----StringBuilder 타입의 sql을 String으로 변환해서 PreparedStatement에 넣고 DB와 연결
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1,newContact.getName());                  	//쿼리?에 바인딩:newName
			pstmt.setString(2,newContact.getPhoneNumber());          	//쿼리?에 바인딩:newPhoneNumber
			pstmt.setString(3,newContact.getSidoId());               	//쿼리?에 바인딩:newSidoid
			pstmt.setString(4,newContact.getAddress());              	//쿼리?에 바인딩:newAddress
			pstmt.setInt(5,Integer.parseInt(newContact.getMoimId()));	//쿼리?에 바인딩:newMoimId
			
			//----결과를 받지 않는 (INSERT)이므로 executeUpdate() 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {        	         	//DB 연동 예외처리
			e.printStackTrace();         	        	//예외 발생시 trace 출력
		} finally{                 	  	            	//예외 처리 끝에
			DbConnection.close(conn, pstmt);        	//DB 연동 관련 close 실행
		}
	} 
//----insertNew() method: return void END------------------------------------------------------		
} 
//--InsertNewContact.java class END------------------------------------------------------------
