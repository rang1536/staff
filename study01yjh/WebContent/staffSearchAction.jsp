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
<h1>STAFF ���� ��ȸ</h1>
<div>
<form>
	<table>
		<tr>
			<th>�̸�</th>
			<th>�ֹι�ȣ</th>
			<th>����</th>
			<th>�з�</th>
			<th>���</th>
			<th>������</th>
			<th>�����׻���</th>
		</tr>
		<tr>
			<c:forEach var="i" items="${staff}">
				<td>${i.staffName}</td>
				<td>${i.staffSn}</td>
			</c:forEach>
		</tr>
	</table>
</form>	

</div>
</body>
</html>