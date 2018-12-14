package dao.factory;

import java.sql.Connection;

import dao.dao.IDeptDAO;
import dao.dao.IEmpDAO;
import dao.dao.impl.DeptDAOImpl;
import dao.dao.impl.EmpDAOImpl;

public class DAOFactory {
	//[getIEmpDAOInstance]方法提供[IEmpDAO]接口对象;
	//实例化[IEmpDAO]接口的子类[EmpDAOImpl]需要传入[Connection]对象;
	//工厂类的特征=外层不需要知道具体的子类;
	public static IEmpDAO getIEmpDAOInstance(Connection conn){
		return new EmpDAOImpl(conn);
	}
	public static IDeptDAO getIDeptDAOInstance(Connection conn){
		return new DeptDAOImpl(conn);
	}

}
