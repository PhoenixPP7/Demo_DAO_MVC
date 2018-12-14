package mvc.dao;

import java.util.List;
import java.util.Set;

import mvc.vo.Emp;

public interface IEmpDAO extends IDAO<Integer, Emp> {
	/**
	 * 根据部门编号删除所有该部门的雇员信息;
	 * @param deptno 部门编号;
	 * @throws Exception 执行 SQL 异常;
	 */
	public void doRemoveByDeptno(Integer deptno) throws Exception ;
	/**
	 * 查询雇员的完整信息,包含领导信息(领导编号及领导姓名)、部门信息;
	 * @param id 要查询的雇员编号
	 * @return 如果查询到内容,则以VO对象返回,否则返回null;
	 * @throws Exception
	 */
	public Emp findByIdDetails(Integer id) throws Exception;
	/**
	 * 查询雇员的详细信息,包含有雇员的完整信息、领导信息(领导编号及领导姓名)、部门信息;
	 * @param column 模糊查询列;
	 * @param keyWord 模糊查询关键字;
	 * @param currentPage 当前所在页;
	 * @param lineSize 每页显示的数据行数;
	 * @return List集合的方式返回,如果没有数据,则返回集合长度为0(size()==0);
	 * @exception
	 */
	public List<Emp> findAllSplitDetails(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;
	/**
	 * 根据部门编号deptno查询此部门的全部雇员信息;
	 * @param deptno 部门编号;
	 * @param column 模糊查询列;
	 * @param keyWord 模糊查询关键字;
	 * @param currentPage 当前所在页;
	 * @param lineSize 每页显示的数据行数;
	 * @return 返回一个部门的所有雇员信息,如果此部门没有雇员,则返回数据长度为0(size() == 0);
	 * @throws Exception
	 */
	public List<Emp> findAllByDeptno(Integer deptno,String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception;
	/**
	 * 取得制定部门中所有雇员的照片信息;
	 * @param deptno 部门名称;
	 * @return 返回所有雇员的照片信息,使用Set集合可以避免重复照片;
	 * @throws Exception
	 */
	public Set<String> findAllPhotoByDeptno(Set<Integer> deptno) throws Exception;
}
