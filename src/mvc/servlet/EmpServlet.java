package mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import mvc.factory.ServiceFactory;
import mvc.util.BasePath;
import mvc.vo.Dept;
import mvc.vo.Emp;

@SuppressWarnings("serial")
public class EmpServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	request.setCharacterEncoding("UTF-8");//避免出现乱码
		String path = "/pages/errors.jsp";
		String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
		System.out.println("request.getRequestURI() = " + request.getRequestURI());// =EmpProject/pages/back/admin/emp/EmpServlet/list
//		System.out.println("status = " + status);// =list
		if (status != null){
			if("insertPre".equals(status)){
				path = this.insertPre(request);
			} else if ("insert".equals(status)) {
				path = this.insert(request,response);
			} else if ("list".equals(status)){
				path = this.list(request);
			} else if ("listSplit".equals(status)){
				path = this.listSplit(request);
			} else if ("listDetails".equals(status)){
				path = this.listDetails(request);
			} else if ("updatePre".equals(status)){
				path = this.updatePre(request);
			} else if ("update".equals(status)){
				path = this.update(request, response);
			} else if ("delete".equals(status)){
				path = this.delete(request);
			} else if ("show".equals(status)){
				path = this.show(request);
			}
		}
		request.getRequestDispatcher(path).forward(request,response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}
	
	public String insertPre(HttpServletRequest request) throws ServletException, IOException {
  		try {
			Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().insertPre();
			request.setAttribute("allEmps",map.get("allEmps"));
			request.setAttribute("allDepts",map.get("allDepts"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/back/admin/emp/emp_insert.jsp";
	}
	
	public String insert(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		SmartUpload smart = new SmartUpload();
		smart.initialize(super.getServletConfig(),request,response);
		try {
			smart.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	  	Emp vo = new Emp();
	  	vo.setEmpno(Integer.parseInt(smart.getRequest().getParameter("empno")));
	  	vo.setEname(smart.getRequest().getParameter("ename"));
	  	vo.setJob(smart.getRequest().getParameter("job"));
	  	try {
			vo.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(smart.getRequest().getParameter("hiredate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	  	vo.setSal(Double.parseDouble(smart.getRequest().getParameter("sal")));
	  	vo.setComm(Double.parseDouble(smart.getRequest().getParameter("comm")));
	  	int tempMgr = Integer.parseInt(smart.getRequest().getParameter("mgr"));
	  	if (tempMgr != 0){//表示有雇员领导
	  		Emp mgr = new Emp();
	  		mgr.setEmpno(tempMgr);
	  		vo.setMgr(mgr);
	  	}
	  	int tempDeptno = Integer.parseInt(smart.getRequest().getParameter("deptno"));
	  	if (tempDeptno != 0){
			Dept dept = new Dept();
	  		dept.setDeptno(tempDeptno);
	  		vo.setDept(dept);
	  	}
	  	String fileName = "nophoto.jpg";
	  	if (smart.getFiles().getFile(0).getSize() > 0){
		  	if (smart.getFiles().getFile(0).getContentType().contains("image")) {
		  		fileName = java.util.UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt();
		  	}
	  	}
	  	vo.setPhoto(fileName);
	  	vo.setNote(smart.getRequest().getParameter("note"));
	  	String msg = "雇员信息增加失败!";
	  	try {
			if(ServiceFactory.getIEmpServiceInstance().insert(vo)){
			  	if (smart.getFiles().getFile(0).getSize() > 0){
				  	if (smart.getFiles().getFile(0).getContentType().contains("image")) {
				  		String filePath = getServletContext().getRealPath("/upload/") + fileName;
				  		smart.getFiles().getFile(0).saveAs(filePath);
				  	}
			  	}
				msg = "雇员信息增加成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	request.setAttribute("msg",msg);
	  	request.setAttribute("url","/pages/back/admin/emp/EmpServlet/insertPre");
		return "/pages/forward.jsp";
	}
	
	public String list(HttpServletRequest request) throws ServletException, IOException {
		String url = "/pages/back/admin/emp/EmpServlet/list";//实现从不同的起始页面进行了修改/删除等操作后返回该页面路径;
		try {
			request.setAttribute("allEmps", ServiceFactory.getIEmpServiceInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("url",url);
		return "/pages/back/admin/emp/emp_list.jsp";
	}
	
	public String listSplit(HttpServletRequest request) throws ServletException, IOException {
		String url = "/pages/back/admin/emp/EmpServlet/listSplit";//实现从不同的起始页面进行了修改/删除等操作后返回该页面路径;
		int currentPage = 1;//当前页
		String keyWord = "";//关键词,""表示默认全部查询;
		int lineSize = 5;//每页数据数
		String column = "ename";//检索列,默认检索"ename"列;
		String columnData = "雇员编号:empno|雇员姓名:ename|雇员职位:job";
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
		try {
			Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().list(column, keyWord, currentPage, lineSize);
			request.setAttribute("allEmps",map.get("allEmps"));
			request.setAttribute("allRecorders",map.get("empCount"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("columnData",columnData);
		request.setAttribute("column",column);
		request.setAttribute("keyWord",keyWord);
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("lineSize",lineSize);
		request.setAttribute("url",url);
		return "/pages/back/admin/emp/emp_list_split.jsp";
	}
	
	public String listDetails(HttpServletRequest request) throws ServletException, IOException {
		String url = "/pages/back/admin/emp/EmpServlet/listDetails";//实现从不同的起始页面进行了修改/删除等操作后返回该页面路径;
		int currentPage = 1;//当前页
		int lineSize = 5;//每页数据数
		String keyWord = "";//关键词,""表示默认全部查询;
		String column = "ename";//检索列,默认检索"ename"列;
		String columnData = "雇员编号:empno|雇员姓名:ename|雇员职位:job";//
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
		try {
			Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().listDetails(column, keyWord, currentPage, lineSize);
			request.setAttribute("allEmps",map.get("allEmps"));
			request.setAttribute("allRecorders",map.get("empCount"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("url",url);
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("lineSize",lineSize);
		request.setAttribute("keyWord",keyWord);
		request.setAttribute("column",column);
		request.setAttribute("columnData",columnData);
		return "/pages/back/admin/emp/emp_list_details.jsp";
		
	}

	public String updatePre(HttpServletRequest request) throws ServletException, IOException {
		int empno = Integer.parseInt(request.getParameter("empno"));
		try {
			Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().updatePre(empno);
			request.setAttribute("emp",map.get("emp"));
			request.setAttribute("allEmps",map.get("allEmps"));
			request.setAttribute("allDepts",map.get("allDepts"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/back/admin/emp/emp_update.jsp";
	}
	
	public String update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		SmartUpload smart = new SmartUpload();
		smart.initialize(super.getServletConfig(),request,response);
		try {
			smart.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	  	Emp vo = new Emp();
	  	vo.setEmpno(Integer.parseInt(smart.getRequest().getParameter("empno")));
	  	vo.setEname(smart.getRequest().getParameter("ename"));
	  	vo.setJob(smart.getRequest().getParameter("job"));
	  	try {
			vo.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse(smart.getRequest().getParameter("hiredate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	  	vo.setSal(Double.parseDouble(smart.getRequest().getParameter("sal")));
	  	vo.setComm(Double.parseDouble(smart.getRequest().getParameter("comm")));
	  	int tempMgr = Integer.parseInt(smart.getRequest().getParameter("mgr"));
	  	if (tempMgr != 0){//表示有雇员领导
	  		Emp mgr = new Emp();
	  		mgr.setEmpno(tempMgr);
	  		vo.setMgr(mgr);
	  	}
	  	int tempDeptno = Integer.parseInt(smart.getRequest().getParameter("deptno"));
	  	if (tempDeptno != 0){
			Dept dept = new Dept();
	  		dept.setDeptno(tempDeptno);
	  		vo.setDept(dept);
	  	}
	  	vo.setNote(smart.getRequest().getParameter("note"));
	  	String fileName = smart.getRequest().getParameter("oldpic");
	  	if (smart.getFiles().getSize() > 0) {
	  		if (smart.getFiles().getFile(0).getContentType().contains("image")) {
	  			if("nophoto.jpg".equals(fileName)){
	  				fileName = java.util.UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt();
	  			}
	  		}
	  	}
	  	vo.setPhoto(fileName);
	  	String msg = "雇员信息修改失败!";
	  	try {
			if(ServiceFactory.getIEmpServiceInstance().update(vo)){
			  	if (smart.getFiles().getFile(0).getSize() > 0){
				  	if (smart.getFiles().getFile(0).getContentType().contains("image")) {
				  		String filePath = getServletContext().getRealPath("/upload/") + fileName;
				  		smart.getFiles().getFile(0).saveAs(filePath);
				  	}
			  	}
				msg = "雇员信息修改成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	StringBuffer buf = new StringBuffer();//用于接收backUrl的路径;
	  	buf.append(smart.getRequest().getParameter("backurl")).append("?");
	  	buf.append("currentPage=").append(smart.getRequest().getParameter("currentPage"));
	  	buf.append("&lineSize=").append(smart.getRequest().getParameter("lineSize"));
	  	buf.append("&keyWord=").append(smart.getRequest().getParameter("keyWord"));
	  	buf.append("&column=").append(smart.getRequest().getParameter("column"));
	  	request.setAttribute("msg",msg);
	  	request.setAttribute("url",buf.toString());
		return "/pages/forward.jsp";
	}
	
	public String delete(HttpServletRequest request) throws ServletException, IOException {
//		System.out.println("delete-request.getParameter(eno) = " + request.getParameter("eno"));
		String eno = request.getParameter("eno");//传来的"empno"中的数据之间使用"|"分隔;
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
		  	try {
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	  	StringBuffer buf = new StringBuffer();//用于接收backUrl的路径;
	  	buf.append(request.getParameter("backurl")).append("?");
	  	buf.append("currentPage=").append(request.getParameter("currentPage"));
	  	buf.append("&lineSize=").append(request.getParameter("lineSize"));
	  	buf.append("&keyWord=").append(request.getParameter("keyWord"));
	  	buf.append("&column=").append(request.getParameter("column"));
	  	request.setAttribute("msg",msg);
	  	request.setAttribute("url",buf.toString());
	  	
	  	System.out.println("delete-buf.toString() = " + buf.toString());// =/pages/back/admin/emp/EmpServlet/listDetails?currentPage=3&lineSize=5&keyWord=&column=ename
	  	
		return "/pages/forward.jsp";
	}
	
	public String show(HttpServletRequest request) throws ServletException, IOException {
		int empno = Integer.parseInt(request.getParameter("empno"));
		try {
			request.setAttribute("emp",ServiceFactory.getIEmpServiceInstance().show(empno));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/back/admin/emp/emp_show.jsp";
	}
	
}
