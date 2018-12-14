package dao.impl;

import java.util.List;
import java.util.Set;

import dao.dbc.DatabaseConnection;
import dao.factory.DAOFactory;
import dao.service.IDeptService;
import dao.vo.Dept;

public class DeptServiceImpl implements IDeptService {
	//此业务层实现类的对象内部提供一个数据库连接类的实例化对象;[DatabaseConnection]对象一旦被调用,数据库就会被连接;
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean insert(Dept vo) throws Exception {
		try {
			//如果要增加的雇员编号不存在,则findById()返回的结果就是null,null表示可以进行新雇员数据的增加;
			if(DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(vo.getDeptno()) == null){
				return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doCreate(vo) ;
			}
			return false;
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Dept vo) throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdate(vo) ;
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids) ;
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Dept get(int ids) throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(ids);
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Dept> list() throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e){
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
