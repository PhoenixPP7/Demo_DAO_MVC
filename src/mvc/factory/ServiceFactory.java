package mvc.factory;

import mvc.service.IAdminService;
import mvc.service.IDeptService;
import mvc.service.IEmpService;
import mvc.service.impl.AdminServiceImpl;
import mvc.service.impl.DeptServiceImpl;
import mvc.service.impl.EmpServiceImpl;

public class ServiceFactory {
	public static IDeptService getIDeptServiceInstance(){
		return new DeptServiceImpl();
	}
	public static IEmpService getIEmpServiceInstance(){
		return new EmpServiceImpl();
	}
	public static IAdminService getIAdminServiceInstance() {
		return new AdminServiceImpl();
	}
}
