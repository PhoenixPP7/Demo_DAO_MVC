<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta http-equiv="Content-Type" content="text/html; charset='utf-8'" />
    <base href="<%=basePath%>">
    <title>学生查询</title>
</head>
<body>
<%!//设置连接数据库的参数
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/csdpsystem";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "";
%>

<%
    String url = "/jsp/cdsp_information/manager/page.jsp";
    int currentPage = 1;
    String keyWord = "";//默认的关键字
    int lineSize = 5;//每页显示的数据数
    int allRecorders = 0;//保存总记录数
    String column = "studentID";//定义默认的查询列
    String columnData = "用户用户名:studentID|用户姓名:studentName|用户状态:sStatus";//可操作的查询列

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
%>

<%
    try{//如果没有输入参数那么就会是null,null无法变为数字
        currentPage = Integer.parseInt(request.getParameter("cp"));
    }catch(Exception e){}
    try{//如果没有输入参数那么就会是null,null无法变为数字
        lineSize = Integer.parseInt(request.getParameter("ls"));
    }catch(Exception e){}
    if(request.getParameter("kw")!=null){//表示有查询的关键字
        System.out.println("取得关键字:"+new String(request.getParameter("kw").getBytes("iso-8859-1"), "utf-8"));
        keyWord = new String(request.getParameter("kw").getBytes("iso-8859-1"), "utf-8");//将取得的关键字进行转换
    }
    if(request.getParameter("col")!=null){//表示有查询的检索列
        column = request.getParameter("col");
    }
%>

<%//查询总记录数
    String sql = " SELECT COUNT(*) FROM student WHERE "+column+" LIKE ? ";
    Class.forName(DBDRIVER);//加载数据库驱动
    conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);//设置数据库连接
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1,"%"+keyWord+"%");
    rs = pstmt.executeQuery();//将查询的结果返回
    if (rs.next()){
        allRecorders = rs.getInt(1);//返回总记录数
//        System.out.println(allRecorders);
    }
%>

<%//模糊查找数据
    sql = " SELECT studentID,sStatus,sIdentity,studentName,studentPassword,sLastTime " +
            " FROM student " +
            " WHERE "+ column +"  LIKE ? LIMIT ?,? ";
//        select * from tablename limit 2,4
//        即取出第3条至第6条，4条记录
//    0,5   即取出第1条到第5条 , 5条记录
//    5,5   即取出第6条到第10条, 5条记录
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1,"%"+keyWord+"%");// 模糊查询  %关键词%
    pstmt.setInt(2,(currentPage-1)*lineSize);//(当前所在的页 - 1) * 每页显示数据行数 0 5
    pstmt.setInt(3,lineSize);//每页最多显示数据行数 5 5
    rs = pstmt.executeQuery();
%>
<%
    System.out.println("page.jsp的keyWord:"+keyWord);
%>
<%--搜索栏--%>
<div id="spiltSearchDiv">
    <jsp:include page="split_page_search.jsp">
        <jsp:param name="columnData" value="<%=columnData%>"></jsp:param>
        <jsp:param name="keyWord" value="<%=keyWord%>"></jsp:param>
        <jsp:param name="allRecorders" value="<%=allRecorders%>"></jsp:param>
        <jsp:param name="column" value="<%=column%>"></jsp:param>
    </jsp:include>
</div>

<%--数据显示栏--%>
<div id="dataDiv">
    <table border="1" width="100%" bgcolor="#F2F2F2">
        <tr>
            <td>学生学号:</td>
            <td>学生姓名:</td>
            <td>学生状态:</td>
            <td>学生最后一次登录时间:</td>
        </tr>
        <%
            while (rs.next()){
                //studentID,sStatus,sIdentity,studentName,studentPassword,sLastTime
                String studentID = rs.getString(1);
                String studentName = rs.getString(4);
                int sStatus = rs.getInt(2);
                Date sLastTime = rs.getDate(6);
        %>
            <tr>
                <td><%=studentID%></td>
                <td><%=studentName%></td>
                <td>
                    <%
                        if (sStatus == 1){
                    %>
                            在线
                    <%
                        }else{
                    %>
                            不在线
                    <%
                        }
                    %>
                </td>
                <td><%=sLastTime%></td>
            </tr>
        <%
            }
            conn.close();
        %>
    </table>
</div>

<%--分页栏--%>
<div id="splitBarDiv">
    <jsp:include page="split_page_bar.jsp">
        <jsp:param name="currentPage" value="<%=currentPage%>"></jsp:param>
        <jsp:param name="lineSize" value="<%=lineSize%>"></jsp:param>
        <jsp:param name="column" value="<%=column%>"></jsp:param>
        <jsp:param name="keyWord" value="<%=keyWord%>"></jsp:param>
        <jsp:param name="allRecorders" value="<%=allRecorders%>"></jsp:param>
        <jsp:param name="url" value="<%=url%>"></jsp:param>
    </jsp:include>
</div>
</body>
</html>