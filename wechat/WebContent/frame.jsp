<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天室</title>
</head>
<%
	if(session.getAttribute("username")==null)
		response.sendRedirect("login.jsp");
%>
<frameset cols="25%,75%">
	<frameset rows="20%,*">
		<frame src="back.jsp" />
	<frame src="frame_a.jsp" />
	</frameset>
  
  <frameset rows="75%,25%">
  	<frame src="frame_b.jsp" /> 
  	<frame src="frame_c.html" />
</frameset>
</frameset>
<body>

</body>
</html>