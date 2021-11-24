<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kpu.web.club.domain.CustomerVO, java.util.*, java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>

<!DOCTYPE html>
	<c:set var="customer" value="${requestScope.login_customer}" />
	<c:set var="book_list" value="${requestScope.rent_customerlistvo}" />
	
	
<html>
<head>

<meta charset="UTF-8">
<title>study alone</title>

<link rel="stylesheet" href="resources/rentlist.css"></link>

</head>

<body>
	<header>
		<c:choose>
			<c:when test="${customer.customerId == null}" >
				 <a href="http://localhost:8080/sangmun_free/welcome.jsp" class="join"><h1>혼자만의 스터디 공간 Study Alone</h1></a>
			</c:when>
				<c:when test="${customer.customerId != null}" >
					<a href="http://localhost:8080/sangmun_free/CustomerServlet?key=home&id=${customer.customerId}" class="join"><h1>혼자만의 스터디 공간 Study Alone</h1></a>
			</c:when>
		</c:choose>
	</header>

	<nav class="main-nav">
  			  <ul>
  			 
    		  	 <c:choose>
				<c:when test="${customer.customerId == null}" >
					 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=join" class="news">회원가입</a></li>
				</c:when>
					<c:when test="${customer.customerId != null}" >
						<li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=home&id=${customer.customerId}" class="news">홈으로</a></li>
					</c:when>
				</c:choose>
				
    		  	 <!--  로그인 성공시 해당 고객의 id 출력   -->
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
    		  	 
      	 		 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent&id=${customer.customerId}" class="games">예약하기</a></li>
      		     <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent_list&id=${customer.customerId}" class="join">전체 예약정보</a></li>
      		      
  			 </ul>
		</nav>
		
<section class="banner">
		<img src="resources/images/study_room1.jpg" alt="study_banner">
		<div class="welcome">
		
		<table>
		<mytag:item>예약자현황</mytag:item>
			<c:forEach var="book" items="${book_list}">
				<tr id=list>
					<c:choose>
	    		      	<c:when test="${customer.customerId == book.customerId}" >
	    		      		<td><a id=same href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent_info_update&id=${customer.customerId}" class="join">${customer.customerId}</a></td>
	    		     	</c:when>
	    		     	<c:otherwise>
	    		     		<td>${book.customerId}</td>
	    		     	</c:otherwise>
    		  	  	 </c:choose>
					<td>${book.customer_Name}</td>
					<td>${book.customer_Phone}</td>
					<td>${book.customer_Email}</td>
					<td>${book.room_Num}</td>
					<td>${book.room_bookday}</td>
					<td>${book.room_bookduring}</td>
				</tr>
			
			
			</c:forEach>		
		</table>
		<br>
		</div>
	</section>
	<footer>
		<br>
	</footer>

</body>
</html>