package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.dbc.DatabaseConnection;
import dao.factory.DAOFactory;
import dao.service.IEmpService;
import dao.vo.Emp;

public class EmpServiceImpl implements IEmpService {
	//此业务层实现类的对象内部提供一个数据库连接类的实例化对象;[DatabaseConnection]对象一旦被调用,数据库就会被连接;
	private DatabaseConnection dbc = new DatabaseConnection();

	public boolean insert(Emp vo) throws Exception {
		try {
			//如果要增加的雇员编号不存在,则findById()返回的结果就是null,null表示可以进行新雇员数据的增加;
			if(DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findById(vo.getEmpno()) == null){
				return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doCreate(vo) ;
			}
			return false;
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	public boolean update(Emp vo) throws Exception {
		try {
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doUpdate(vo) ;
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids) ;
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	public Emp get(int ids) throws Exception {
		try {
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findById(ids);
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	public List<Emp> list() throws Exception {
		try {
			return DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("allEmps", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord)) ;
			map.put("empCount", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord)) ;
			return map;
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}
}
