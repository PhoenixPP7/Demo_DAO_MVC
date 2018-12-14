<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理页面</title>

<script src="js/prototype.lite.js" type="text/javascript"></script>
<script src="js/moo.fx.js" type="text/javascript"></script>
<script src="js/moo.fx.pack.js" type="text/javascript"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(images/menu_bgS.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height:15px;
	margin:0px;
	overflow:hidden;
	padding:0px;
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top"><div id="container">
      <h1 class="type"><a href="javascript:void(0)">魔乐科技</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="" target="main">作者：MLDN</a></li>
          <li><a href="#" target="main">www.MLDN.cn</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">雇员管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM"><!-- EmpServlet?status=insertpre -->
          <li><a href="<%=basePath%>pages/back/admin/emp/emp_insert.jsp" target="main">增加雇员</a></li>
          <li><a href="<%=basePath%>pages/back/admin/emp/emp_list.jsp" target="main">雇员列表</a></li>
          <li><a href="<%=basePath%>pages/back/admin/emp/emp_list_split.jsp" target="main">分页列表</a></li>
          <li><a href="<%=basePath%>pages/back/admin/emp/emp_list_details.jsp" target="main">详细列表</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">部门管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="<%=basePath%>pages/back/admin/dept/dept_insert.jsp" target="main">增加部门列表</a></li>
		  <li><a href="<%=basePath%>pages/back/admin/dept/dept_list.jsp" target="main">部门列表</a></li>
		  <li><a href="<%=basePath%>pages/back/admin/dept/dept_list_details.jsp" target="main">详细列表</a></li>
        </ul>
      </div>
       <h1 class="type"><a href="javascript:void(0)">商品管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="admin/product/ProductServlet?status=insertpre" target="main">增加商品</a></li>
		  <li><a href="admin/product/ProductServlet?status=list" target="main">商品列表</a></li>
        </ul>
      </div>
       <h1 class="type"><a href="javascript:void(0)">商品类别管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="admin/types/types.do?status=list" target="main">类别管理</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">商品子类别管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="admin/subtypes/subtypes.do?status=insertpre" target="main">添加子类别</a></li>
		  <li><a href="admin/subtypes/subtypes.do?status=list" target="main">子类别列表</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">权限管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="admin/privilege/PrivilegeServlet?status=list" target="main">权限列表</a></li>
		  <li><a href="admin/admingroup/AdmingroupServlet?status=list" target="main">管理员组列表</a></li>
		  <li><a href="admin/admingroup/AdmingroupServlet?status=insertpre" target="main">增加管理员组</a></li>
		  <li><a href="admin/admin/AdminServlet?status=list&temppid=14" target="main">管理员列表</a></li>
		  <li><a href="admin/admin/AdminServlet?status=insertpre&temppid=1" target="main">增加管理员</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">新闻管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="admin/news/news_insert.jsp" target="main">增加新闻</a></li>
		  <li><a href="admin/news/NewsServlet?status=list" target="main">所有新闻列表</a></li>
		  <li><a href="admin/news/NewsServlet?status=listlock" target="main">锁定新闻列表</a></li>
		  <li><a href="admin/news/NewsServlet?status=listunlock" target="main">活动新闻列表</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">注册用户管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="#" target="main">会员管理</a></li>
          <li><a href="#" target="main">留言管理</a></li>
          <li><a href="#" target="main">回复管理</a></li>
          <li><a href="#" target="main">订单管理</a></li>
          <li><a href="#" target="main">举报管理</a></li>
          <li><a href="#" target="main">评论管理</a></li>
        </ul>
      </div>
    </div>

        <script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[0]);
	</script>
        </td>
  </tr>
</table>
</body>
</html>
