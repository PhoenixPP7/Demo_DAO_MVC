package mvc.dao;

import java.util.List;

import mvc.vo.Dept;

public interface IDeptDAO extends IDAO<Integer, Dept> {
	/**
	 * 查看所有部门的完整的统计信息,包含的信息内容如下:<br>
	 * <li>包含部门的基础信息:deptno、dname、loc；
	 * <li>包含部门的统计信息:人数、平均工资、最高工资、最低工资、总工资；
	 * @return 以List集合的方式返回，如果没有数据则集合长度为0(size()==0);
	 * @throws Exception
	 */
	public List<Dept> findAllByStat() throws Exception;
	/**
	 * 取得单个部门的完整统计信息;<br>
	 * <li>包含部门的基础信息:deptno、dname、loc；
	 * <li>包含部门的统计信息:人数、平均工资、最高工资、最低工资、总工资；
	 * @param id 部门编号;
	 * @return 如果查询出此部门的信息,则以dept对象的形式返回,否则返回null;
	 * @throws Exception
	 */
	public Dept findByIdDetails(Integer id) throws Exception;
}
/*
实现部门表和雇员表信息一对多关系的CRUD功能,扩充以下方法:
1.查看所有部门的完整的统计信息;
2.取得单个部门的完整的统计信息;
*/