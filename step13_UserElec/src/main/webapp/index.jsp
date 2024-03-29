<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- c 외의 다른 것으로 할 수는 있지만 권장하지 않는다. -->
    <jsp:include page="common/header.jsp"/>
    <!-- 수정 예정 
    제어문 사용해서 가격이 15000원 이상인 것들만 띄우기, 
    ISBN값에 맞춰서 이미지가 출력될 수 있게 하기, 표 글씨 중앙정렬, 화면 크기에 맞춰서 크기가 조절될 수 있게 하기
    교보 문고처럼 세로로 나올 수 있게 하기-->
    <!-- 참고 링크 : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=fourleaf0311&logNo=140114369236 테이블에 DB값 받아서 출력하기
         참고 링크2 : https://earth-ing.tistory.com/20 DB 연동
	 참고 링크3 : https://rsorry.tistory.com/169  테이블 안에 이미지 넣기-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team5</title>
<script type="text/javascript">
    function viewBookDesc(bookname){
     	document.f.bookName.value=bookname;
    	document.f.submit();
    }
  </script>
</head>
<body>
 <blockquote>
 <h1>이번 주 인기 서적</h1>
 <table style ="text-align:center" width = "100%" border="3">
 <tr>
 <th>책표지</th>
 <th>책이름</th>
 <th>책분류</th>
 <th>저자</th>
 <th>상세정보</th>
 </tr>
 
  <c:forEach items="${applicationScope.bestseller}" var="booklist" >
  	<tr>
    	<td align=center><img src="images/${booklist.isbn}.PNG" width="100", height="100" alt="불편한 편의점"></td>
    	<td align=center>${booklist.bookName}</td>
    	<td align=center>${booklist.bookType}</td>
   		<td align=center>${booklist.wirter}</td>
	    <td align=center><button style="background-color : #58ACFA" onclick="viewBookDesc('${booklist.bookName}')">상세정보</button></td>
	</tr>
     <!-- <input type="radio" name="nation" value="${booklist.isbn}" /> ${booklist.bookName}  -->
  </c:forEach>
</table>
  
</blockquote>
<form action="${pageContext.request.contextPath}/front" method="post" name="f">
  <input type="hidden" name="key" value="book"/> <!-- actionMapping.properties의 sku.mvc.controller.BookController -->
  <input type="hidden" name="methodName" value="viewBookDesc"/> <!-- 메소드 호출 -->
  <input type="hidden" name="bookName" />
</form>
</body>
</html>
 <jsp:include page="common/footer.jsp"/>
