<%@ page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%--
    代码的引入过程
//(建议)第三种方式,在第二种的基础上,通过Servlet传递request属性,直接接收request并取得Attribute;
	<div id="splitBarDiv" style="float:right">
	    <jsp:include page="/pages/split_page_plugin_bar.jsp"/>
	</div>
--%>
<%//设置由外部接收的数据
    String url = null;
    int currentPage = 1;//当前页
    int lineSize = 5;//每页数据数
    String column = null;//检索列
    String keyWord = null;//关键词
    int allRecorders = 0;//总记录数
    int pageSize = 0 ;//总页数
    int lsData [] = new int [] {1,5,10,15,20,30,50,100} ;//每页显示多少条数据
    int seed = 3 ;
%>
<%//接收外部传递的参数
    try {
        currentPage = (Integer) request.getAttribute("currentPage");
    } catch (Exception e) {}
    try {
        lineSize = (Integer) request.getAttribute("lineSize");
    } catch (Exception e) {}
    try {
        allRecorders = (Integer) request.getAttribute("allRecorders");
    } catch (Exception e) {}
    column = (String) request.getAttribute("column") ;
    keyWord = (String) request.getAttribute("keyWord") ;
    url = basePath + (String) request.getAttribute("url") ;
%>

<%//计算总页数
    if (allRecorders > 0) {
        pageSize = (allRecorders + lineSize - 1) / lineSize ;
    } else {    // 没有记录
        pageSize = 1 ;
    }
%>

<script type="text/javascript">
    function goSplit(vcp) { // 根据外部传递的cp内容进行操作
        try {
            var eleKw = document.getElementById("kw").value ;
            var eleCol = document.getElementById("colSel").value ;
            window.location = "<%=url%>?currentPage=" + vcp + "&lineSize=" + eleLs + "&keyWord=" + eleKw + "&column=" + eleCol ;
        } catch (Exception) {//如果出现异常,说明没有关键字和检索列
            window.location = "<%=url%>?currentPage=" + vcp + "&lineSize=" + eleLs  ;
        }
    }
</script>
<ul class="pagination">
<%
	if (pageSize > seed * 3){
%>
	<li class="<%=currentPage == 1?"active":""%>"><a onclick="goSplit()">
</ul>


    <input type="button" value="首页" onclick="goSplit(1)" <%=currentPage == 1 ? "disabled" : ""%>>
    <input type="button" value="上一页" onclick="goSplit(<%=currentPage-1%>)" <%=currentPage == 1 ? "disabled" : ""%>>
    <input type="button" value="下一页" onclick="goSplit(<%=currentPage+1%>)" <%=currentPage == pageSize ? "disabled" : ""%>>
    <input type="button" value="尾页" onclick="goSplit(<%=pageSize%>)" <%=currentPage == pageSize ? "disabled" : ""%>>
    <%--//添加一个下拉列表框--%>
	跳转到:<select id="cpSel" onchange="goSplit(this.value)">
    <%--动态控制的option--%>
<%
    for (int x = 1;x <= pageSize ; x ++){
%>
		<option value="<%=x%>" <%=currentPage == x ? "selected" : ""%>><%=x%></option>
<%
    }
%>
	</select>页&nbsp;每页显示:
    <select id="lsSel" onchange="goSplit(1)">
        <%
            for (int x = 0 ; x < lsData.length ; x ++){
        %>
        	<option value="<%=lsData[x]%>" <%=lineSize == lsData[x] ? "selected" : ""%>>
        	<%=lsData[x]%>
        	</option>
        <%
            }
        %>
    </select>
	行记录