<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <jsp:include page="../common/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- �ѷ� ��� -->
</head>
<body>
<blockquote>
 <h1>${booklist.booktype}�о� �̹��� �α� ����</h1>
 <table width = "100%" border="3">
 <tr>
 <th>åǥ��</th>
 <th>å�̸�</th>
 <th>å�з�</th>
 <th>����</th>
 <th>������</th>
 </tr>
 
  <c:forEach items="${applicationScope.bestseller}" var="booklist" >
  	<tr>
    	<td><img src="${path}/images/${booklist.isbn}.PNG" width="100", height="50"></td>
    	<td>${booklist.bookName}</td>
    	<td>${booklist.bookType}</td>
   		<td>${booklist.wirter}</td>
	    <td><td align=center><button style="background-color : #58ACFA">
	    <a href="bookContent.jsp">������</a></button></td></td>
	</tr>
     <!-- <input type="radio" name="nation" value="${booklist.isbn}" /> ${booklist.bookName}  -->
  </c:forEach>
</table>
  
</blockquote>
</body>
</html>
 <jsp:include page="../common/footer.jsp"/>