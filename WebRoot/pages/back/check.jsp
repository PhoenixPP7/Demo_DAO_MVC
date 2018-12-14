<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mvc.vo.*" %>
<%@ page import="mvc.factory.*" %>
<%@ page import="mvc.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String loginUrl = basePath + "pages/back/login.jsp";
String indexUrl = basePath + "pages/back/index.jsp";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>雇员管理程序</title>
    <base href="<%=basePath %>">
    <link type="text/css" rel="stylesheet" href="css/ppcss.css">
    <script type="text/javascript" src="js/ppjs.js"></script>
  </head>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%
	String msg = "验证码输入错误,请重新输入!";
	String url = loginUrl;
	String code = request.getParameter("code");
	String rand = (String) request.getAttribute("rand");
	/* 
	if (rand.equalsIgnoreCase(code)) { //判断验证码是否正确
		// 进行检查,须使用MD5进行密码的加密处理;
		Admin vo = new Admin();
		vo.setAid(request.getParameter("aid"));
		vo.setPassword(new MD5Code().getMD5ofStr(request.getParameter("password")));
		if (ServiceFactory.getIAdminServiceInstance().login(vo)) {//判断用户名和密码是否正确;
			msg = "登陆成功!";
			url = loginUrl;
		} else {
			msg = "登陆失败,请重新登陆!";
			url = indexUrl;
		}
	}
	 */
	Admin vo = new Admin();
	vo.setAid(request.getParameter("aid"));
	vo.setPassword(new MD5Code().getMD5ofStr(request.getParameter("password")));
	if (ServiceFactory.getIAdminServiceInstance().login(vo)) {//判断用户名和密码是否正确;
		session.setAttribute("aid",vo.getAid());//将登陆用户名保存至session属性,后续页面用于接受;
		msg = "登陆成功!";
		url = indexUrl;
	} else {
		msg = "登陆失败,请重新登陆!";
		url = loginUrl;
	}
%>
<script type="text/javascript">
	window.alert("<%=msg%>");
	window.alert("<%=url%>");
	window.loction = "http://localhost/demo_DAO_MVC/pages/back/index.jsp";
</script>

</html>
