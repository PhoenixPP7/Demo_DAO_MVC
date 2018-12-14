package mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.factory.ServiceFactory;
import mvc.vo.Dept;

@SuppressWarnings("serial")
public class DeptServlet extends HttpServlet {
/*	
	public DeptServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	*//**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 *//*
	public void init() throws ServletException {
		// Put your code here
	}
*/
	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	request.setCharacterEncoding("UTF-8");//避免出现乱码
		String path = "/pages/errors.jsp";
		String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
		System.out.println("DeptServlet - doGet**********status = " + status);
		if (status != null){
			if("insert".equals(status)){
				path = this.insert(request);
			} else if ("list".equals(status)) {
				path = this.list(request);
			} else if ("updatePre".equals(status)){
				path = this.updatePre(request);
			} else if ("update".equals(status)){
				path = this.update(request);
			} else if ("delete".equals(status)){
				path = this.delete(request);
			} else if ("listDetails".equals(status)){
				path = this.listDetails(request);
			}
		}
		System.out.println("DeptServlet - doGet**********path = " + path);
		request.getRequestDispatcher(path).forward(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to post.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public String insert(HttpServletRequest request) {
	  	Dept vo = new Dept();
	  	vo.setDeptno(Integer.parseInt(request.getParameter("deptno")));
	  	vo.setDname(request.getParameter("dname"));
	  	vo.setLoc(request.getParameter("loc"));
	  	String msg = "部门信息增加失败!";
	  	try {
			if(ServiceFactory.getIDeptServiceInstance().insert(vo)){
				msg = "部门信息增加成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	request.setAttribute("msg", msg);//forward.jsp要根据接收的msg进行信息提示;
	  	request.setAttribute("url","/pages/back/admin/dept/dept_insert.jsp");//forward.jsp要根据接收的url进行路径跳转;
		return "/pages/forward.jsp";
	}
	
	public String list(HttpServletRequest request) {
		try {
			request.setAttribute("allDepts", ServiceFactory.getIDeptServiceInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/back/admin/dept/dept_list.jsp";
	}
	
	public String listDetails(HttpServletRequest request) {
		try {
			request.setAttribute("allDepts", ServiceFactory.getIDeptServiceInstance().listDetails());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/back/admin/dept/dept_list_details.jsp";
	}
	
	public String updatePre(HttpServletRequest request) {
		int deptno = Integer.parseInt(request.getParameter("deptno"));//接收地址重写传递来的部门编号;
		try {
			request.setAttribute("dept", ServiceFactory.getIDeptServiceInstance().updatePre(deptno));//根据部门编号查询部门对象信息;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/back/admin/dept/dept_update.jsp";
	}
	
	private String update(HttpServletRequest request) {
	  	Dept vo = new Dept();
	  	vo.setDeptno(Integer.parseInt(request.getParameter("deptno")));
	  	vo.setDname(request.getParameter("dname"));
	  	vo.setLoc(request.getParameter("loc"));
	  	String msg = "部门信息修改失败!";
	  	try {
			if(ServiceFactory.getIDeptServiceInstance().update(vo)){
				msg = "部门信息修改成功!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	request.setAttribute("msg", msg);//forward.jsp要根据接收的msg进行信息提示;
	  	request.setAttribute("url","/pages/back/admin/dept/DeptServlet/list");
		return "/pages/forward.jsp";
	}
	
	@SuppressWarnings("unchecked")
	private String delete(HttpServletRequest request) {
		String dno = request.getParameter("dno");// 传来的"dno"中的数据之间使用"|"分隔;
		String msg = "部门信息删除失败!";
		if (!(dno == null || "".equals(dno))) {// 判断"dno"为非空,"dno"有数据;
			String result[] = dno.split("\\|");
			Set<Integer> ids = new HashSet<Integer>();
			for (int x = 0; x < result.length; x++) {
				ids.add(Integer.parseInt(result[x]));
			}
			Map<String, Object> map;
			try {
				map = ServiceFactory.getIDeptServiceInstance().delete(ids);
				boolean flag = (boolean) map.get("flag");
				if (flag) {
					msg = "部门信息删除成功!";
					// 删除部门信息后,要删除对应的雇员照片;
					Set<String> photos = (Set<String>) map.get("allPhotos");
					Iterator<String> iter = photos.iterator();
					while (iter.hasNext()) {
						String photo = iter.next();
						if (!"nophoto".equals(photo)) {// 照片名字photo不是初始照片nophoto的话,执行
							String filePath = getServletContext().getRealPath(
									"/upload/")
									+ photo;
							File file = new File(filePath);
							if (file.exists()) {
								file.delete();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	  	request.setAttribute("msg", msg);//forward.jsp要根据接收的msg进行信息提示;
	  	request.setAttribute("url","/pages/back/admin/dept/DeptServlet/list");
		return "/pages/forward.jsp";
	}
	
}
/*
DeptServlet主要用途=
	1.接收数据;
	2.数据验证;(本次不涉及)
	3.调用业务层并根据结果进行跳转;

*/