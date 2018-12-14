<%@ page pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%
    String columnData = null;//查询的数据列
    String keyWord = null;//查询的关键字
    String column = null;//查询的
    int allRecorders = 0;//查询的总数据量
%>
<%//接收页面的接收列
    try{
        allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
    }catch (Exception e){}
    columnData = request.getParameter("columnData");
    keyWord = request.getParameter("keyWord");
    column = request.getParameter("column");
%>
请输入查询关键字:
<%
    if (columnData!=null){
%>
<select id="colSel">
    <%
        String result[] = columnData.split("\\|");
        for (int x = 0 ; x < result.length ; x ++){
            String temp[] = result[x].split(":");
    %>
        <option value="<%=temp[1]%>" <%=column.equals(temp[1])?"selected":""%>><%=temp[0]%></option>
    <%
        }
    %>
</select>
<%
    }
%>
<input type="text" name="kw" id="kw" value="<%=keyWord%>">
<%
    System.out.println("split_page_search.jsp的keyWord;"+keyWord);
%>

<input type="button" value="检索" onclick="goSplit(1)"><br>
<span>一共查询到<%=allRecorders%>条记录</span><br>
<%--,一共有<%=pageSize%>页。--%>