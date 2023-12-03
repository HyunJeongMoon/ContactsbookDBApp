/*
 * ViewAllContacts.java
 * - 메인 화면에서 2. 회원 목록 을 선택하면 실행: 모든 회원 조회
 * - DAO의 SelectContacts.selectAll()을 호출해서 결과를 리턴받아 화면에 출력
 */
package service; 	   		    	//ViewAllContacts.java 가 속한 패키지: service
import java.util.ArrayList;			//DTO-ContactDto를 담아서 올 ArrayList 위한 라이브러리
import dao.SelectContacts;			//DAO-SelectContacts : DB에서 select 쿼리 실행한느 클래스
import dto.ContactDto;				//DTO-1명 정보 담기위한 object
//--ViewAllContacts class------------------------------------------
public class ViewAllContacts {
	
//----viewAllContacts() method: return void------------------------	
	public void viewAllContacts() {
		
		//DAO-SelectContacts.selectAll(): DB에서 select실행해서 결과 리턴
		//ContactDto들을 담고 있는 ArrayList 로 넘겨받는다.
		ArrayList<ContactDto> contactList =	SelectContacts.selectAll();	 

		//넘겨받은 DB 검색 결과를 화면에 출력
		System.out.println("\n총 "+contactList.size()+"명의 회원이 저장되어 있습니다.");
		for(int i=0; i<contactList.size();i++) {
			System.out.println(contactList.get(i));
		}
}
//----viewAllContacts() method: return void END---------------------	
	
}
//---ViewAllContacts class end--------------------------------------
