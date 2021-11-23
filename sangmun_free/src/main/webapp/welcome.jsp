<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kpu.web.club.domain.CustomerVO, java.util.List, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
		<c:set var="customer" value="${requestScope.login_customer}" />
		<c:set var="message" value="${requestScope.message}" />
		<c:set var="idcheck" value="${requestScope.idcheck}" />
		<c:set var="id_check" value="${requestScope.id_nocheck}" />
		
		<%
		request.setCharacterEncoding("UTF-8");
			if(request.getAttribute("logid") != null) {
				session.setAttribute("user", request.getAttribute("logid"));
			}	
		%>
<html>
<head>
 	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>study alone</title>
	<link rel ="stylesheet" href="resources/main.css"></link>
</head>
<body>
		<c:choose>
			<c:when test="${message == '수정 완료'}" >
					<script>
					alert('회원정보가 변경되었습니다.');
					</script>
			</c:when>
			<c:when test="${message == '로그인 실패'}" >
					<script>
					alert('로그인 오류입니다. 아이디와 비밀번호를 확인하세요.');
					</script>
			</c:when>
			<c:when test="${message == '회원정보 삭제 완료'}" >
					<script>
					alert('정상적으로 회원탈퇴 되었습니다.');
					</script>
			</c:when>
			<c:when test="${message == '로그아웃 성공'}" >
					<script>
					alert('정상적으로 로그아웃 되었습니다.');
					</script>
			</c:when>
			<c:when test="${message == '예약 내용 변경'}" >
					<script>
					alert('예약 정보가 변경되었습니다.');
					</script>
			</c:when>
			<c:when test="${message == '예약 삭제 성공'}" >
					<script>
					alert('예약 정보가 삭제되었습니다.');
					</script>
			</c:when>
			<c:when test="${message == '예약 완료'}" >
					<script>
					alert('스터디 룸 예약이 완료되었습니다.');
					</script>
			</c:when>
			
			<c:when test="${message == '가입 축하'}" >
					<script>
					alert('가입을 축하합니다 로그인 하세요.');
					</script>
			</c:when>
			
			<c:when test="${message == '로그인 필요'}" >
					<script>
					alert('로그인이 필요한 작업입니다!!');
					</script>
			</c:when>
			
			<c:when test="${message == '예약자 조회를 위해선 로그인이 필요'}" >
					<script>
					alert('로그인이 필요한 작업입니다');
					</script>
			</c:when>
		</c:choose>

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
		 

		<section class="banner">
   				 <img src="resources/images/study_room1.jpg" alt="study_banner"> 
		</section>

		<nav class="main-nav">
  			  <ul>
    		  	 <c:choose>
				<c:when test="${customer.customerId == null}" >
					 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=join" class="join">회원가입</a></li>
				</c:when>
					<c:when test="${customer.customerId != null}" >
						<li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=home&id=${customer.customerId}" class="join">홈으로</a></li>

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
    		  	 
      	 		 <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent&id=${customer.customerId}" class="news">예약하기</a>
      		     <li><a href="http://localhost:8080/sangmun_free/CustomerServlet?key=rent_list&id=${customer.customerId}"  class="contact">전체 예약정보</a></li>
  			 </ul>
		</nav>
		
			
		<main>
  			  <article>
       			 <h2>혼자만의 공간</h2>
       			 <p>코로나 시대에 카페가서 공부하기는 좀 그렇고...<br>
       			 혼자 공부할 공간을 찾으시나요?<br>
       			 국내 최대 스터디 룸 100개의 방 구비 완료.<br>
       			 지금 바로 예약해보세요!</p>
    		</article>
    		
    <ul class="images">
        <li><img src="resources/images/study_room2.jpg" alt="room2"></li>
        <li><img src="resources/images/study_room3.jpg" alt="room3"></li>
        <li></li>
        <li></li>
    </ul>
		</main>

	<section class="join">
   			 <h2>Study is always fun!</h2>
   			 <p>Study Alone에서 지금 바로 공부해보세요!</p>
      
	</section>
		<footer><br></footer>
</body>
</html>