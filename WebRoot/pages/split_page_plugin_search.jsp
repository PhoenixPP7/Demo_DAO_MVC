<%@ page pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%--代码的引入过程
//(建议)第三种方式,在第二种的基础上,通过Servlet传递request属性,直接接收request并取得Attribute;
	<div id="splitSearchDiv">
	    <jsp:include page="/pages/split_page_plugin_search.jsp"/>
	</div>
--%>
<%
    String columnData = null;//查询的数据列
    String keyWord = null;//查询的关键字
    int allRecorders = 0;//查询的总数据量
    String column = null;//查询的
%>
<%//接收页面的接收列
    try{
        allRecorders = (Integer) request.getAttribute("allRecorders");
    }catch (Exception e){}
    columnData = (String) request.getAttribute("columnData");
    keyWord = (String) request.getAttribute("keyWord");
    column = (String) request.getAttribute("column");
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
        <option value="<%=temp[1]%>" <%=column.equals(temp[1])?"selected":""%> > <%=temp[0]%> </option>
    <%
        }
    %>
</select>
<%
    }
%>
<input type="text" name="keyWord" id="keyWord" value="<%=keyWord%>">
<input type="button" value="检索" onclick="goSplit(1)"><br>
<span>一共查询到<%=allRecorders%>条记录</span><br>