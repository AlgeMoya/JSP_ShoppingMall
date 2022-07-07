<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping</title>

<link rel="StyleSheet" 
href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<script 
src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

</head>
<body>
<div class="navbar navbar-default">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="${path}/index.jsp">첫 화면</a>
    <!-- 여기에 총류 서블릿 입력 -->
    <a class="navbar-brand" href="${path}/">총류</a>
    <a class="navbar-brand" href="${path}/">철학</a>
    <!-- <button type="submit" style="float:right;"onclick="location.href='${path}/user/login.jsp'">로그인</button>  -->
  </div>
  
  <div class="navbar-collapse collapse navbar-responsive-collapse">

     
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
      <li><a href="${pageContext.request.contextPath}/user/login.jsp">Login</a></li>
      <!-- dispatcher 서블릿으로 들어갈 이유가 없는데도 들어가는 이유는 로그인 -->
      <li><a href="${pageContext.request.contextPath}/front">Board</a></li>
     </ul>
     </c:otherwise>
     </c:choose>
     
     
  </div>
</div>



