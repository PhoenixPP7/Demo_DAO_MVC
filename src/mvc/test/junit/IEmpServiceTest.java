package mvc.test.junit;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;
import mvc.factory.ServiceFactory;
import mvc.vo.Emp;

import org.junit.Test;


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
	}/*
	
	@Test
	public void testUpdate() {
		Emp vo = new Emp();
		vo.setEmpno(2210);
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
		ids.add(4906);
		ids.add(7095);
		try {
			TestCase.assertTrue(ServiceFactory.getIEmpServiceInstance().delete(ids));
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
	
	@Test
	public void testUpdatePre() {
		try {
			Emp vo = ServiceFactory.getIEmpServiceInstance().updatePre(8889);
			TestCase.assertNotNull(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testListStringStringIntInt() {
		try {
			Map<String, Object> map = ServiceFactory.getIEmpServiceInstance().list("ename", "",1, 5) ;
			int count = (Integer)map.get("empCount");
			List<Emp> all = (List<Emp>)map.get("allEmps");
			System.out.println(count);
			TestCase.assertTrue(count > 0 && all.size() > 0);
			
//			Map<String,Object> map = ServiceFactory.getIEmpServiceInstance().list("ename", "", 1, 5);
//			List<Emp> all = (List<Emp>) map.get("allEmps");
//			int allRecorders = (Integer) map.get("empCount");
//			Iterator<Emp> iter = all.iterator();
//			while(iter.hasNext()){
//				Emp vo = iter.next();
//				vo.getEmpno();
//				vo.getEname();
//				vo.getJob();
//				vo.getHiredate();
//				vo.getSal();
//				vo.getComm();
//			}
				
			//map.put("allEmps", DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
			//map.put("empCount",	DAOFactory.getIEmpDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
//			TestCase.assertNotNull(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
