<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <jsp:include page="../common/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- 총류 목록 -->
</head>
<body>
<blockquote>
 <h1>${booklist.booktype}분야 이번주 인기 서적</h1>
 <table width = "100%" border="3">
 <tr>
 <th>책표지</th>
 <th>책이름</th>
 <th>책분류</th>
 <th>저자</th>
 <th>상세정보</th>
 </tr>
 
  <c:forEach items="${applicationScope.bestseller}" var="booklist" >
  	<tr>
    	<td><img src="${path}/images/${booklist.isbn}.PNG" width="100", height="50"></td>
    	<td>${booklist.bookName}</td>
    	<td>${booklist.bookType}</td>
   		<td>${booklist.wirter}</td>
	    <td><td align=center><button style="background-color : #58ACFA">
	    <a href="bookContent.jsp">상세정보</a></button></td></td>
	</tr>
     <!-- <input type="radio" name="nation" value="${booklist.isbn}" /> ${booklist.bookName}  -->
  </c:forEach>
</table>
  
</blockquote>
</body>
</html>
 <jsp:include page="../common/footer.jsp"/>