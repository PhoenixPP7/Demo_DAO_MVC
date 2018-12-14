package mvc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mvc.vo.Emp;

public interface IEmpService {
	public boolean insert(Emp vo) throws Exception;

	public boolean update(Emp vo) throws Exception;

	public boolean delete(Set<Integer> ids) throws Exception;

	public List<Emp> list() throws Exception;

	/**
	 * 此方法用于数据更新前的查询操作,相当于[DAOProject]中[IDeptService]接口中 [public Dept get(int
	 * ids) throws Exception]方法;
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	public Map<String, Object> list(String column, String keyWord,
			int currentPage, int lineSize) throws Exception;
	/**
	 * 根据编号查询雇员信息;
	 * @param id 雇员编号;
	 * @return 如果查询到数据,则以VO对象形式返回,否则返回null;
	 * @throws Exception
	 */
	public Emp show(int id) throws Exception;
	/**
	 * 增加雇员信息前的准备工作,因为在增加雇员时需要设置领导信息和部门信息;
	 * 所以要将emp表和dept表的数据都查询出来;
	 * @return 返回两组信息;1.查询全部的雇员信息,key = allEmps,value = IEmpDAO.findAll();
	 * 						2.全部的部门信息;
	 * @throws Exception
	 */
	public Map<String,Object> insertPre() throws Exception;
	/**
	 * 修改雇员信息前的准备工作,因为在修改雇员时需要查询领导信息、部门信息、此雇员信息;
	 * 所以要将emp表和dept表的数据都查询出来;
	 * @return 返回三组信息; 1.查询全部的雇员信息;key = allEmps,value = IEmpDAO.findAll();
	 * 						2.全部的部门信息;key = allDepts,value = IEmpDAO.findAll();
	 * 						3.查询此雇员的完整信息;key = emp,value = IEmpDAO.findByIdDetails();
	 * @throws Exception
	 */	
	public Map<String,Object> updatePre(Integer id) throws Exception;
	/**
	 * 使用详细列表的方式列出雇员的完整信息,要执行以下操作:<br>
	 * <li>调用IEmpDAO.findAllSplitDetails()方法列出详细雇员信息;
	 * <li>调用IEmpDAO.getAllCount()方法列出详细的雇员人数,主要用于分页操作;
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param pageSize
	 * @return 所有的内容以Map集合返回,返回的内容如下:<br>
	 * <li>分页的详细信息;key = allEmps , value = IEmpDAO.findAllSplitDetails();
	 * <li>返回雇员统计记录数;key = empCount,value = IEmpDAO.getAllCount();
	 * @throws Exception
	 */
	public Map<String,Object> listDetails(String column,String keyWord,int currentPage,int lineSize) throws Exception;
}
