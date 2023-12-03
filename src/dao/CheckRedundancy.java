/*
 * CheckRedundancy.java
 * - 입력받은 회원 정보 5항목이 모두 일치하는 다른 회원이 DB에 있는지 체크 
 * - 회원정보 5항목: name, phoneNumber, sidoId, address, moimId
 */ 
package dao;                                         	//SelectContacts.java 속한 패키지: dao

import java.sql.Connection;                           	//DB Connection 라이브러리
import java.sql.PreparedStatement;                     	//SQL 쿼리 실행위한 라이브러리
import java.sql.ResultSet;                             	//DB로 부터 쿼리결과 받기위한 라이브러리
import java.sql.SQLException;                         	//예외처리위한 라이브러리
import util.DbConnection;                              	//DB 연결 클래스
//--CheckRedundancy.java class, 중복된 회원정보 있는지 조회--------------------------------------------
public class CheckRedundancy {
//----isRedundantContact() method: return boolean, 주어진 데이터 항목이 모두 일치하는 다른 회원이 있는지 검색--
	public static boolean isRedundantContact(String newName, String newPhoneNumber,String newSidoId, String newAddress,String newMoimId) {
		//----DB에서 수행시킬 SQL 쿼리문 저장을 위한 StringBuilder sql 변수
		StringBuilder sql = new StringBuilder();
		boolean flag=false; 					    		//boolean 판정결과 변수

		sql.append("SELECT	ROWNUM           	");   		//SELECT 조회 결과 행수
		sql.append("  FROM	CONTACTS c       	");   		//FROM CONTACTS 테이블		
		sql.append(" WHERE	c.NAME =        ?	");   		//1.newName
		sql.append("   AND	c.PHONENUMBER = ?	");   		//2.newPhoneNumber
		sql.append("   AND	c.SIDOID =      ?	");   		//3.newSidoId
		sql.append("   AND	c.ADDRESS =     ?	");   		//4.newAddress
		sql.append("   AND	c.MOIMID =      ?	");   		//5.newMoimId
		Connection conn = DbConnection.getConnction();  	//connection 해주는 클래스 호출
		PreparedStatement pstmt= null;            	  		//SQL 쿼리 실행위한 PreparedStatement 변수       
		ResultSet rs = null;                     	  		//SQL 쿼리 실행 결과 받을 ResultSet 변수  
		try {
			//----StringBuilder 타입의 sql을 String으로 변환해서 PreparedStatement에 넣고 DB와 연결
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, newName);                 	//쿼리?에 바인딩:newName
			pstmt.setString(2, newPhoneNumber);            	//쿼리?에 바인딩:newPhoneNumber
			pstmt.setString(3, newSidoId);                 	//쿼리?에 바인딩:newSidoId
			pstmt.setString(4, newAddress);                	//쿼리?에 바인딩:newAddress
			pstmt.setInt(5, Integer.parseInt(newMoimId));  	//쿼리?에 바인딩:newMoimId
			//----결과를 받는 (SELECT) 쿼리이므로 executeQuery()실행
			rs = pstmt.executeQuery();
			if(rs.next()) {                              	//검색 결과가 있으면
				flag = true;                            	//중복 true
			} else {                                    	//검색 결과가 없으면
				flag= false;                             	//중복 false                	            
			}
		} catch (SQLException e) {                       	//DB 연동 예외처리
			e.printStackTrace();                        	//예외 발생시 trace 출력
		} finally{                                      	//예외 처리 끝에
			DbConnection.close(conn, pstmt, rs);           	//DB 연동 관련 close 실행
		}
		return flag;                                     	//return flag
	}
//----isRedundantContact() method: return boolean END---------------------------------------------
}
//--CheckRedundancy.java class END----------------------------------------------------------------
