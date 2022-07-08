<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../common/header.jsp"/>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 총류 목록 -->
<script type="text/javascript">
    function viewBookDesc(bookname){
     	document.g.bookName.value=bookname;
    	document.g.submit();
    }
  </script>
</head>
<body>
<blockquote>
 <h1>${bookType} 분야 이번주 인기 서적</h1>
 <table width = "100%" border="3">
 <tr>
 <th>책표지</th>
 <th>책이름</th>
 <th>책분류</th>
 <th>저자</th>
 <th>상세정보</th>
 </tr>
 <!--  request.setAttribute("list", bookList);  -->
  <c:forEach items="${list}" var="booklist" >
  <p></p>
  	<tr>
    	<td><img src="${path}/images/${booklist.isbn}.PNG" width="100" height="50"></td>
    	<td>${booklist.bookName}</td>
    	<td>${booklist.bookType}</td>
   		<td>${booklist.wirter}</td>
		<td align=center><button style="background-color : #58ACFA" onclick="viewBookDesc('${booklist.bookName}')">상세정보</button></td>
	</tr>
     <!-- <input type="radio" name="nation" value="${booklist.isbn}" /> ${booklist.bookName}  -->
  </c:forEach>
</table>

</blockquote>
</body>
<form action="${pageContext.request.contextPath}/front" method="post" name="g">
  <input type="hidden" name="key" value="book"/> <!-- actionMapping.properties의 sku.mvc.controller.BookController -->
  <input type="hidden" name="methodName" value="viewBookDesc"/> <!-- 메소드 호출 -->
  <input type="hidden" name="bookName" />
</form>
</html>
 <jsp:include page="../common/footer.jsp"/>