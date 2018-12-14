package dao.factory;

import dao.impl.DeptServiceImpl;
import dao.impl.EmpServiceImpl;
import dao.service.IDeptService;
import dao.service.IEmpService;

public class ServiceFactory {
	public static IEmpService getIEmpServiceInstance(){
		return new EmpServiceImpl() ;
	}
	public static IDeptService getIDeptServiceInstance(){
		return new DeptServiceImpl() ;
	}
}