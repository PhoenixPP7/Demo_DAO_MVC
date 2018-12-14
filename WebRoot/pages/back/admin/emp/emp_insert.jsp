<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String insertUrl = basePath + "pages/back/admin/emp/EmpServlet/insert";
String listUrl = basePath + "pages/back/admin/emp/EmpServlet/list";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>雇员管理程序</title>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="css/ppcss.css">
<script type="text/javascript" src="js/ppjs.js"></script>
<script type="text/javascript" src="js/emp.js"></script>
<script type="text/javascript" src="<%=basePath%>js/WdatePicker.js"></script>
</head>
<body>
  	<form action="<%=insertUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
  		<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2" width="100%">
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td colspan="4"><span>增加雇员信息</span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td width="20%">雇员编号:</td>
  				<td width="20%"><input type="text" id="empno" name="empno" class="init" onblur="return validateEmpno()"></td>
  				<td width="20%"><span id="empnoMsg"></span></td>
  				<td width="40%">雇员照片</td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>姓名:</td>
  				<td><input type="text" id="ename" name="ename" class="init" onblur="return validateEname()"></td>
  				<td><span id="enameMsg"></span></td>
  				<td rowspan="10"><div id="preview"></div></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>职位:</td>
  				<td><input type="text" id="job" name="job" class="init" onblur="return validateJob()"></td>
  				<td><span id="jobMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>雇员领导:</td>
  				<td>
  					<select name="mgr" id="mgr" class="init">
  						<option value="0">=====没有对应的雇员领导=====</option>
  						<c:if test="${allEmps != null}" var="res">
  							<c:forEach items="${allEmps}" var="t_emp">
  								<option value="${t_emp.empno}">${t_emp.ename}</option>
  							</c:forEach>
  						</c:if>
  					</select>
  				</td>
  				<td><span id="mgrMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>所在部门:</td>
  				<td>
  					<select name="deptno" id="deptno" class="init">
  						<option value="0">=====没有对应的部门信息=====</option>
  						<c:if test="${allDepts != null}" var="res">
  							<c:forEach items="${allDepts}" var="t_dept">
  								<option value="${t_dept.deptno}" ${t_dept.deptno == param.deptno ? "selected" : "" }>${t_dept.dname}</option>
  							</c:forEach>
  						</c:if>
  					</select>
  				</td>
  				<td><span id="deptnoMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>雇佣日期:</td>
  				<td><input type="text" id="hiredate" name="hiredate" class="init" onclick="WdatePicker()" readonly="readonly" ></td>
  				<td><span id="hiredateMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>薪资:</td>
  				<td><input type="text" id="sal" name="sal" class="init" onblur="return validateSal()"></td>
  				<td><span id="salMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>绩效:</td>
  				<td><input type="text" id="comm" name="comm" class="init" onblur="return validateComm()"></td>
  				<td><span id="commMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>雇员照片</td>
  				<td><input type="file" id="photo" name="photo" class="init" onchange="preview(this)" ></td>
  				<td><span id="fileMsg"></span></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td>雇员简介</td>
  				<td colspan="2"><input type="textarea" id="note" name="note"></td>
  			</tr>
  			<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
  				<td colspan="4">
	  				<input type="submit" value="增加">
	  				<input type="reset" value="重置">
	  				<a href="<%=listUrl%> ">雇员信息</a>
  				</td>
  			</tr>
  		</table>
  	</form>
  </body>
</html>
