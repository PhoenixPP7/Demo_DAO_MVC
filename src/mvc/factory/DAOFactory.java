package mvc.factory;

import java.sql.Connection;

import mvc.dao.IAdminDAO;
import mvc.dao.IDeptDAO;
import mvc.dao.IEmpDAO;
import mvc.dao.impl.AdminDAOImpl;
import mvc.dao.impl.DeptDAOImpl;
import mvc.dao.impl.EmpDAOImpl;

public class DAOFactory {
	public static IDeptDAO getIDeptDAOInstance(Connection conn){
		return new DeptDAOImpl(conn);
	}
	public static IEmpDAO getIEmpDAOInstance(Connection conn){
		return new EmpDAOImpl(conn);
	}
	
	public static IAdminDAO getIAdminDAOInstance(Connection conn) {
		return new AdminDAOImpl(conn);
	}
}
