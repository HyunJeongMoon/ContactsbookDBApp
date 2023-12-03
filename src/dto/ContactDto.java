/*
 * ContactDto.java
 * - 회원 1명의 DB에 저장될 정보를 담는 클래스
 * - 회원정보 항목의 갯수: 9 개 항목
 * - DB내 테이블 갯수 및 테이블 명: 3개, CONTATCS, SIDO, MOIM
 * - 각 테이블 컬럼 구조 및 설명
 * --1. CONTACT table : DB입력시 자동부여되는 회원일련번호와 사용자로부터 키보드 입력 받는 5개 정보 항목
 * -----   CONTACTID number      	PK	회원ID, DB 자동부여 일련번호
 * -----        NAME varchar2(50)	NN	회원이름
 * ----- PHONEMUNBER varchar2(50)	NN	회원 전화번호
 * -----      SIDOID varchar2(2) 	FK	회원 시도주소ID, 행안부 시도주소코드 17개중 하나
 * -----     ADDRESS varchar2(100)	  	회원상세주소, 자유입력, 공백허용
 * -----      MOIMID number       	FK	회원그룹ID, 4개 그룹중 하나
 * --2. SIDO table : 행안부 표준 시도주소 코드, 17개 시도구분, 2자리 숫자코드
 * -----      SIDOID varchar2(2) 	PK	시도주소ID, 17개 2자리 숫자코드
 * -----    SIDONAME varchar2(100)	NN	시도 한글이름
 * --3. MOIM table : 회원 그룹, 4개 그룹	
 * -----      MOIMID number       	PK	회원그룹ID, 일련번호
 * -----    MOIMNAME varchar2(50) 	NN	회원그룹이름
 * - Application과 DB 데이터 전송시 필요한 단위 데이터 구조 정의  
 */
package dto;                         	//ContactDto.java 속한 패키지: dto
//-- ContactDto.java calss --------------------------------------------------------
public class ContactDto {
//-- private member variables	
	private	String serialNumber="";        	//화면출력용 정렬 번호
	private String contactId;              	//회원id
	private String name;                 	//회원이름
	private	String sidoId;               	//시도주소id
	private String sidoName;               	//시도주소이름
	private String address;               	//상세주소
	private String phoneNumber;            	//전화번호
	private String moimId;               	//회원그룹id
	private String moimName;            	//회원그룹이름

//-- ContactDto() 기본 생성자-----------------------------------------------------------	
	public ContactDto() {
	}
//-- ContactDto() 기본 생성자 END-------------------------------------------------------	
	
//-- ContactDto() 생성자: 5항목 키보드 입력 정보로 생성하는 생성자--------------------------------	
	public ContactDto(String name, String phoneNumber, String sidoId, String address, String moimId) {
		this.name = name;               	//회원이름
		this.phoneNumber = phoneNumber; 	//전화번호
		this.sidoId = sidoId;           	//시도주소id
		this.address = address;         	//상세주소
		this.moimId = moimId;           	//회원그룹id
	}
//-- ContactDto() 생성자: 5항목 END-----------------------------------------------------	

//-- ContactDto() 생성자: 9항목 키보드 입력 정보+DB 생성 정보로 생성하는 생성자---------------------	
	public ContactDto(String serialNumber, String contactId, String name, String sidoId, String sidoName,
		String address, String phoneNumber, String moimId, String moimName) {
		this.serialNumber = serialNumber;	//DB로부터 꺼내온 정렬순서 번호
		this.contactId = contactId;      	//회원id
		this.name = name;                 	//회원이름
		this.phoneNumber = phoneNumber;   	//전화번호
		this.sidoId = sidoId;            	//시도주소id         
		this.sidoName = sidoName;        	//시도주소이름
		this.address = address;          	//상세주소
		this.moimId = moimId;            	//회원그룹id
		this.moimName = moimName;         	//회원그룹이름
	}
//-- ContactDto() 생성자: 9항목 END-----------------------------------------------------	
	
//-- getSerialNumber() method, DB 정렬 순서번호 getter----------------------------------	
	public String getSerialNumber() {
		return serialNumber;             	//DB로부터 꺼내온 정렬순서 번호
	}
//-- getSerialNumber() method END----------------------------------------------------	

//-- setSerialNumber() method, DB 정렬 순서번호 setter-----------------------------------	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;	//DB로부터 꺼내온 정렬순서 번호
	}
