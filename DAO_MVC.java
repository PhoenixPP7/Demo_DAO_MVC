
public class Emp {

}

/*
==========07_《JSP&Servlet实战开发》
========================================JSP实战=JSP+DAO
====================业务要求=部门+雇员管理
使用JSP+DAO的开发实现部门的管理操作;
实现数据的CRUD操作,可以进行部门数据的增加、修改、查询结果列表显示、删除操作;
实现JavaScript验证;
====================开发步骤=
==========后台开发
1.定义VO类=[Dept.java]+[Emp.java];
2.定义数据库连接工具类=[DatabaseConnection.java];
3.开发数据层(DAO)接口父接口;---直接导入"DAO实例"中的[IDAO]接口;
3.1.开发数据层抽象类;---[AbstractDAOImpl.java]
4.定义数据层(DAO)接口父接口=[IDeptDAO.java]+[IEmpDAO.java],此接口继承IDAO接口;
5.定义数据层(DAO)接口实现类=[IDeptDAOImpl]+[EmpDAOImpl.java];
6.定义数据层(DAO)工厂类=[DAOFactory];
7.定义业务层(Service)接口=[IDeptService]+[IEmpService];
8.定义业务层(Service)接口实现类(子类)=[DeptServiceImpl]+[EmpServiceImpl];
9.定义业务层(Service)工厂类(功能类)=[ServiceFactory];
==========前台开发
10.在[js]目录下定义[dept.js]脚本,此脚本负责相关验证操作;
11.编写[dept_insert.jsp]页面文件;说明=
	填写要增加的部门数据;
	部门数据增加操作由[dept_insert_do.jsp]页面负责执行;
12.编写[dept_insert_do.jsp]页面文件;说明=
	导入所需程序包;=[<%@ page import="mvc.factory.*" %>];
	处理中文乱码;=[request.setCharacterEncoding("UTF-8");];
	接收请求参数,且将其设置到VO类对象中;=[vo.setDname(request.getParameter("dname"));]
	调用服务层,进行数据的保存;=[if(ServiceFactory.getIDeptServiceInstance().insert(vo)){];
	增加失败跳转回[dept_insert.jsp]页面;=[window.location = "<%=url%>";];
	信息提示功能建议使用"JavaScript"完成,跳转功能建议使用"window.location"完成;"window.location"属于客户端跳转,地址栏会发生改变;
13.编写[dept_list.jsp]页面文件;说明=
	修改功能=地址重写的方式向[dept_update.jsp]传入部门编号[deptno]=[<a href="<%=updatePreUrl%>?deptno=<%=vo.getDeptno()%>">修改</a>];
	删除功能-全选操作=[ppjs.js]-[function checkboxSelect(obj,eleName)];
	删除功能=采用地址重写的方式完成,结构=[dept_delete_do,jsp?deptno=10|20|30...],使用"|"分隔要删除的编号;[ppjs.js]-[function deleteAll(url,paramName,eleName)];
14.编写[dept_update.jsp]页面文件;说明=
	根据[dept_list.jsp]传递过来的部门编号[deptno]查询部门信息;
	使用隐藏域向之后的页面传递=[<input type="hidden" id="deptno" name="deptno" value="<%=vo.getDeptno()%>">];
15.编写[dept_update_do.jsp]页面文件;说明=
	执行更新操作,更新后跳转回[dept_list.jsp]页面;
==========雇员照片及简介功能
	1.需在emp表增加2个字段=
		1.保存每个雇员的照片,字段名称=[photo],类型=[varchar2];
		2.保存每个雇员的简介,字段名称=[note],类型=[clob];
	2.导入SmartUpload.jar;
	3.建立上传文件保存目录,[upload]文件夹;
	4.修改后台业务逻辑
		1.在[Emp.java]类增加photo和note属性;
		2.修改[EmpDAOImpl.java]子类方法;
		3.在[IEmpDAO.java]接口增加一个功能,根据部门编号找到所有雇员照片;
		4.修改[EmpDAOImpl.java]子类方法;
		5.修改[IDeptService.java]接口"delete"方法;---因为删除部门数据操作有改变;
		6.修改[DeptServiceImpl.java]子类"delete"方法;
		7.修改[dept_delete_do.jsp];---使用File类判断要删除的文件是否存在,再执行删除,且不能删除初始头像文件"nophoto.jpg"文件;
		8.修改[emp_insert.jsp];表单添加封装属性=<form enctype="multipart/form-data">,代表通过二进制进行封装;
		9.修改[emp_insert_do.jsp];---由于表单已被封装,需使用SmartUpload组件进行数据接收;如果没有雇员照片,则将"nophoto.jpg"作为默认照片;
		10.修改[emp_show.jsp]显示雇员详情功能;
		11.修改[emp_list_details.jsp]及[emp_delete_do.jsp]中删除功能;---雇员信息除了照片存在于[upload]文件夹,其他信息都在数据库,此删除功能应将要删除的雇员编号和照片路径作为参数传递用以删除;
		12.修改[emp_update.jsp]及[emp_update_do.jsp]更新雇员信息功能;---注意修改照片,标记出旧照片;

====================总结=本程序是一个综合整合程序
1.JavaScript=验证页面的元素数据操作;
2.CSS=修饰页面;
3.JSP=基础语法,如=<page import="">;
4.所有可执行的".class"文件都保存在[WEB-INF\classes\]目录下;
5.request对象的使用=
	5.1.接收单个参数=[request.getParameter("deptno")],接收和返回的值都是String,可以进行正则验证,可以进行各种类型的转换及字符串的操作;
	5.2.参数来源=表单参数、地址重写;
	5.3.解决乱码=[request.setCharacterEncoding("UTF-8");];


====================补充说明=分页显示
Oracle使用ROWNUM实现,例=[SELECT * FROM (SELECT empno,ename,ROWNUM rn FROM emp WHERE ROWNUM<=?) temp WHERE temp.rn>?];
控制"首页"/"上一页"组件的可控状态=[<input> type="button" value="首页" <%=currentPage == 1 ? "disabled" : "" %>]
==========分页核心
当前所在页=currentPage(cp);
每页显示的数据量=lineSize(ls);
模糊查询的数据列=column(col);
模糊查询的关键字=keyWord(kw);

====================补充说明=日期组件-JavaScript
1.将[My97DatePicker]文件夹下的[lang]+[skin]+[开发包]+[calendar.js]+[WdatePicker.js]复制到项目[js]目录下;
2.需要日期组件的页面添加语句=
	<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
3.页面日期框添加事件=[onclick="WdatePicker()"];

====================补充说明=数据模糊查询
根据模糊关键字查询;根据查询结果分页;
result[0]=[雇员编号:empno]
	temp[0]=[雇员编号]
	temp[1]=[empno]
result[1]=[雇员姓名:ename]
	temp[0]=[雇员姓名]
	temp[1]=[ename]
result[2]=[雇员职位:job]


*/