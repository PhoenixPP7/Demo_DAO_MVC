package mvc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mvc.vo.Dept;

public interface IDeptService {
	public boolean insert(Dept vo) throws Exception;
	public boolean update(Dept vo) throws Exception;
	/**
	 * 批量删除部门数据,此方法要执行3个操作=
	 * <li>根据要删除的部门编号,找到所有对应的雇员的图片信息,调用[IEmpDAO.findAllPhotoByDeptno()]方法;
	 * <li>删除此部门的所有雇员数据,调用[IEmpDAO.doRemoveByDeptno()]方法;
	 * <li>删除本部门的数据,调用[IDeptDAO.doRemoveBatch()]方法;
	 * @param ids 所有要删除的部门编号;
	 * @return 返回操作成功或失败的标记,及所有已删除的雇员照片,以Map集合返回,返回结果包含:<br>
	 * <li>key = flag,value = 删除成功或失败的标记;
	 * <li>key = allPhotos,value = 所有要删除部门的雇员照片;
	 * @throws Exception
	 */
	public Map<String,Object> delete(Set<Integer> ids) throws Exception;
	public List<Dept> list() throws Exception;
	/**
	 * 此方法用于数据更新前的查询操作,相当于[DAOProject]中[IDeptService]接口中
	 * [public Dept get(int ids) throws Exception]方法;
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Dept updatePre(Integer id) throws Exception;
	/**
	 * 列出所有部门的详细信息,调用IDeptDAO.findAllByStat()方法;
	 * @return 所有部门的详细信息以及统计信息以List集合的形式返回;
	 * @throws Exception
	 */
	public List<Dept> listDetails() throws Exception;
	/**
	 * 查看一个部门的完整信息,调用IDeptDAO.findByIdDetails()方法;
	 * @param id 要查询的部门编号;
	 * @return 如果有部门信息则以对象的形式返回,否则返回null;
	 * @throws Exception
	 */
	public Dept show(Integer id,String column,String keyWord,int currentPage,int lineSize) throws Exception;
}
