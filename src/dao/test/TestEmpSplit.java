package dao.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.factory.ServiceFactory;
import dao.vo.Emp;

public class TestEmpSplit {
	public static void main(String[] args) {
		try {
			Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().list(1, 5, "ename", "") ;
			int count = (Integer)map.get("empCount");
			System.out.println("数据量是 = " + count);
			List<Emp> all = (List<Emp>)map.get("allEmps");
			Iterator<Emp> iter = all.iterator();
			while (iter.hasNext()) {
				Emp vo = iter.next();
				System.out.println(vo.getEname() + " ; " + vo.getJob());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
