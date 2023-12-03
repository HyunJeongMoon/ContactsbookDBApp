/*
 * SelectContacts.java
 * - DB에 select 쿼리 보내서 결과 받는 클래스 
 * - 두종류 select 메소드 : selectAll, selectByName
 * - 1. selectAll : DB에 저장된 모든 회원 정보를 감섹 리턴
 * - 2. selectByName: 이름으로 검색한 회원들의 정보를 리턴
 */ 
package dao;                                         	//SelectContacts.java 속한 패키지: dao

import java.sql.Connection;                           	//DB Connection 라이브러리
import java.sql.PreparedStatement;                     	//SQL 쿼리 실행위한 라이브러리
import java.sql.ResultSet;                             	//DB로 부터 쿼리결과 받기위한 라이브러리
import java.sql.SQLException;                         	//예외처리위한 라이브러리
import java.util.ArrayList;                           	//여러명 회원정보들을 담을 ArrayList 라이브러리

import dto.ContactDto;                                 	//1명 회원정보 담을 dto 클래스 
import util.DbConnection;                              	//DB 연결 클래스
//--SlectContacts.java ----------------------------------------------------- 
public class SelectContacts {
	
//--selectAll() method : ArrayList<ContactDto> return, 모든 회원정보 조회--------  
	public static ArrayList<ContactDto> selectAll() {
		//----회원정보들을 담아서 넘길 ArrayList<ContactDto> 생성
		ArrayList<ContactDto> contactList =	new ArrayList<ContactDto>();
		//----DB에서 수행시킬 SQL 쿼리문 저장을 위한 StringBuilder sql 변수
		StringBuilder sql = new StringBuilder();
		//---- CONTACT 테이블에 있는 모든 회원정보를 조회
		//---- 이때, 시도주소id와 회원그룹id는 각각 SIDO 테이블과 MOIM 테이블에서 이름으로 조회, 변환 
		Connection conn = DbConnection.getConnction(); 	//connection 해주는 클래스 호출
		PreparedStatement pstmt= null;            	  	//SQL 쿼리 실행위한 PreparedStatement 변수       
		ResultSet rs = null;                     	  	//SQL 쿼리 실행 결과 받을 ResultSet 변수
		sql.append("SELECT	c.CONTACTID          	");	//1.contactId
		sql.append("	 ,	c.NAME               	");	//2.name
		sql.append("	 ,	c.PHONENUMBER        	");	//3.phoneNumber
		sql.append("	 ,	c.SIDOID            	");	//4.sidoId
		sql.append("	 ,	s.SIDONAME          	");	//5.sidoName
		sql.append("	 ,	c.ADDRESS            	");	//6.address
		sql.append("	 ,	c.MOIMID            	");	//7.moimId
		sql.append("	 ,	m.MOIMNAME          	");	//8.moimName
		sql.append("  FROM	CONTACTS c           	");	//CONTACT 테이블		
		sql.append("	 ,	SIDO s              	");	//SIDO 테이블		
		sql.append("	 ,	MOIM m              	");	//MOIM 테이블
		sql.append(" WHERE	c.SIDOID = s.SIDOID 	");	//CONTACT JOIN SIDO 조건	
		sql.append("   AND	c.MOIMID = m.MOIMID 	");	//CONTACT JOIN MOIM 조건
		sql.append(" ORDER 	BY c.NAME ASC       	");	//조회하고 이름 오름차순 정렬	
		try {                                    	   	//DB 연동 예외처리
			//----StringBuilder 타입의 sql을 String으로 변환해서 PreparedStatement에 넣고 DB와 연결
			pstmt = conn.prepareStatement(sql.toString());	
			//----결과를 받아야 하는 쿼리(SELECT)이므로 executeQuery() 실행, ResultSet 타입 rs로 받기
			rs = pstmt.executeQuery();
			while(rs.next()) {                      	//실행 결과있는 동안  한 줄(row)씩 처리
				ContactDto cdto = new ContactDto(); 	// 한명 회원 저장 위한 ContactDto 생성
				cdto.setContactId	(rs.getString("contactid"));  	//회원일련번호 
				cdto.setName		(rs.getString("name"));       	//회원이름
				cdto.setPhoneNumber	(rs.getString("phonenumber")); 	//전화번호
				cdto.setSidoId		(rs.getString("sidoid"));      	//시도주소id
				cdto.setSidoName	(rs.getString("sidoname"));   	//시도주소이름
				cdto.setAddress		(rs.getString("address"));     	//상세주소
				cdto.setMoimId		(rs.getString("moimid"));     	//회원그룹id
				cdto.setMoimName	(rs.getString("moimname"));    	//회원그룹이름
				contactList.add(cdto);               	//contactList에 ContactDto cdto 한줄 추가
			}	
		} catch (SQLException e) {        	         	//DB 연동 예외처리
			e.printStackTrace();         	        	//예외 발생시 trace 출력
		} finally{                 	  	            	//예외 처리 끝에
			DbConnection.close(conn, pstmt,rs);        	//DB 연동 관련 close 실행
		}
		return contactList;    	  		               	//DB 검색 결과 담은 ArrayList 리턴	
	}
//--selectAll() method : ArrayList<ContactDto> return END-------------------------------  
	
//--selectByName() method : ArrayList<ContactDto> return, 특정 이름(글자) 회원정보 조회---------  
	public static ArrayList<ContactDto> selectByName(String searchName) {
		//----회원정보들을 담아서 넘길 ArrayList<ContactDto> 생성		
		ArrayList<ContactDto> contactList =	new ArrayList<ContactDto>();
		//----DB에서 수행시킬 SQL 쿼리문 저장을 위한 StringBuilder sql 변수
		StringBuilder sql = new StringBuilder();
		//---- CONTACT 테이블서 회원 이름 NAME에 searchName을 가진 모든 회원정보를 조회
		//---- 이때, 시도주소id와 회원그룹id는 각각 SIDO 테이블과 MOIM 테이블에서 이름으로 조회, 변환
		Connection conn = DbConnection.getConnction();	//connection 해주는 클래스 호출
		PreparedStatement pstmt= null;                	//SQL 쿼리 실행위한 PreparedStatement 변수       
		ResultSet rs = null;                           	//SQL 쿼리 실행 결과 받을 ResultSet 변수
		sql.append("SELECT	ROWNUM                           	");	//SELECT 결과 정렬순번
		sql.append("     ,	t.CONTACTID                       	");	//회원정보id
		sql.append("     ,	t.NAME                            	");	//회원이름
		sql.append("     ,	t.PHONENUMBER                     	");	//전화번호
		sql.append("     ,	t.SIDOID                          	");	//시도주소id
		sql.append("     ,	t.SIDONAME                        	");	//시도이름
		sql.append("     ,	t.ADDRESS                         	");	//상세주소
		sql.append("     ,	t.MOIMID                          	");	//회원그룹id
		sql.append("     ,	t.MOIMNAME                         	");	//회원그룹이름
		sql.append(" FROM	(                                 	");	//FROM (서브쿼리
		sql.append(" 	    SELECT	c.CONTACTID              	");	//회원정보id
		sql.append(" 		     ,	c.NAME                   	");	//회원이름
		sql.append(" 		     ,	c.PHONENUMBER             	");	//전화번호
		sql.append(" 		     ,	c.SIDOID                 	");	//시도주소id
		sql.append(" 		     ,	s.SIDONAME                	");	//시도이름
		sql.append(" 		     ,	c.ADDRESS                 	");	//상세주소
		sql.append(" 		     ,	c.MOIMID                  	");	//회원그룹id
		sql.append(" 		     ,	m.MOIMNAME                	");	//회원그룹이름
		sql.append(" 	      FROM	CONTACTS c                	");	//FROM CONTACTS 테이블
		sql.append(" 		     ,	SIDO s                    	");	//SIDO 테이블
		sql.append(" 		     ,	MOIM m                    	");	//MOIM 테이블
		sql.append(" 	     WHERE	c.SIDOID = s.SIDOID       	");	//WHERE CONTACTS 조인 SIDO
		sql.append(" 	       AND	c.MOIMID = m.MOIMID       	");	//AND   CONTACTS 조인 MOIM
		sql.append(" 	       AND	c.NAME LIKE '%'|| ? ||'%'	");	//회원이름에 검색어 ? 포함
		sql.append("         ORDER 	BY c.NAME ASC             	");	//회원이름 오름차순 정렬
		sql.append(" 	    ) t  	   	                      	");	//서브쿼리) ALIAS
		try {                          	                          	//DB 연동예외처리
			//----StringBuilder 타입의 sql을 String으로 변환해서 PreparedStatement에 넣고 DB와 연결
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchName);            	//검색어 searchName을 쿼리?에 바인딩
			//----결과를 받아야 하는 쿼리(SELECT)이므로 executeQuery() 실행, ResultSet 타입 rs로 받기
			rs = pstmt.executeQuery();
			while(rs.next()) {                         	//실행 결과있는 동안  한 줄(row)씩 처리
				ContactDto cdto = new ContactDto();     // 한명 회원 저장 위한 ContactDto 생성
				cdto.setSerialNumber(rs.getString("rownum"));     	//정렬순번
				cdto.setContactId	(rs.getString("contactid"));	//회원일련번호 
				cdto.setName		(rs.getString("name"));       	//회원이름
				cdto.setPhoneNumber	(rs.getString("phonenumber"));	//전화번호
				cdto.setSidoId		(rs.getString("sidoid"));      	//시도주소id
				cdto.setSidoName	(rs.getString("sidoname"));   	//시도주소이름
				cdto.setAddress		(rs.getString("address"));    	//상세주소
				cdto.setMoimId		(rs.getString("moimid"));      	//회원그룹id
				cdto.setMoimName	(rs.getString("moimname"));    	//회원그룹이름
				contactList.add(cdto);               	//contactList에 ContactDto cdto 한줄 추가
			}	
		} catch (SQLException e) {        	         	//DB 연동 예외처리
			e.printStackTrace();         	        	//예외 발생시 trace 출력
		} finally{                 	  	            	//예외 처리 끝에
			DbConnection.close(conn, pstmt, rs);       	//DB 연동 관련 close 실행
		}
		return contactList;    	  		               	//DB 검색 결과 담은 ArrayList 리턴	
	}
//--selectByName() method : ArrayList<ContactDto> END------------------------------------  
}
//--SlectContacts.java -----------------------------------------------------
