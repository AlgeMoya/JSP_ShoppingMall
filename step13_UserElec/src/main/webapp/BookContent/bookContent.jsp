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
<h1> ${booklist.bookname} å �Ұ� ������</h1>
<table width="100%" border="1" height="250">
<tr>
	<th rowspan="4"><img src="../images/ddd.jpg">å ǥ��</th>
	<th>å �Ұ�</th>
</tr>

<tr>
<td>å ����</td>
</tr>

<tr>
<td>����</td>
</tr>
<tr>
<td><button id ="btn" onclick="Buy();">�����ϱ�</button>
<script>
function Buy(){
	alert("���ŵǼ̽��ϴ�");
}
</script>
</td>
</tr>
</table>


<table width="100%" border="1" height="180">
<tr>
<td> å ����</td>
</tr>
</table>
</body>
</html>
 <jsp:include page="../common/footer.jsp"/>