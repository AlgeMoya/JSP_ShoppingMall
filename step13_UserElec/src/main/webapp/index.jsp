<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <jsp:include page="common/header.jsp"/>
    <!-- 수정 예정 
    제어문 사용해서 가격이 15000원 이상인 것들만 띄우기, 
    이미지, 책이름, 저자 정도만 나오게 하기 
    교보 문고처럼 세로로 나올 수 있게 하기-->
    <!-- 참고 링크 : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=fourleaf0311&logNo=140114369236
         참고 링크2 : https://earth-ing.tistory.com/20 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team5</title>
</head>
<body>
 <blockquote>
 5팀 메인 화면
 <table width = "100%" border="3">
 <tr>
 <td>책표지</td>
 <td>책이름</td>
 <td>책분류</td>
 <td>저자</td>
  <td>출판사</td>
   <td>출판일</td>
 <td>가격</td>
  <td>책 소개</td>
 </tr>
 <%
 Class.forName("oracle.jdbc.driver.OracleDriver");
 
 Connection conn=null;
 Statement stmt=null;
 ResultSet rs=null;
 
 try{
	 String jdbcDriver="jdbc:oracle:thin:@localhost:1521:XE";
	 String dbUser = "user1";//db 아이디
	 String dbPass = "1234"; //db 비번
	 
	 String query ="select * from BOOKTABLE";
	conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
	// 3. Statement 생성
    stmt = conn.createStatement();

    // 4. 쿼리 실행
    rs = stmt.executeQuery(query);

    // 5. 쿼리 실행 결과 출력
    while(rs.next())
    {
%>
<tr>
    <td><%= rs.getString("ISBN") %></td>
    <td><%= rs.getString("BOOKNAME") %></td>
    <td><%= rs.getString("BOOKTYPE") %></td>
    <td><%= rs.getString("WRITER") %></td>
    <td><%= rs.getString("PUBLISHER") %></td>
    <td><%= rs.getString("PUBLICATION") %></td>
    <td><%= rs.getString("PRICE") %></td>
    <td><%= rs.getString("DESCRIPTION") %></td>
</tr>
<%
    }
}catch(SQLException ex){
    out.println(ex.getMessage());
    ex.printStackTrace();
}finally{
    // 6. 사용한 Statement 종료
    if(rs != null) try { rs.close(); } catch(SQLException ex) {}
    if(stmt != null) try { stmt.close(); } catch(SQLException ex) {}

    // 7. 커넥션 종료
    if(conn != null) try { conn.close(); } catch(SQLException ex) {}
}%>
</table>
  
</blockquote>
</body>
</html>
 <jsp:include page="common/footer.jsp"/>
