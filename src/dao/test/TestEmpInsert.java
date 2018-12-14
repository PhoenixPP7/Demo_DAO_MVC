package dao.test;

import java.util.Date;

import dao.factory.ServiceFactory;
import dao.vo.Emp;

public class TestEmpInsert {
	public static void main(String[] args) {
		Emp vo = new Emp();
		vo.setEmpno(8889);
		vo.setEname("PP");
		vo.setJob("Coder");
		vo.setHiredate(new Date());
		vo.setSal(9999.99);
		vo.setComm(888.888);
		
		try {
			System.out.println(ServiceFactory.getIEmpServiceInstance().insert(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
