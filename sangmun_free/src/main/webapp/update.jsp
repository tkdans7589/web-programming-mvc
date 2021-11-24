<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kpu.web.club.domain.CustomerVO, java.util.List, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
	<c:set var="customer" value="${requestScope.update_customervo}" />

<html>
<head>

<meta charset="UTF-8">
<title>study alone</title>

<link rel="stylesheet" href="resources/update.css"></link>

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
						 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=join" class="news">회원가입</a></li>
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
						<li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=update&id=${customer.customerId}" class="join">${customer.customerId}님</a>
							<a href="http://localhost:8080/sangmun_free/CustomerServlet?key=logout" class="news">로그아웃</a></li>
					</c:when>
				</c:choose>
    		  	 
      		 	
      	 		 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent&id=${customer.customerId}" class="news">예약하기</a>
      		     <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent_list&id=${customer.customerId}"  class="contact">전체 예약정보</a></li>
  			 </ul>
		</nav>
		

	<section class="banner">
		<img src="resources/images/study_room1.jpg" alt="study_banner">
		<div class="welcome">
			<form action="http://localhost:8080/sangmun_free/CustomerServlet?key=update" method="post">
					<ul>		
						<li>ID : <input type="text" name="form_cusid" value="${customer.customerId}" readonly ></li>
						<li>PASSWORD : <input type="password" name="form_cuspassword" value="${customer.customer_Password}" ></li>
						<li>USERNAME : <input type="text" name="form_cusname" value="${customer.customer_Name}"></li>
						<li>MOBILE : <input type="text" name="form_cusphone" value="${customer.customer_Phone }"></li>
						<li>EMAIL : <input type="text" name="form_cusemail" value="${customer.customer_Email}"></li>
					</ul>
					<br>
					<input type="submit" name="submit" value="보내기">
					<input type="reset" name="reset" value="다시 작성">
					<a href="http://localhost:8080/sangmun_free/CustomerServlet?key=delete&id=${customer.customerId}">회원탈퇴</a>
			</form>

		</div>
	</section>

	<footer><br></footer>

</body>
</html>