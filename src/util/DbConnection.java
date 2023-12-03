/*
 * Database Connection: creation, close
 * 오라클 DB 연결을 위한 클래스
 */
package util;                     	//DbConnection.java가 속한 패키지 : util

import java.sql.Connection;      	//DB연결위한 라이브러리
import java.sql.DriverManager;		//드라이버 로딩을 위한 라이브러리
import java.sql.PreparedStatement;	//sql 쿼리 실행을 위한 PreparedStatement 라이브러리 
import java.sql.ResultSet;       	//쿼리 실행결과를 담아올 ResultSet 라이브러리
import java.sql.SQLException;		//예외 처리를 위한 라이브러리

//---DB 연결을 위한 DbConnection.java 클래스----------------------------------------------
public class DbConnection {
//--member variables: 오라클 DB 연결을 위해 필요한 값 저장을 위한 변수들	
	static final String driver	= "oracle.jdbc.driver.OracleDriver";	//오라클 jdbc 드라이버
	static final String url		= "jdbc:oracle:thin:@localhost:1521:xe";//오라클DB 포트:SID
	static final String userid	= "ora_user";                          	//오라클DB 스키마 유저명
	static final String passwd	= "1234";                              	//오라클DB 비밀번호 
	
//----Constructor: DBConnection()----------------------------------------------	
	private DbConnection() {                  	// private 이므로 new 안된다. 생성 할 수 없다
		
	}
//----Constructor: DBConnection() END------------------------------------------
	
//----getConnection() method: return Connection--------------------------------	
	public static Connection getConnction() {		//DB 연결 connection
		Connection conn = null;
		try {
			Class.forName(driver);           		//driver를 load한다.
			conn = DriverManager.getConnection(url, userid, passwd); //(url,userid,passwd)가지고 DB연결을 얻는다
		}catch(ClassNotFoundException e) {    		//발생할 수 있는 예외 처리
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;                           		//DB 연결 connection을 리턴
	}
//----getConnection() method: return Connection END------------------------------		

//----close() method: DB connection, PreparedStatement, ResultSet 3가지 close------	
	public static void close(Connection conn, 	PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs 		!=	null) rs.close();   	//ResultSet rs가 null 아니면 close
			if(pstmt	!=	null) pstmt.close();	//PreparedStatement pstmt가 null 아니면 close
			if(conn 	!=	null) conn.close(); 	//Connection conn이 null 아니면 close
		} catch(SQLException e) {			       	//발생할 수 있는 예외처리
			e.printStackTrace();
		}
	}
//----close() method: DB connection, PreparedStatement, ResultSet 3가지 close END---	

//----close() method: DB connection, PreparedStatement 2가지 close------------------	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt	!= 	null) pstmt.close();	//PreparedStatement pstmt가 null 아니면 close
			if(conn 	!=	null) conn.close(); 	//Connection conn이 null 아니면 close
		} catch(SQLException e) {			       	//발생할 수 있는 예외처리
			e.printStackTrace();
		}
	}
//----close() method: DB connection, PreparedStatement 2가지 close END---------------	

}
//---DB 연결을 위한 DbConnection 클래스 END----------------------------------------------
