<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String showUrl = basePath + "pages/back/admin/emp/EmpServlet/show";
String deptShowUrl = basePath + "pages/back/admin/dept/DeptServlet/show";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>雇员管理程序</title>
<base href="<%=basePath %>">
<link type="text/css" rel="stylesheet" href="css/ppcss.css">
<script type="text/javascript" src="js/ppjs.js"></script>
<script type="text/javascript" src="js/emp.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
</head>
<body>
	<c:if test="${emp != null}" var="res">
		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2"
			width="100%">
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td colspan="3"><span>查看雇员信息</span></td>
			</tr>

			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td width="20%">雇员编号:</td>
				<td width="40%">${emp.empno}</td>
				<td width="40%">雇员照片</td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>姓名:</td>
				<td>${emp.ename}</td>
				<td rowspan="8"><img src="upload/${emp.photo}"></td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>职位:</td>
				<td>${emp.job}</td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>雇员领导:</td>
				<td>
				<a href="<%=showUrl%>?empno=${emp.mgr.empno}">${emp.mgr.ename}</a></td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>所在部门:</td>
				<td><a href="<%=deptShowUrl%>?deptno=${emp.dept.deptno}">${emp.dept.dname}</a></td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>雇佣日期:</td>
				<td>${emp.hiredate}</td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>薪资:</td>
				<td>${emp.sal}</td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>绩效:</td>
				<td>${emp.comm}</td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td>雇员简介:</td>
				<td></td>
			</tr>
			<tr onmouseover="changeColor(this,'#FFFFFF')"
				onmouseout="changeColor(this,'#F2F2F2')">
				<td colspan="3">${emp.note}</td>
			</tr>
		</table>
	</c:if>
	<input type="button" value="关闭窗口" onclick="closePage()">
</body>
</html>
