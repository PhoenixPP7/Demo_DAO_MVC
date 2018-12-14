package dao.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.vo.Emp;

/**
 * 此类定义emp表业务层的执行标准;此类负责数据库的打开与关闭操作;
 * 此类可以通过[DAOFactory]类取得[IEmpDAO]接口对象;
 * @author Administrator
 *
 */
public interface IEmpService {
	/**
	 * 实现雇员数据的增加操作,本操作需要调用[IEmpDAO]接口的如下方法=
	 * <li>[IEmpDAO].[findById()]方法,判断要增加的雇员id是否存在;
	 * <li>如果不存在,则调用[IEmpDAO].[doCreate()]方法,返回操作的结果;
	 * @param vo 包含了要增加数据的VO对象;
	 * @return 增加的ID重复或保存失败返回false,否则返回true;
	 * @throws Exception SQL执行异常;
	 */
	public boolean insert(Emp vo) throws Exception ;
	/**
	 * 实现雇员数据的修改操作,本操作需要调用[IEmpDAO]的[doUpdate()]方法;
	 * @param vo 包含了要修改数据的VO对象;
	 * @return 修改成功返回true,否则返回false;
	 * @throws Exception SQL执行异常;
	 */
	public boolean update(Emp vo) throws Exception ;
	/**
	 * 实现雇员数据的删除操作,本操作需要调用[IEmpDAO]接口的[doRemoveBatch()]方法;	
	 * @param ids 包含了所有要删除数据的集合,没有重复数据;
	 * @return 删除成功返回true,否则返回false;
	 * @throws Exception SQL执行异常;
	 */
	public boolean delete(Set<Integer> ids) throws Exception ;
	/**
	 * 根据雇员编号查找雇员的完整信息,调用[IEmpDAO]接口的[findById()]方法;
	 * @param ids 要查找的雇员编号;
	 * @return 查找成功后雇员信息以VO对象返回,否则返回null;
	 * @throws Exception SQL执行异常;
	 */
	public Emp get(int ids) throws Exception ;
	/**
	 * 查询全部雇员信息,调用[IEmpDAO]接口的[findAll()]方法;
	 * @return 查询结果以List集合的形式返回,如果没有数据则集合的长度为0;
	 * @throws Exception SQL执行异常;
	 */
	public List<Emp> list() throws Exception ;
	/**
	 * 实现数据的模糊查询与数据统计功能,本操作需要调用[IEmpDAO]接口的两个方法:
	 * [findAllSplit()]方法,查询出所有的表数据,返回值为[List<Emp>];
	 * [getAllCount()]方法,查询表所有数据量,返回值为[Integer];
	 * @param currentPage 当前所在页;
	 * @param lineSize 每页显示的记录数;
	 * @param column 模糊查询的数据列;
	 * @param keyWord 模糊查询关键字;
	 * @return 本方法需要返回多种数据类型,且数据类型不统一,故需要使用Map集合接受返回值,返回值value的类型设置为Object;返回内容如下:<br>
	 * <li>定义如果key = allEmps,则value = [IEmpDAO].[findAllSplit()]方法返回的List<Emp>;
	 * <li>定义如果key = empCount,则value = [IEmpDAO].[getAllCount()]方法返回的Integer;
	 * @throws Exception SQL执行异常;
	 */
	public Map<String,Object> list(int currentPage,int lineSize,String column,String keyWord) throws Exception ;
}
