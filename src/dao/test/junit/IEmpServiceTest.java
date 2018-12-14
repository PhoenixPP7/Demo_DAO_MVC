package dao.test.junit;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import dao.factory.ServiceFactory;
import dao.vo.Emp;

public class IEmpServiceTest {
	private static int empno;
	static{
		empno = new Random().nextInt(10000);//动态生成一个empno数据
	}

	@Test
	public void testInsert() {
		Emp vo = new Emp();
		vo.setEmpno(empno);
		vo.setEname("PP-"+ empno);
		vo.setJob("Job-" +empno);
		vo.setHiredate(new Date());
		vo.setSal((double) empno);
		vo.setComm((double) empno);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().insert(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		Emp vo = new Emp();
		vo.setEmpno(4487);
		vo.setEname("dd");
		vo.setJob("Engineer");
		vo.setHiredate(new Date());
		vo.setSal(7777.777);
		vo.setComm(666.66666);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().update(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		Set<Integer> ids = new HashSet<Integer>();
		ids.add(4487);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().delete(ids));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGet() {
		try {
			TestCase.assertNotNull(ServiceFactory.getIEmpServiceInstance().get(7566));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testList() {
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().list().size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testListIntIntStringString() {
		try {
			Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().list(1, 5, "ename", "") ;
			int count = (Integer)map.get("empCount");
			List<Emp> all = (List<Emp>)map.get("allEmps");
			TestCase.assertTrue(count > 0 && all.size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
