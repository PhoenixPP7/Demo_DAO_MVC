<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String listUrl = basePath + "pages/back/admin/emp/EmpSerlet/listSplit";
String updateUrl = basePath + "pages/back/admin/emp/EmpServlet/update";
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
<c:if test="${emp != null}" var="res">
<form action="<%=updateUrl%>" method="post" onsubmit="return validateUpdate()" enctype="multipart/form-data">
	<table border="1" cellpadding="5" cellspacing="0" bgcolor="#F2F2F2" width="100%">
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td colspan="4"><span>修改雇员信息</span></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td width="20%">雇员编号:</td>
			<td width="20%">${emp.empno}</td>
			<td width="20%"><span id="empnoMsg"></span></td>
			<td width="40%">雇员照片</td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>姓名:</td>
			<td><input type="text" id="ename" name="ename" class="init" onblur="return validateEname()" value="${emp.ename}"></td>
			<td><span id="enameMsg"></span></td>
			<td rowspan="10"><div id="preview"><img src="upload/${emp.photo}"></div></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>职位:</td>
			<td><input type="text" id="job" name="job"
				class="init" onblur="return validateJob()" value="${emp.job}"></td>
			<td><span id="jobMsg"></span></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>雇员领导:</td>
			<td>
				<select name="mgr" id="mgr" class="init">
					<option value="0">=====没有对应的雇员领导=====</option>
					<c:if test="${allEmps != null}" var="res">
						<c:forEach items="${allEmps}" var="t_emp">
							<option value="${t_emp.empno}" ${emp.mgr.empno == t_emp.empno ? "selected" : ""}>${t_emp.ename}</option>
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
							<option value="${t_dept.deptno}" ${t_dept.deptno == emp.dept.deptno ? "selected" : "" }>${t_dept.dname}</option>
						</c:forEach>
					</c:if>
				</select>
			</td>
			<td><span id="deptnoMsg"></span></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>雇佣日期:</td>
			<td><input type="text" id="hiredate" name="hiredate" class="init" onclick="WdatePicker()" value="${emp.hiredate}" readonly="readonly"></td>
			<td><span id="hiredateMsg"></span></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>薪资:</td>
			<td><input type="text" id="sal" name="sal" class="init" onblur="return validateSal()" value="${emp.sal}"></td>
			<td><span id="salMsg"></span></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>绩效:</td>
			<td><input type="text" id="comm" name="comm" class="init" onblur="return validateComm()" value="${emp.comm}"></td>
			<td><span id="commMsg"></span></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>雇员照片</td>
			<td><input type="file" id="photo" name="photo" class="init" onchange="preview(this)" ></td>
			<td><span id="fileMsg"></span></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td>雇员简介</td>
			<td colspan="3"><input type="textarea" id="note" name="note" value="${emp.note}"></td>
		</tr>		
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td colspan="4">
				<input type="hidden" id="empno" name="empno" value="${emp.empno}">
				<input type="hidden" id="oldpic" name="oldpic" value="${emp.photo}">
				
				<input type="hidden" id="backurl" name="backurl" value="<%=request.getParameter("backurl")%>">
				<input type="hidden" id="currentPage" name="currentPage" value="<%=request.getParameter("currentPage")%>">
				<input type="hidden" id="lineSize" name="lineSize" value="<%=request.getParameter("lineSize")%>">
				<input type="hidden" id="keyWord" name="keyWord" value="<%=request.getParameter("keyWord")%>">
				<input type="hidden" id="column" name="column" value="<%=request.getParameter("column")%>">				
				<%-- 
				<input type="hidden" id="backurl" name="backurl" value="${param.backurl}">
				<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}">
				<input type="hidden" id="lineSize" name="lineSize" value="${param.lineSize}">
				<input type="hidden" id="keyWord" name="keyWord" value="${param.keyWord}">
				<input type="hidden" id="column" name="column" value="${param.column}">
				 --%>
				<input type="submit" value="修改">
				<input type="reset" value="重置">
				<a href="<%=listUrl%> ">雇员信息</a>
			</td>
		</tr>
	</table>
</form>
</c:if>
</body>
</html>
