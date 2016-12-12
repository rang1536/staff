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
	<form action = "<c:url value='/StaffSearchServlet'/>" method="post">
		<table border="1">
			<tr>
				<th>�̸�</th>
				<th>
					<input type="text" name="staffName" id="staffName" />
				</th>
				<th>����</th>
				<th>
					<input type="checkbox" name="gender" value="1"/>��
					<input type="checkbox" name="gender" value="2"/>��
				</th>
				<th>����</th>
				<th>
					
					<select name="religion">
						<c:forEach  var="i" items="${alr}">
							<option value="${i.religionNo}">${i.religionName}</option>
					
						</c:forEach>
					</select>
					
				</th>
			</tr>
			<tr>
				<td>�з�</td>
				<td>
					<c:forEach var="i" items="${als}">
						<input type="radio" name="schoolGraduate" value="${i.schoolNo}"/>${i.schoolGraduate}
					</c:forEach>
				</td>
				<td>���</td>
				<td colspan="3">
					<c:forEach var="i" items="${alSkill}">
						<input type="checkbox" name="skillName" value="${i.skillName}"/>${i.skillName}
						
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>������</td>
				<td colspan="5">
					<input type="date" name="graduateDayStart" />~
					<input type="date" name="graduateDayLast" />
				</td>
			</tr>
			<tr>
				<center>
				<td  colspan = "6">
					<input type="submit" value="��ȸ" id="submit" />
					
				</td>
				</center>
			</tr>
		</table>
	
	</form>
</div>
</body>
</html>