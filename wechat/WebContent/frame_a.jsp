<%@page import="dba.DBAccess"%>
<%@page import="java.awt.image.BufferedImage" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.*" %>
<%@page import="java.io.*" %>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="dba.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function() 
{ 
var arr; 
if(arr=document.cookie.match(/scrollTop=([^;]+)(;|$)/)) 
document.documentElement.scrollTop=parseInt(arr[1]); 
document.body.scrollTop=parseInt(arr[1]); 
}
window.onbeforeunload=function(){ 
	var scrollPos=0; 
	if (typeof window.pageYOffset != 'undefined') { 
	   scrollPos = window.pageYOffset; 
	} 
	else if (typeof document.compatMode != 'undefined' && 
	     document.compatMode != 'BackCompat') { 
	   scrollPos = document.documentElement.scrollTop; 
	} 
	else if (typeof document.body != 'undefined') { 
	   scrollPos = document.body.scrollTop; 
	} 
	document.cookie="scrollTop="+scrollPos; 
	}

</script>
</head>
<body>
<table border="1" width="200" cellpadding="10" style="float:center;">

<%
	int counts=0;
	if(application.getAttribute("onlinecount")!=null)
		counts=((Integer)application.getAttribute("onlinecount")).intValue();
	%>
	<tr><td>在线人数：</td><td><%=counts %></td></tr>
	<%	
	if(application.getAttribute("onlineuser")!=null)
	{
	ArrayList<String> user=(ArrayList<String>)application.getAttribute("onlineuser");
	DBAccess db=new DBAccess();
	ArrayList<Users> users=db.getpicture(user);
	for(Users u:users)
	{
		/* InputStream buffin = new ByteArrayInputStream(u.getImages());
    	BufferedImage img = ImageIO.read(buffin);
    	Image image = img.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
		BufferedImage i=new BufferedImage(40, 30, BufferedImage.TYPE_INT_RGB);
    	Graphics g=i.getGraphics();
    	Color c=Color.WHITE;
    	g.drawRect(1, 1,40, 30);
    	g.drawImage(image, 0, 0, null);
   	 	g.dispose();
   	 out.clear();
   	out = pageContext.pushBody();
   	 	   */
    	
%>
	<tr><td><% /* ImageIO.write(i, "jpeg",response.getOutputStream()); */ %></td><td><%   
		 out.print(u.getUsername()); %></td></tr>
<%
	}
	}
	response.addHeader("refresh", "2");
%>
</table>
</body>
</html>