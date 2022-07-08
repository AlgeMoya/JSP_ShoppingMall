<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <jsp:include page="../common/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

</head>
<body>
<h1> ${booklist.bookname} 책 소개 페이지</h1>
<table width="100%" border="1" height="250">
<tr>
	<th rowspan="4"><img src="../images/ddd.jpg">책 표지</th>
	<th>책 소개</th>
</tr>

<tr>
<td>책 제목</td>
</tr>

<tr>
<td>가격</td>
</tr>
<tr>
<td><button id ="btn" onclick="Buy();">구매하기</button>
<script>
function Buy(){
	alert("구매되셨습니다");
}
</script>
</td>
</tr>
</table>


<table width="100%" border="1" height="180">
<tr>
<td> 책 내용</td>
</tr>
</table>
</body>
</html>
 <jsp:include page="../common/footer.jsp"/>