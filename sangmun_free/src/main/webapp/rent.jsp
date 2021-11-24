<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kpu.web.club.domain.CustomerVO, java.util.*, java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<c:set var="customer" value="${requestScope.rent_customervo}" />

<html>
<head>

<meta charset="UTF-8">
<title>study alone</title>

<link rel="stylesheet" href="resources/rent.css"></link>

</head>

<body>

	<header>
		<c:choose>
				<c:when test="${customer.customerId != null}" >
					<a href="http://localhost:8080/sangmun_free/CustomerServlet?key=home&id=${customer.customerId}" class="join"><h1>혼자만의 스터디 공간 Study Alone</h1></a>
				</c:when>
		</c:choose>
	</header>

	<nav class="main-nav">
  			  <ul>
  			 
    		  	<c:choose>
					<c:when test="${customer.customerId == null}" >
						 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=news" class="news">회원가입</a></li>
					</c:when>
					<c:when test="${customer.customerId != null}" >
						<li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=home&id=${customer.customerId}" class="news">홈으로</a></li>
					</c:when>
				</c:choose>
				
    		  	 <c:choose>
				<c:when test="${customer.customerId == null}" >
					 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=login" class="news">로그인</a></li>
				</c:when>
					<c:when test="${customer.customerId != null}" >
						<li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=update&id=${customer.customerId}" class="news">${customer.customerId}님</a>
							<a href="http://localhost:8080/sangmun_free/CustomerServlet?key=logout" class="news">로그아웃</a>
						</li>
					</c:when>
				</c:choose>
    		  	 
      	 		 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent&id=${customer.customerId}" class="join">예약하기</a></li>
      		     <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent_list&id=${customer.customerId}" class="contact">전체 예약정보</a></li>
  			 </ul>
		</nav>
		


	<section class="banner">
		<img src="resources/images/study_room1.jpg" alt="study_banner">
		<div class="welcome">
			<form action="http://localhost:8080/sangmun_free/CustomerServlet?key=rent&id=${customer.customerId}" method="post">
					<ul>

						<li>
						방번호 : <select name ="form_roomnum"  autofocus required> 
							<% 
								for(int i=1; i <100; i++)
									out.println("<option>"+ i + "</option>\n" );
							%>
							
						</select>
						</li>
						<%
								Calendar cal = Calendar.getInstance();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String datestr = sdf.format(cal.getTime());
						%>
																									
						<li>예약 희망일: <input type="date" id="22" name="form_rentday" value="<%=datestr%>" min="<%=datestr%>"></li>
						<li>예약 기간(일): <input type="number" name="form_during" min="1" max="20"></li>
						
					</ul>
				<br>
					<input type="submit" name="submit" value="예약">
					<input type="reset" name="reset" value="다시 작성">
					 
			</form>

		</div>
	</section>

	<footer>
		<br>
	</footer>

</body>
</html>