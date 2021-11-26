package kpu.web.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kpu.web.club.domain.CustomerVO;



public class CustomerDAO {
	
	Connection conn = null;
	PreparedStatement pstmt=null;
	
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?"+ " allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	String url="jdbc:mysql://localhost/jspbookdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	String user = "jspbook";
	String password = "passwd";
	
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url,"jspbook","passwd");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	

	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			}	 catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
					conn.close();
			} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	//sql에 customer 테이블에 가입한 고객 정보 넣어줌
	public boolean add(CustomerVO vo) {
		connect();
		String sql = "insert into customer values (?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCustomerId());
			pstmt.setString(2, vo.getCustomer_Password());
			pstmt.setString(3, vo.getCustomer_Name());
			pstmt.setString(4, vo.getCustomer_Phone());
			pstmt.setString(5, vo.getCustomer_Email());
			pstmt.setString(6, vo.getRoom_Check());
			pstmt.setString(7, vo.getRoom_Num());
			pstmt.setString(8, vo.getRoom_bookday());
			pstmt.setString(9, vo.getRoom_bookduring());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	//조회
	public ArrayList<CustomerVO> showCustomer_booklist(){
		connect();
		ArrayList<CustomerVO> customer_booklist = new ArrayList<CustomerVO>();
		String sql = "select *from customer where cus_ischeck=?";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				CustomerVO customervo = new CustomerVO();
				
				customervo.setCustomerId(rs.getString("cus_id"));
				customervo.setCustomer_Password(rs.getString("cus_password"));
				customervo.setCustomer_Name(rs.getString("cus_name"));
				customervo.setCustomer_Phone(rs.getString("cus_phone"));
				customervo.setCustomer_Email(rs.getString("cus_email"));
				customervo.setRoom_Check(rs.getString("cus_ischeck"));
				customervo.setRoom_Num(rs.getString("cus_roomnum"));
				customervo.setRoom_bookday(rs.getString("cus_roombookday"));
				customervo.setRoom_bookduring(rs.getString("cus_during"));
				customer_booklist.add(customervo);	
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}return customer_booklist;
	}
	
	//로그인 대상에 저장
	public ArrayList<CustomerVO> getCustomerlogininfo(){
		connect();
		ArrayList<CustomerVO> customerlist = new ArrayList<CustomerVO>();
		String sql = "select *from customer";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				CustomerVO customervo = new CustomerVO();
				customervo.setCustomerId(rs.getString("cus_id"));
				customervo.setCustomer_Password(rs.getString("cus_password"));
				customervo.setCustomer_Name(rs.getString("cus_name"));
				customervo.setCustomer_Phone(rs.getString("cus_phone"));
				customervo.setCustomer_Email(rs.getString("cus_email"));
			
				customerlist.add(customervo);	
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} return customerlist;
	}
	
	
	
	//로그인을 위한 id, password읽기 유효성 확인
	public boolean login(String compare_id, String compare_password){
		connect();
		boolean login_check = false;
		
		String sql="select *from customer";
		String id;
		String password;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				id = rs.getString(1);
				password = rs.getString(2);
		
				//아이디와 비밀번호가 일치하는지 확인
				if(id.equals(compare_id)&&password.equals(compare_password)){
					
					login_check = true;
				}
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		} return login_check;
	}
	
	//로그인 했을 때 해당 id에 나머지 정보 입력 
	public CustomerVO login_info(String compare_id, String compare_password){
		CustomerVO customervo = new CustomerVO();
		connect();

		String sql="select *from customer";
		String id;
		String password;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				id = rs.getString(1);
				password = rs.getString(2);
		
				//아이디와 비밀번호가 일치하는지 확인
				if(id.equals(compare_id)&&password.equals(compare_password)){
					customervo.setCustomerId(rs.getString("cus_id"));
					customervo.setCustomer_Password(rs.getString("cus_password"));
					customervo.setCustomer_Name(rs.getString("cus_name"));
					customervo.setCustomer_Phone(rs.getString("cus_phone"));
					customervo.setCustomer_Email(rs.getString("cus_email"));
				}
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		} return customervo;
	}
		
		
	public CustomerVO read(String input_id){
		CustomerVO customervo = new CustomerVO();
		connect();

		String sql="select *from customer where cus_id=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_id);
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
					customervo.setCustomerId(rs.getString("cus_id"));
					customervo.setCustomer_Password(rs.getString("cus_password"));
					customervo.setCustomer_Name(rs.getString("cus_name"));
					customervo.setCustomer_Phone(rs.getString("cus_phone"));
					customervo.setCustomer_Email(rs.getString("cus_email"));
					customervo.setRoom_Check(rs.getString("cus_ischeck"));
					customervo.setRoom_Num(rs.getString("cus_roomnum"));
					customervo.setRoom_bookday(rs.getString("cus_roombookday"));
					customervo.setRoom_bookduring(rs.getString("cus_during"));
			}
			rs.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		} return customervo;
	}
		
		//로그인한 회원 정보 업데이트
		public boolean update(CustomerVO vo) {
			connect();
			String sql = "update customer set cus_password=?, cus_name=?, cus_phone=?, cus_email=? where cus_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getCustomer_Password());
				pstmt.setString(2, vo.getCustomer_Name());
				pstmt.setString(3, vo.getCustomer_Phone());
				pstmt.setString(4, vo.getCustomer_Email());
				pstmt.setString(5, vo.getCustomerId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				disconnect();
			} return true;
		}
		
		//예약 정보 업데이트
		public boolean book_update(CustomerVO vo) {
			connect();
			String sql = "update customer set  cus_ischeck=?,cus_roomnum=?, cus_roombookday=?, cus_during=? where cus_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getRoom_Check());
				pstmt.setString(2, vo.getRoom_Num());
				pstmt.setString(3, vo.getRoom_bookday());
				pstmt.setString(4, vo.getRoom_bookduring());
				pstmt.setString(5, vo.getCustomerId());
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				disconnect();
			} return true;
		}
		
		//회원탈퇴
		public boolean delete(CustomerVO vo) {
			connect();
			String sql = "delete from customer where cus_id=?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getCustomerId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				disconnect();
			} return true;
		}
		
		//예약 정보 업데이트
		public boolean book_delete(CustomerVO vo) {
			connect();
			String sql = "update customer set  cus_ischeck=?,cus_roomnum=?, cus_roombookday=?, cus_during=? where cus_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setString(1, vo.getRoom_Check());
				pstmt.setString(2, vo.getRoom_Num());
				pstmt.setString(3, vo.getRoom_bookday());
				pstmt.setString(4, vo.getRoom_bookduring());
				pstmt.setString(5, vo.getCustomerId());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				disconnect();
			} return true;
		}

		//조회
		public ArrayList<CustomerVO> customer_Booklist(){
			connect();
			ArrayList<CustomerVO> customer_Booklist = new ArrayList<CustomerVO>();
			String sql = "select *from customer where cus_ischeck='O'";
			try {
				pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					CustomerVO customervo = new CustomerVO();
					
					customervo.setCustomerId(rs.getString("cus_id"));
					customervo.setCustomer_Password(rs.getString("cus_password"));
					customervo.setCustomer_Name(rs.getString("cus_name"));
					customervo.setCustomer_Phone(rs.getString("cus_phone"));
					customervo.setCustomer_Email(rs.getString("cus_email"));
					customervo.setRoom_Check(rs.getString("cus_ischeck"));
					customervo.setRoom_Num(rs.getString("cus_roomnum"));
					customervo.setRoom_bookday(rs.getString("cus_roombookday"));
					customervo.setRoom_bookduring(rs.getString("cus_during"));
					
					customer_Booklist.add(customervo);	
				}
				rs.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			} return customer_Booklist;
		}
}
