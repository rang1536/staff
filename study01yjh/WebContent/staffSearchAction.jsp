<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<c:import url="/staffSearchForm.jsp"></c:import>
<br/><br/><br/><br/>
<div>
<form>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>주민번호</th>
			<th>졸업일</th>
			<th>학력</th>
			<th>종교</th>
			<th>수정및삭제</th>
		</tr>
		<c:forEach var="i" items="${staffList}">
			<tr>
				<td>${i.staffName}</td>
				<td>${i.staffSn}</td>
				<td>${i.graduateday}</td>
				<td>${i.schoolGraduate}</td>
				<td>${i.religionName}</td>
				<td><a href="<c:url value="/StaffUpdateServlet?staffNo=${i.staffNo}"/>">수정,</a>
				<a href="<c:url value="/StaffUpdateServlet?staffNo=${i.staffNo}"/>">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</form>	

</div>
</body>
</html>