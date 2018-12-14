package dao.dao;

import java.util.List;
import java.util.Set;

/**
 * 定义公共的DAO操作接口标准,基本功能包括=增加、修改全部、删除数据、根据编号查询、查询全部、分页显示、数据统计;
 * @author PP
 * @param <K> 表示要操作的主键类型,由子接口实现;
 * @param <V> 表示要操作的VO类型,由子接口实现;
 */
public interface IDAO<K,V> {
	/**
	 * 实现数据的增加操作
	 * @param vo 包含要增加数据的VO对象;
	 * @return 数据保存成功返回true,失败返回false;
	 * @throws Exception SQL执行异常;
	 */
	public boolean doCreate(V vo) throws Exception;
	/**
	 * 实现数据的修改操作,根据id进行全部字段数据的修改;
	 * @param vo 包含了要修改的数据信息,需提供ID内容;
	 * @return 数据修改成功返回true,失败返回false;
	 * @throws Exception SQL执行异常;
	 */
	public boolean doUpdate(V vo) throws Exception;
	/**
	 * 执行数据的批量删除操作,所有要删除的数据以Set集合的形式保存;
	 * @param ids 包含了所有要删除的数据ID,不包含重复内容;
	 * @return 删除的数据个数和要删除的个数相同代表删除成功,返回true,失败返回false;
	 * @throws Exception SQL执行异常;
	 */
	public boolean doRemoveBatch(Set<K> ids) throws Exception;
	/**
	 * 根据雇员/部门编号查询指定的雇员/部门信息;
	 * @param id 要查询的雇员/部门编号
	 * @return 如果雇员/部门信息存在,则将数据以VO类对象的形式返回,如果雇员/部门数据不存在,则返回null
	 * @throws Exception SQL执行异常;
	 */
	public V findById(K id) throws Exception;
	/**
	 * 查询数据表的全部记录,并且以集合的形式返回;
	 * @return 如果表中有数据,则将所有的数据封装为VO对象后利用List集合返回,如果没有数据,那么集合的长度为0(size()==0),不是null;
	 * @throws Exception SQL执行异常;
	 */
	public List<V> findAll() throws Exception;
	/**
	 * 执行模糊查询,查询结果以集合的形式返回,并分页显示;
	 * @param currentPage 当前所在的页;
	 * @param lineSize 每页显示的数据行数;
	 * @param column 模糊查询的数据列字段;
	 * @param keyWord 模糊查询的关键字;
	 * @return 如果表中有数据,则将所有的数据封装为VO对象后利用List集合返回,如果没有数据,那么集合的长度为0(size()==0),不是null;
	 * @throws Exception SQL执行异常;
	 */
	public List<V> findAllSplit(Integer currentPage,Integer lineSize,String column,String keyWord) throws Exception;
	/**
	 * 进行模糊查询,并将结果统计返回;
	 * @param column 模糊查询的数据列字段;
	 * @param keyWord 模糊查询的关键字;
	 * @return 如果表中有数据,则将所有的数据封装为VO对象后利用List集合返回,如果没有数据,那么集合的长度为0(size()==0),不是null;
	 * @throws Exception SQL执行异常;
	 */
	public Integer getAllCount(String column,String keyWord) throws Exception;

}
