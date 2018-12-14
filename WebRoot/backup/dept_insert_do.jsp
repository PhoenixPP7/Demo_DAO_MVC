<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mvc.factory.*" %>
<%@ page import="mvc.vo.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String listUrl = basePath + "pages/back/admin/dept/dept_list.jsp";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>雇员管理程序</title>
    <base href="<%=basePath %>">
  </head>
  <body>
  <%
  	request.setCharacterEncoding("UTF-8");
  	Dept vo = new Dept();
  	vo.setDeptno(Integer.parseInt(request.getParameter("deptno")));
  	vo.setDname(request.getParameter("dname"));
  	vo.setLoc(request.getParameter("loc"));
  	String msg = "部门信息增加失败!";
  	if(ServiceFactory.getIDeptServiceInstance().insert(vo)){
  		msg = "部门信息增加成功!";
  	}
  %>
  <script type="text/javascript">
  	alert("<%=msg%>");
  	window.location = "<%=listUrl%>";
  </script>
  </body>
</html>
