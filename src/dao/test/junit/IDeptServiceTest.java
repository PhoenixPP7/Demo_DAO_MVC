package dao.test.junit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import dao.factory.ServiceFactory;
import dao.vo.Dept;
import dao.vo.Emp;

public class IDeptServiceTest {

	@Test
	public void testInsert() {
		Dept vo = new Dept();
		vo.setDeptno(11);
		vo.setDname("Insert-Dname");
		vo.setLoc("Insert-loc");
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().insert(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		Dept vo = new Dept();
		vo.setDeptno(11);
		vo.setDname("Update-Dname");
		vo.setLoc("Update-loc");
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().update(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		Set<Integer> ids = new HashSet<Integer>();
		ids.add(11);
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().delete(ids));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGet() {
		try {
			TestCase.assertNotNull(ServiceFactory.getIDeptServiceInstance().get(10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testList() {
		try {
			TestCase.assertTrue(ServiceFactory.getIDeptServiceInstance().list().size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
