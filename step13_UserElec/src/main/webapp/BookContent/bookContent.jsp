<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <jsp:include page="../common/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> ${book.bookName} 책 소개 페이지</h1>
 
<!--  request.setAttribute("book", selectedbook); -->
<table width="100%" border="1" height="250">
<tr>
	<th width="100" rowspan="4"><img src="images/${book.isbn}.PNG" alt="불편한 편의점"></th>
	<th class="text-center">${book.bookName}</th>
</tr>

<tr>
<td class="text-center">${book.wirter}</td>
</tr>

<tr>
<td class="text-center">${book.price} 원</td>
</tr>
<tr>
<td class="text-center"><button id ="btn" onclick="Buy();" class="btn btn-primary">구매하기</button>
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
<td class="text-center">${book.description}</td>
</tr>
</table>
</body>
</html>
 <jsp:include page="../common/footer.jsp"/>