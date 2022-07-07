<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team5 Book Shopping</title>

<link rel="StyleSheet" 
href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<script 
src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

</head>
<readonly onfocus="this.blur();">
<body>
<div class="navbar navbar-default">
  <div class="navbar-header">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
     <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">첫 화면</a>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">총류</a> 
    <a class="navbar-brand" href="${pageContext.request.contextPath}">철학</a>
    <button type="submit" style="float:right;"onclick="location.href='${pageContext.request.contextPath}/user/login.jsp'">로그인</button>
  </div>

</div>
