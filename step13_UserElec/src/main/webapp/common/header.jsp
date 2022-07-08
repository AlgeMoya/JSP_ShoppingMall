<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 드롭 메뉴 스타일 참고 : https://blog.naver.com/PostView.naver?blogId=bgpoilkj&logNo=221150512824
 로그인 메뉴만 오른쪽으로 옮기기 : https://demun.tistory.com/1771-->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team5 Book Shopping</title>

<link rel="StyleSheet" 
href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<script 
src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

<style type="text/css">
*{
margin : 0;
padding : 0;
}
ul li{
	list-style : none;
}
a{ text-decoration:none;
color:white;
}
#menu{
font: bold 16px ""malgun gothic";
width:700px;
height:50px;
background:#7FCCFF;
color:black;
line-height:50px;
margin:0 auto;
text-align:center;
}
#menu > ul > li{
float : left;
width: 140px;
position:relative;
}
#menu > ul>li>ul{
width: 140px;
display:none;
position:absolute;
font-size:14px;
background:#B7E3FF;
}
#menu > ul>li:hover > ul{
display:block;
}
#menu > ul > li> ul>li:hover {
	background : #9999FF;
	transition:ease 1s;
}
#menu > ul >li:hover{
background : #9999FF;}
.login {
text-align:center;
float:right;}
#menu li.login{float:right;}
</style>

</head>
<body>
<div id="menu">
	<ul>
		<li class="first screen"><a href="${pageContext.request.contextPath}/index.jsp">첫 화면</a></li>
		<li class="book"><a href="#">책 분류</a>
			<ul>
				<li><a href="${pageContext.request.contextPath}/content/print000.jsp">총류</a></li>
				<li><a href="${pageContext.request.contextPath}/">철학</a></li>
				<li><a href="${pageContext.request.contextPath}/">종교</a></li>
				<li><a href="${pageContext.request.contextPath}/">사회과학</a></li>
				<li><a href="${pageContext.request.contextPath}/">자연과학</a></li>
				<li><a href="${pageContext.request.contextPath}/">기술과학</a></li>
				<li><a href="${pageContext.request.contextPath}/">예술</a></li>
				<li><a href="${pageContext.request.contextPath}/">문학</a></li>
				<li><a href="${pageContext.request.contextPath}/">역사</a></li>
			</ul>
		</li>

		<!-- 로그인이 되어있다면 로그아웃 버튼을 생성한다. -->
     <c:choose>
     <c:when test="${not empty loginUser}">
	     <ul class="nav navbar-nav navbar-right">
	     <!-- 사용자 ID -->
	     	<li class="active"><a href="${pageContext.request.contextPath}/mypage">${loginUser.userId}!</a></li>
	     	<!-- 로그아웃 버튼 -->
	     	<!-- UserController의 logout을 호출 -->
	      	<li><a href="${pageContext.request.contextPath}/front?key=user&methodName=logout" class="btn btn-danger">로그아웃</a></li>
	     </ul>
     </c:when>
     <c:otherwise>
         <ul class="nav navbar-nav" style="float:right;">
     
     <!-- 로그인 화면으로 이동 -->
      <li><a href="${pageContext.request.contextPath}/user/login.jsp">로그인</a></li>
      <li><a href="${pageContext.request.contextPath}/user/registration.jsp">회원가입</a></li>
      <!-- dispatcher 서블릿으로 들어갈 이유가 없는데도 들어가는 이유는 로그인 -->
      <li><a href="${pageContext.request.contextPath}/front">마이페이지</a></li>
     </ul>
     </c:otherwise>
     </c:choose>
	</ul>
</div>
</head>
<body>
</body>
</html>


