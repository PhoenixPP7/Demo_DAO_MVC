<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mvc.factory.*" %>
<%@ page import="mvc.vo.*" %>
<%@ page import="java.io.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String url = basePath + "pages/back/admin/dept/dept_list.jsp";
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
	/* String eno = request.getParameter("eno");//传来的"empno"中的数据之间使用"|"分隔;
  	String msg = "雇员信息删除失败!";
  	Set<String> photos = new HashSet<String>();//保存所有要删除的雇员照片;
	if (!(eno == null || "".equals(eno))){//判断"empno"为非空,"empno"有数据;
		String result[] = eno.split("\\|");
		Set<Integer> ids = new HashSet<Integer>();
		for(int x = 0 ; x < result.length ; x++){
			String temp[] = result[x].split(":");
			ids.add(Integer.parseInt(temp[0]));
			if (!"nophoto.jpg".equals(temp[1])) {
				photos.add(temp[1]);
			}
		}
	  	if(ServiceFactory.getIEmpServiceInstance().delete(ids)){
	  		Iterator<String> iter = photos.iterator();
	  		while (iter.hasNext()) {
	  			String photo = iter.next();
	  			if (!"nophoto".equals(photo)){//照片名字photo不是初始照片nophoto的话,执行
	  				String filePath = getServletContext().getRealPath("/upload/")+photo ;
	  				File file = new File(filePath);
	  				if (file.exists()){
	  					file.delete();
	  				}
	  			}
	  		}
	  		msg = "雇员信息删除成功!";
	  	}
	}
	
  	StringBuffer buf = new StringBuffer();//用于接收backUrl的路径;
  	buf.append(request.getParameter("backurl")).append("?");
  	buf.append("cp=").append(request.getParameter("cp"));
  	buf.append("&ls=").append(request.getParameter("ls"));
  	buf.append("&kw=").append(request.getParameter("kw"));
  	buf.append("&col=").append(request.getParameter("col")); */
  %>
  <script type="text/javascript">
  	alert("<%=msg%>");
  	window.location = "<%=buf%>";
  </script>
  </body>
</html>
