<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript">
function check(){
	var name=document.form1.name.value
	var password=document.form1.password.value
	if(name==""||password=="")
		return false
	else
		return true
}
function initAjax(){
	var xmlhttp;
	try
	   {
	  // Firefox, Opera 8.0+, Safari
	   xmlhttp=new XMLHttpRequest();
	   }
	catch (e)
	   {

	 // Internet Explorer
	  try
	     {
	     xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
	     }
	  catch (e)
	     {
	     try
	        {
	        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	        }
	     catch (e)
	        {
	        alert("您的浏览器不支持AJAX！");
	        }
	     }
	   }
	return xmlhttp;
}
function checkname(){
	if(check()){
	var httprequest=initAjax();
	var name=document.getElementById("name").value;
	var password=document.getElementById("password").value;
	var imagecheck=document.getElementById("imagecheck").value
	httprequest.open("post", "login", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4)
			{
			if(httprequest.status == 200) {
				if(httprequest.responseText==1)
					{
					document.getElementById("info").innerHTML="登录成功前去<a href='frame.jsp'>聊天</a>";
					window.location.href="frame.jsp";
					}
				else if(httprequest.responseText==2)
					document.getElementById("info").innerHTML="登录失败！验证码错误";
				else if(httprequest.responseText==0){
					document.getElementById("info").innerHTML="登录失败！检查密码是否有误";
				}
				
				
			}
			}
		
	};
	httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	var pr="name="+name+"&password="+password+"&imagecheck="+imagecheck;
	httprequest.send(pr);
	}
}
function changed() {
	document.getElementById("image").src="checkimage?"+Math.random();
	
}
</script>


</head>
<body>
<%
	if(session.getAttribute("username")!=null)
	{
		out.print("您已经登录！不能重复登录");
		response.addHeader("refresh", "3;frame.jsp");
		return;
	}
%>


<form action="" name="form1" method="post">
<table>
<tr><td colspan="3"><div id="info" style="color: red;"></div></td></tr>
<tr><td></td><td align="center" colspan="2">登录页面</td></tr>
<tr><td>昵称:</td><td colspan="2"><input name="name" id="name"></td></tr>
<tr><td>密码:</td><td colspan="2"><input type="password" name="password" id="password"></td></tr>
<tr><td>验证码:</td><td colspan="2"><input name="imagecheck" id="imagecheck"></td><td><img name="image" id="image" alt="" src="checkimage" onclick="changed()"></td></tr>
<tr><td></td><td><input type="button" value="登录" onclick="checkname()"></td><td><input type="button" value="注册" onclick="register()"></td></tr>
</table>
</form>
</body>
<script type="text/javascript">
 function register() {
		window.location.href="register.jsp";
	}
</script>
</html>