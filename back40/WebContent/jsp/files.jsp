<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String dir = application.getRealPath("files");
	File f = new File(dir);
	File[] files = f.listFiles();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>files.jsp</title>
</head>
<body>
	<table>
		<%--
			for(File file : files){
				%>
				<tr>
					<td>
						<a href="/back25/download?name=${pageScope.file.getName}">
						${pageScope.file.getName}
						</a>
					</td>
				</tr>
				<%
			}
		--%>
		<c:forEach items="requestScope.files" var="file">
			<tr>
				<td>
					<a href="/back25/download?name=${file.name }">${file.name }</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>