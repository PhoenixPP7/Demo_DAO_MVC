<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mvc.factory.*" %>
<%@ page import="mvc.vo.*" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String insertUrl = basePath + "pages/back/admin/emp/emp_insert.jsp";
	String deleteUrl = basePath + "pages/back/admin/emp/emp_delete_do.jsp";
	String updateUrl = basePath + "pages/back/admin/emp/emp_update.jsp";
	String showUrl = basePath + "pages/back/admin/emp/emp_show.jsp";
	String deptShowUrl = basePath + "pages/back/admin/dept/dept_show.jsp";
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
<%//设置由外部接收的数据
	String url = basePath + "pages/back/admin/dept/dept_show.jsp";//设置返回路径,用于不同的起始页面,例=从[emp_list.jsp]至->增加/修改操作后->返回至[emp_list.jsp],而不是[emp_list_split.jsp];
	int currentPage = 1;//当前页
	String keyWord = "";//关键词,""表示默认全部查询;
	int lineSize = 5;//每页数据数
	int allRecorders = 0;//总记录数
	String column = "ename";//检索列,默认检索"ename"列;
	String columnData = "雇员编号:empno|雇员姓名:ename|雇员职位:job";//
	//int pageSize = 0 ;//总页数
	//int lsData [] = new int [] {1,5,10,15,20,30,50,100} ;//每页显示多少条数据
%>
<%//接收外部传递的参数
	try {//如果没有传入参数"currentPage",则是null,null无法转成"currentPage",故跳过此步;
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	} catch (Exception e) {}
	try {
	    lineSize = Integer.parseInt(request.getParameter("lineSize"));
	} catch (Exception e) {}
	if (request.getParameter("keyWord") != null){//表示查询关键字不为空;
	 keyWord = request.getParameter("keyWord") ;
	}
	if (request.getParameter("column") != null) {
	column = request.getParameter("column") ;
	}
%>

<%
	Dept dept = ServiceFactory.getIDeptServiceInstance().show(deptno, column, keyWord, currentPage, lineSize);
	List<Emp> all = dept.getEmps();
	allRecorders = (Integer) dept.getStat().get("count");
%>
<h1>雇员信息</h1>
	<table border="1" cellpadding="8" cellspacing="0" width="100%" bgcolor="#F2F2F2">
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td width="10%"><strong>部门编号</strong></td>
			<td width="10%"><strong>部门名称</strong></td>
			<td width="10%"><strong>部门位置</strong></td>
			<td width="10%"><strong>部门人数</strong></td>
			<td width="10%"><strong>工资合计</strong></td>
			<td width="10%"><strong>平均工资</strong></td>
			<td width="10%"><strong>最高工资</strong></td>
			<td width="10%"><strong>最低工资</strong></td>
		</tr>
		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td><%=dept.getDeptno()%></td>
			<td><%=dept.getDname()%></td>
			<td><%=dept.getLoc()%></td>
			<td><%=dept.getStat().get("count")%></td>
			<td><%=dept.getStat().get("sum")%></td>
			<td><%=dept.getStat().get("avg")%></td>
			<td><%=dept.getStat().get("max")%></td>
			<td><%=dept.getStat().get("min")%></td>
		</tr>
	</table>
	<hr>
	<div id="splitSearchDiv">
	    <jsp:include page="/pages/split_page_plugin_search.jsp">
	        <jsp:param name="columnData" value="<%=columnData%>"/>
	        <jsp:param name="column" value="<%=column%>"/>
	        <jsp:param name="keyWord" value="<%=keyWord%>"/>
	        <jsp:param name="allRecorders" value="<%=allRecorders%>"/>
	    </jsp:include>
	</div>
	<div id="dataDiv">
	<table border="1" cellpadding="5" cellspacing="0" width="100%" bgcolor="#F2F2F2">
  		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td width="10%"><input type="checkbox" id="selall" onclick="checkboxSelect(this,'empno')"></td>
			<td width="10%"><strong>雇员编号</strong></td>
			<td width="10%"><strong>雇员名称</strong></td>
			<td width="10%"><strong>职位</strong></td>
			<td width="10%"><strong>领导</strong></td>
			<td width="10%"><strong>部门</strong></td>
			<td width="10%"><strong>雇佣日期</strong></td>
			<td width="10%"><strong>薪资</strong></td>
			<td width="10%"><strong>绩效</strong></td>
			<td width="10%"><strong>操作</strong></td>
		</tr>
		<%
		  	Iterator<Emp> iter = all.iterator();
			while(iter.hasNext()){
				Emp vo = iter.next();
		%>
  		<tr onmouseover="changeColor(this,'#FFFFFF')" onmouseout="changeColor(this,'#F2F2F2')">
			<td><input type="checkbox" id="empno" name="empno" value="<%=vo.getEmpno() %>"></td>
			<td><%=vo.getEmpno() %></td>
			<td><a onclick="openPage('<%=showUrl%>?empno=<%=vo.getEmpno()%>')" ><%=vo.getEname()%></a></td>
			<td><%=vo.getJob() %></td>
			<td><a onclick="openPage('<%=showUrl%>?empno=<%=vo.getMgr().getEmpno()%>')" ><%=vo.getMgr().getEname() != null ? vo.getMgr().getEname() : ""%></a></td>
			<td><a onclick="openPage('<%=deptShowUrl%>?deptpno=<%=vo.getDept().getDeptno()%>')" ><%=vo.getDept().getDname() != null ? vo.getDept().getDname() : ""%></a></td>
			<td><%=vo.getHiredate() %></td>
			<td><%=vo.getSal() %></td>
			<td><%=vo.getComm() %></td>
			<td><a href="<%=updateUrl%>?empno=<%=vo.getEmpno()%>&backurl=<%=url%>&currentPage=<%=currentPage%>&lineSize=<%=lineSize%>&keyWord=<%=keyWord%>&column=<%=column%>">修改</a></td>
		</tr>
		<%
			}
		%>
  	</table>
  	<div>
	<input type="button" value="删除雇员信息" onclick="deleteAll('<%=deleteUrl%>?backurl=<%=url%>&currentPage=<%=currentPage%>&lineSize=<%=lineSize%>&keyWord=<%=keyWord%>&column=<%=column%>','eno','empno')">
	<a href="<%=insertUrl %>">增加新雇员</a>
  	</div>
  	</div>
	<div id="splitBarDiv" style="float:right">
	    <jsp:include page="/pages/split_page_plugin_bars.jsp">
	        <jsp:param name="currentPage" value="<%=currentPage%>"/>
	        <jsp:param name="lineSize" value="<%=lineSize%>"/>
	        <jsp:param name="column" value="<%=column%>"/>
	        <jsp:param name="keyWord" value="<%=keyWord%>"/>
	        <jsp:param name="allRecorders" value="<%=allRecorders%>"/>
	        <jsp:param name="url" value="<%=url%>"/>
	        <jsp:param name="paramName" value="deptno"/>
	        <jsp:param name="paramValue" value="<%=deptno%>"/>
	    </jsp:include>
	</div>
  </body>
</html>
