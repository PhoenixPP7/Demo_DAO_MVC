<%@ page pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%--
    代码的引入过程
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
%>

<%//接收外部传递的参数
    try {
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    } catch (Exception e) {}
    try {
        allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
        System.out.println("jsp/cdsp_information/manager/split_page_bar.jsp:"+allRecorders);
    } catch (Exception e) {}
    try {
        lineSize = Integer.parseInt(request.getParameter("lineSize"));
    } catch (Exception e) {}
    column = request.getParameter("column") ;
    keyWord = request.getParameter("keyWord") ;
    url = request.getParameter("url") ;
%>

<%//计算总页数
    if (allRecorders > 0) {
        pageSize = (allRecorders + lineSize - 1) / lineSize ;
    } else {    // 没有记录
        pageSize = 1 ;
    }
%>
<%
    System.out.println("split_page_bar.jsp的keyWord;"+keyWord);
%>
<script type="text/javascript">
    function goSplit(vcp) { // 根据外部传递的cp内容进行操作
        var eleLs = document.getElementById("lsSel").value ;
        try {
            var eleKw = document.getElementById("kw").value ;
            var eleCol = document.getElementById("colSel").value ;
            window.location = "<%=url%>?cp=" + vcp + "&ls=" + eleLs + "&kw=" + eleKw + "&col=" + eleCol  ;
        } catch (Exception) {//如果出现异常,说明没有关键字和检索列
            window.location = "<%=url%>?cp=" + vcp + "&ls=" + eleLs  ;
        }
    }
</script>

    <input type="button" class="btn btn-default" value="首页" onclick="goSplit(1)" <%=currentPage == 1 ? "disabled" : ""%>>
    <input type="button" class="btn btn-default" value="上一页" onclick="goSplit(<%=currentPage-1%>)" <%=currentPage == 1 ? "disabled" : ""%>>
    <input type="button" class="btn btn-default" value="下一页" onclick="goSplit(<%=currentPage+1%>)" <%=currentPage == pageSize ? "disabled" : ""%>>
    <input type="button" class="btn btn-default" value="尾页" onclick="goSplit(<%=pageSize%>)" <%=currentPage == pageSize ? "disabled" : ""%>>
    <%--//添加一个下拉列表框--%>
    跳转到:<select id="cpSel" onchange="goSplit(this.value)">
    <%--动态控制的option--%>
    <%
        for (int x = 1;x <= pageSize;x++){
    %>
    <option value="<%=x%>" <%=currentPage == x ? "selected" : ""%>><%=x%></option>
    <%
        }
    %>
</select>页&nbsp;每页显示:
    <select id="lsSel" onchange="goSplit(1)">
        <%
            for (int x = 0 ; x<lsData.length ;x++){
        %>
        <option value="<%=lsData[x]%>" <%=lineSize == lsData[x] ? "selected" : ""%>><%=lsData[x]%></option>
        <%
            }
        %>
    </select>
    行记录