//-- setSerialNumber() method END----------------------------------------------------	

//-- getContactId() method, 회원id getter---------------------------------------------	
	public String getContactId() {
		return contactId;                	//회원id
	}
//-- getContactId() method END-------------------------------------------------------	

//-- setContactId() method,  회원id setter--------------------------------------------	
	public void setContactId(String contactId) {
		this.contactId = contactId;       	//회원id
	}
//-- setContactId() method END-------------------------------------------------------	

//-- getName() method, 회원이름 getter--------------------------------------------------	
	public String getName() {
		return name;                     	//회원이름
	}
//-- getName() method END------------------------------------------------------------	

//-- setName() method, 회원이름 setter--------------------------------------------------	
	public void setName(String name) {
		this.name = name;                  	//회원이름
	}
//-- setName() method END------------------------------------------------------------	

//-- getSidoId() method, 시도주소id getter----------------------------------------------	
	public String getSidoId() {
		return sidoId;                  	//시도주소id
	}
//-- getSidoId() method END-----------------------------------------------------------	

//-- setSidoId() method, 시도주소id setter ----------------------------------------------	
	public void setSidoId(String sidoId) {
		this.sidoId = sidoId;              	//시도주소id
	}
//-- setSidoId() method END-----------------------------------------------------------	

//-- getSidoName() method, 시도주소id getter---------------------------------------------	
	public String getSidoName() {
		return sidoName;                  	//시도주소이름
	}
//-- getSidoName() method END---------------------------------------------------------	

//-- setSidoName() method, 시도주소이름 setter--------------------------------------------	
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;          	//시도주소이름
	}
//-- setSidoName() method END---------------------------------------------------------	

//-- getAddress() method, 상세주소 getter-----------------------------------------------	
	public String getAddress() {
		return address;                  	//상세주소이름
	}
//-- getAddress() method END----------------------------------------------------------	

//-- setAddress() method, 상세주소이름 setter--------------------------------------------	
	public void setAddress(String address) {
		this.address = address;            	//상세주소이름
	}
//-- setAddress() method END----------------------------------------------------------	

//-- getPhoneNumber() method, 전화번호 getter--------------------------------------------	
//-- getPhoneNumber() method, 전화번호 getter--------------------------------------------	
	public String getPhoneNumber() {
		return phoneNumber;                	//전화번호
	}
//-- getPhoneNumber() method END------------------------------------------------------	

//-- setPhoneNumber() method, 전화번호 setter--------------------------------------------	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;    	//전화번호
	}
//-- setPhoneNumber() method END------------------------------------------------------	

//-- getMoimId() method, 회원그룹id getter-----------------------------------------------	
	public String getMoimId() {
		return moimId;                   	//회원그룹id
	}
//-- getMoimId() method END-----------------------------------------------------------	

//-- setMoimId() method, 회원그룹id setter------------------------------------------------	
	public void setMoimId(String moimId) {
		this.moimId = moimId;              	//회원그룹id
	}
//-- setMoimId() method END-----------------------------------------------------------	

//-- getMoimName() method, 회원그룹이름 getter--------------------------------------------	
	public String getMoimName() {
		return moimName;                   	//회원그룹이름
	}
//-- getMoimName() method END---------------------------------------------------------	

//-- setMoimName() method, 회원그룹이름 setter--------------------------------------------	
	public void setMoimName(String moimName) {
		this.moimName = moimName;          	//회원그룹이름
	}
//-- setMoimName() method END---------------------------------------------------------	

//-- toSting() override class: DB에서 검색 한 1명의 회원정보를 한줄의 문자열로  만들어 내보낼 준비------
//--           이때, DB 내부에서 쓰이는 숫자 코드 대신 한글이름(ex 시도주소, 그룹이름)들로 대체해서 출력---
//--           검색 정렬 순번이 없는 경우는 공백으로 처리---------------------------------------
	@Override
	public String toString() {
		if (serialNumber.isEmpty()) {
			serialNumber = "";
		}
		else {
			serialNumber += ". ";
		}
		return serialNumber + "[이름=" + name +  ", 전화번호=" + phoneNumber + " 시도 주소= "+ sidoName + ",  상세 주소=" + address + ", 그룹=" + moimName
				+ "]";
	}
//-- toSting() override END-----------------------------------------------------------

} 
//--ContactDto.java class END---------------------------------------------------------	
