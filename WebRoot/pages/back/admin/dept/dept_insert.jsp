<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/back/admin/dept/DeptServlet/insert";
String listUrl = basePath + "pages/back/admin/dept/DeptServlet/list";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>雇员管理程序</title>
    <base href="<%=basePath %>">
    <link type="text/css" rel="stylesheet" href="css/ppcss.css">
    <script type="text/javascript" src="js/ppjs.js"></script>
    <script type="text/javascript" src="js/dept.js"></script>
  </head>
  <body>
  	<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()">
  		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2" width="100%">
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td colspan="3"><span>增加部门信息</span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td width="20%">部门编号:</td>
  				<td width="40%"><input type="text" id="deptno" name="deptno" class="init" onblur="return validateDeptno()"></td>
  				<td width="40%"><span id="deptnoMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>部门名称:</td>
  				<td><input type="text" id="dname" name="dname" class="init" onblur="return validateDname()"></td>
  				<td><span id="dnameMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>部门位置:</td>
  				<td><input type="text" id="loc" name="loc" class="init" onblur="return validateLoc()"></td>
  				<td><span id="locMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td colspan="3">
	  				<input type="submit" value="增加">
	  				<input type="reset" value="重置">
	  				<a href="<%=listUrl%> ">部门信息</a>
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
