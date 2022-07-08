<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp"/>

<style>
#login{
z-index:150;}
</style>

<c:choose>
	<c:when test="${empty loginUser}">
		<form id=login class="form-horizontal" method="post" action="${pageContext.request.contextPath}/front">
			<input type="hidden" name="key" value = "user" /> <!-- Controller를 찾는 정보 -->
			<input type="hidden" name="methodName" value = "login" />  <!-- 메소드이름 -->
			<fieldset>
				<legend>로그인</legend>
				<div class="form-group" id=login>
					<label for="userId" class="col-lg-2 control-label">ID</label>
					<div class="col-lg-10">
						<input type="text" class="form-control" id="userId" name="userId"
							placeholder="ID 입력">
					</div>
				</div>
				<div class="form-group">
					<label for="pwd" class="col-lg-2 control-label">비밀번호</label>
					<div class="col-lg-10">
						<input type="password" class="form-control" id="pwd" name="pwd"
							placeholder="비밀번호 입력">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button class="btn btn-default">취소</button>
						<button type="submit" class="btn btn-primary">로그인</button>
					
					</div>
				</div>
			</fieldset>
		</form>
	</c:when>
	<c:otherwise>
			<legend>Login</legend>
			<blockquote>
				<p>My Shopping Mall!</p>

				<div class="alert alert-dismissible alert-warning">
					<button type="button" class="close" data-dismiss="alert">Ã</button>
					<h4>Welcome!</h4>
					<p>
						<a href="#" class="alert-link">${loginUser} / ${loginName}</a>.
					</p>
				</div>
			</blockquote>
	</c:otherwise>
</c:choose>

<jsp:include page="../common/footer.jsp"/>

