<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String url = (String) request.getAttribute("url");		//	url = /pages/back/admin/emp/EmpServlet/listDetails?currentPage=4&lineSize=5&keyWord=&column=ename
System.out.println("url = " + url);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>雇员管理程序</title> </head>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="css/ppcss.css">
<script type="text/javascript" src="js/ppjs.js"></script>
<body>
	<script type="text/javascript">
		alert("<%=request.getAttribute("msg")%>");
		window.location = "<%=basePath%><%=request.getAttribute("url")%>";
	</script>
</body>
</html>
