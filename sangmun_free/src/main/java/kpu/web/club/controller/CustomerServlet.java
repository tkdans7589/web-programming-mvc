package kpu.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kpu.web.club.domain.CustomerVO;

import kpu.web.club.persistence.CustomerDAO;


/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String cmdReq="";
		String message="";
		
		cmdReq=request.getParameter("key");
		
		
		//join => 회원가입 창 열어줌 
		if(cmdReq.equals("join")) {
			response.sendRedirect("register.html");		
		}
		
		//login => 로그인 창 열어줌
		else if(cmdReq.equals("login")) {
			response.sendRedirect("login.html");		
		}
		
		//logout
		else if(cmdReq.equals("logout")) {
			message="로그아웃 성공";
			request.setAttribute("message", message);
			RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
		
		//update => 회원정보 수정
		else if(cmdReq.equals("update")) {
			CustomerDAO customerdao = new CustomerDAO();
			CustomerVO customoervo = customerdao.read(request.getParameter("id"));
			request.setAttribute("update_customervo",customoervo);
			RequestDispatcher view=request.getRequestDispatcher("update.jsp");
			view.forward(request, response);
		}
		
		//home => 로그인 상태로 홈으로 돌아갈때
		else if(cmdReq.equals("home")) {
			CustomerDAO customerdao = new CustomerDAO();
			CustomerVO customoervo = customerdao.read(request.getParameter("id"));
			request.setAttribute("login_customer",customoervo);
			RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
		
		//rent => 방 예약 
		else if(cmdReq.equals("rent")) {
			CustomerDAO customerdao = new CustomerDAO();
			CustomerVO customoervo = customerdao.read(request.getParameter("id"));
	
			if(customoervo.getCustomerId()==null){
				message="로그인 필요";
				request.setAttribute("message",message);
				RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
			else {
				request.setAttribute("rent_customervo",customoervo);
				RequestDispatcher view=request.getRequestDispatcher("rent.jsp");
				view.forward(request, response);
			}
		
		}
		
		//rent_list => 예약 리스트
		else if(cmdReq.equals("rent_list")) {
			CustomerDAO dao= new CustomerDAO();
			CustomerVO customoervo = dao.read(request.getParameter("id"));
			
			if(customoervo.getCustomerId()==null){
				message="예약자 조회를 위해선 로그인이 필요";
				request.setAttribute("message",message);
				RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
			else {
				ArrayList<CustomerVO>rent_customerlistvo = dao.customer_Booklist();
				request.setAttribute("rent_customerlistvo", rent_customerlistvo);
				request.setAttribute("login_customer", customoervo);
				RequestDispatcher view=request.getRequestDispatcher("rentlist.jsp");
				RequestDispatcher view2=request.getRequestDispatcher("rentlist.jsp");
				view.forward(request, response);
				view2.forward(request, response);
			}
		
		}
		
		//delete => 회원탈퇴
		else if (cmdReq.equals("delete")) {
			CustomerVO customervo = new CustomerVO();
			CustomerDAO customerdao = new CustomerDAO();
			customervo.setCustomerId(request.getParameter("id"));

			if(customerdao.delete(customervo))message="회원정보 삭제 완료";
			else message="삭제 실패";

			request.setAttribute("message", message);
			request.setAttribute("delete_customer", customervo);

			RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
		
		//rent_delete => 방 예약 삭제
		else if (cmdReq.equals("rent_delete")) {
			CustomerDAO customerdao = new CustomerDAO();
			CustomerVO customoervo = customerdao.read(request.getParameter("id"));
			request.setAttribute("rent_update_customervo",customoervo);
			RequestDispatcher view=request.getRequestDispatcher("rent_update.jsp");
			view.forward(request, response);
		}
		
		//rent_update => 방 예약 하기
		else if(cmdReq.equals("rent_update")) {
			CustomerDAO customerdao = new CustomerDAO();
			CustomerVO customoervo = customerdao.read(request.getParameter("id"));
			request.setAttribute("rent_update_customervo",customoervo);

			RequestDispatcher view=request.getRequestDispatcher("rent.jsp");
			view.forward(request, response);
		}
		
		//rent_info_update => 예약 정보 변경
		else if(cmdReq.equals("rent_info_update")) {
			CustomerDAO customerdao = new CustomerDAO();
			CustomerVO customoervo = customerdao.read(request.getParameter("id"));
			request.setAttribute("rent_update_customervo",customoervo);
			RequestDispatcher view=request.getRequestDispatcher("rent_update.jsp");
			view.forward(request, response);
		}
		
		//rent_info_delete => 예약 삭제
		else if(cmdReq.equals("rent_info_delete")) {
			CustomerDAO customerdao = new CustomerDAO();
			CustomerVO customoervo = customerdao.read(request.getParameter("id"));
			HttpSession session = request.getSession();
			session.setAttribute("room_Delete","X");
			
			customoervo.setCustomerId((String)session.getAttribute("logid"));
			customoervo.setRoom_Check((String)session.getAttribute("room_Delete"));
			customoervo.setRoom_Num((String)session.getAttribute("room_Delete"));
			customoervo.setRoom_bookday((String)session.getAttribute("room_Delete"));
			customoervo.setRoom_bookduring((String)session.getAttribute("room_Delete"));
			
			if(customerdao.book_update(customoervo))message="예약 삭제 성공";
			else message="예약 삭제 실패";
		
			request.setAttribute("message", message);
			request.setAttribute("login_customer",customoervo);
			RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String message="";
		String cmdReq="";
		cmdReq=request.getParameter("key");
	
		if(cmdReq.contentEquals("join") ) {
			CustomerVO customerVO = new CustomerVO();
			
			customerVO.setCustomerId(request.getParameter("form_cusid"));
			customerVO.setCustomer_Password(request.getParameter("form_cuspassword"));
			customerVO.setCustomer_Name(request.getParameter("form_cusname"));
			customerVO.setCustomer_Phone(request.getParameter("form_cusphone"));
			customerVO.setCustomer_Email(request.getParameter("form_cusemail"));
			
			CustomerDAO customerDAO = new CustomerDAO();
			
			
			if(customerDAO.add(customerVO))message="가입 축하";
			else {
				message="가입 실패입니다";
			}
			
			request.setAttribute("message", message);
			request.setAttribute("customer", customerVO);
			
			RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
		
		
		
		 else if (cmdReq.equals("login")) {
				
				CustomerDAO customerDAO = new CustomerDAO();
				CustomerVO customerVO = new CustomerVO();
				String log_id = null;
				
				if(customerDAO.login(request.getParameter("form_logcusid"),request.getParameter("form_logcuspassword"))) {
					customerVO = customerDAO.login_info(request.getParameter("form_logcusid"),request.getParameter("form_logcuspassword"));
					message="로그인 되었습니다.";
					log_id = request.getParameter("form_logcusid");
				}
				
				else {
					message="로그인 실패";
					
				}
				HttpSession session = request.getSession();
				session.setAttribute("logid",log_id);
				request.setAttribute("logid",log_id);
				request.setAttribute("message", message);
				request.setAttribute("login_customer", customerVO);
				
		
				RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
		
		
		
		
		 else if (cmdReq.contentEquals("update")) {
				CustomerVO customerVO = new CustomerVO();

				customerVO.setCustomerId(request.getParameter("form_cusid"));
				customerVO.setCustomer_Password(request.getParameter("form_cuspassword"));
				customerVO.setCustomer_Name(request.getParameter("form_cusname"));
				customerVO.setCustomer_Phone(request.getParameter("form_cusphone"));
				customerVO.setCustomer_Email(request.getParameter("form_cusemail"));	

				CustomerDAO customerDao = new CustomerDAO();
				
				message="";
				
				if(customerDao.update(customerVO)) {
					message="수정 완료";
				}
				else message="수정 실패";

				request.setAttribute("message", message);
				request.setAttribute("login_customer", customerVO);
				RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
		
		

		 else if (cmdReq.contentEquals("rent_update")) {
				CustomerVO customerVO = new CustomerVO();
				HttpSession session = request.getSession();
				
				customerVO.setCustomerId((String)session.getAttribute("logid"));
				customerVO.setRoom_Check(request.getParameter("room_Check"));
				customerVO.setRoom_Num(request.getParameter("form_roomnum"));
				customerVO.setRoom_bookday(request.getParameter("form_rentday"));
				customerVO.setRoom_bookduring(request.getParameter("form_during"));
	
				CustomerDAO customerDao = new CustomerDAO();
				
				message="";
				if(customerDao.book_update(customerVO))message="예약 수정 성공";
				else message="예약 수정 실패";

				request.setAttribute("message", message);
				request.setAttribute("rent_update_customer", customerVO);

				RequestDispatcher view = request.getRequestDispatcher("rentlist.jsp");
				view.forward(request, response);
			}
		
		 else if(cmdReq.contentEquals("rent_info_update") ) {
				CustomerVO customerVO = new CustomerVO();
				HttpSession session = request.getSession();
				session.setAttribute("room_Check","O");
				
				customerVO.setCustomerId((String)session.getAttribute("logid"));
				customerVO.setRoom_Check((String)session.getAttribute("room_Check"));
				customerVO.setRoom_Num(request.getParameter("form_roomnum"));
				customerVO.setRoom_bookday(request.getParameter("form_rentday"));
				customerVO.setRoom_bookduring(request.getParameter("form_during"));
				
				CustomerDAO customerDAO = new CustomerDAO();
				
				if(customerDAO.book_update(customerVO))message="예약 내용 변경";
				else message="예약에 실패했습니다";
				
				request.setAttribute("message", message);
				request.setAttribute("login_customer", customerVO);
				
				RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
		
		
		
		
		 else if (cmdReq.contentEquals("home")) {
				CustomerVO customerVO = new CustomerVO();

				customerVO.setCustomerId(request.getParameter("form_cusid"));
				customerVO.setCustomer_Password(request.getParameter("form_cuspassword"));
				customerVO.setCustomer_Name(request.getParameter("form_cusname"));
				customerVO.setCustomer_Phone(request.getParameter("form_cusphone"));
				customerVO.setCustomer_Email(request.getParameter("form_cusemail"));	

				CustomerDAO customerDao = new CustomerDAO();
				
				message="";
				
				if(customerDao.update(customerVO))message="홈으로 돌아왔습니다";
				else message="홈으로 복귀 실패";

				request.setAttribute("message", message);
				request.setAttribute("login_customer", customerVO);

				RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
				view.forward(request, response);
			}
		
		 else if(cmdReq.contentEquals("rent") ) {
			CustomerVO customerVO = new CustomerVO();
			HttpSession session = request.getSession();
			session.setAttribute("room_Check","O");
			
			customerVO.setCustomerId((String)session.getAttribute("logid"));
			customerVO.setRoom_Check((String)session.getAttribute("room_Check"));
			customerVO.setRoom_Num(request.getParameter("form_roomnum"));
			customerVO.setRoom_bookday(request.getParameter("form_rentday"));
			customerVO.setRoom_bookduring(request.getParameter("form_during"));
			
			CustomerDAO customerDAO = new CustomerDAO();
			
			if(customerDAO.book_update(customerVO))message="예약 완료";
			else message="예약에 실패했습니다";
			String idcheck = "true";
			String id_nocheck = "false";
			request.setAttribute("id_nocheck",id_nocheck);
			request.setAttribute("idcheck",idcheck);
			request.setAttribute("message", message);
			request.setAttribute("login_customer", customerVO);
			
			RequestDispatcher view=request.getRequestDispatcher("welcome.jsp");
			view.forward(request, response);
		}
	}
}