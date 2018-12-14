<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/back/admin/emp/EmpServlet/insertPre";
String deleteUrl = basePath + "pages/back/admin/emp/EmpServlet/delete";
String updateUrl = basePath + "pages/back/admin/emp/EmpServlet/updatePre";
//String backUrl = basePath + "pages/back/admin/emp/EmpServlet/list";//使用参数传递,实现不同其实页面返回不同路径;例=从[emp_list.jsp]至->增加/修改操作后->返回至[emp_list.jsp],而不是[emp_list_split.jsp];
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>雇员管理程序</title>
    <base href="<%=basePath %>">
    <link type="text/css" rel="stylesheet" href="css/ppcss.css">
    <script type="text/javascript" src="js/ppjs.js"></script>
  </head>
  <body>
  <div id="dataDiv">
  <table border="1" cellpadding="5" cellspacing="0" width="100%" bgcolor="#F2F2F2">
  		 <tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td colspan="8"><span>雇员信息</span></td>
		</tr>
  		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td width="10%"><input type="checkbox" id="selall" onclick="checkboxSelect(this,'empno')"></td>
			<td width="10%"><strong>雇员编号</strong></td>
			<td width="20%"><strong>雇员名称</strong></td>
			<td width="10%"><strong>职位</strong></td>
			<td width="20%"><strong>雇佣日期</strong></td>
			<td width="10%"><strong>薪资</strong></td>
			<td width="10%"><strong>绩效</strong></td>
			<td width="10%"><strong>操作</strong></td>
		</tr>
		<c:if test="${allEmps != null}" var="res">
			<c:forEach items="${allEmps}" var="t_emp">
		  		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
					<td><input type="checkbox" id="empno" name="empno" value="${t_emp.empno}:${t_emp.photo}"></td>
					<td>${t_emp.empno}</td>
					<td>${t_emp.ename}</td>
					<td>${t_emp.job}</td>
					<td>${t_emp.hiredate}</td>
					<td>${t_emp.sal}</td>
					<td>${t_emp.comm}</td>
					<td><a href="<%=updateUrl%>?empno=${t_emp.empno}&backurl=${url}">修改</a></td>
				</tr>
			</c:forEach>
		</c:if>
  	</table>
  	</div>
  	<div>
	<input type="button" value="删除雇员信息" onclick="deleteAll('<%=deleteUrl%>?backurl=${url}','eno','empno')">
	<a href="<%=insertUrl %>">增加新雇员</a>
  	</div>
  </body>
</html>
