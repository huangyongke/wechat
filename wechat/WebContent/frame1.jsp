<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

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
	var httprequest=initAjax();
	httprequest.open("post", "", true);
	httprequest.onreadystatechange = function(){
		if(httprequest.readyState==4)
			{
			if(httprequest.status == 200) {
				if(httprequest.responseText==1)
					{
					document.getElementById("info").innerHTML="登录成功前去<a href='frame.jsp'>聊天</a>";
					response.sendRedirect("frame.jsp");
					}
				else if(httprequest.responseText==0){
					document.getElementById("info").innerHTML="登录失败！检查密码是否有误";
				}
				
			}
			}
		
	};
	httprequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
	httprequest.send(pr);
}

</script>
</head>
<body>
<div >
<div style="border:1px solid #000; width: 25%;height:100%; float: left;" ><div ></div></div>
<div style="border:1px solid #000;float:left;width:70%;height:100%">谁说的</div>

</div>
</body>
</html>