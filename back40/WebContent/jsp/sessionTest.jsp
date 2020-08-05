<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	세션 ID : <%=session.getId() %><br>
	세션 isNew : <%=session.isNew() %><br>
	세션 사용 시간 : <%=session.getLastAccessedTime() %>
</body>
</html>