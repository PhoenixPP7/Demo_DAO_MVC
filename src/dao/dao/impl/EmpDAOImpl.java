package dao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dao.dao.IEmpDAO;
import dao.vo.Emp;

public class EmpDAOImpl implements IEmpDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	/**
	 * 必须先一共Connection接口对象,数据层才能进行原子性数据操作;实际开发中业务层要调用数据层,数据库的连接和关闭交由业务层处理比较好;
	 * @param conn 表示数据库连接对象;
	 */
	public EmpDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean doCreate(Emp vo) throws Exception {
		String sql = "INSERT INTO emp(empno,ename,job,hiredate,sal,comm) VALUES(?,?,?,?,?,?)";
		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setInt(1, vo.getEmpno());
		this.pstmt.setString(2, vo.getEname());
		this.pstmt.setString(3, vo.getJob());
		this.pstmt.setDate(4,new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(5,vo.getSal());
		this.pstmt.setDouble(6, vo.getComm());
		return this.pstmt.executeUpdate() > 0;
	}

	public boolean doUpdate(Emp vo) throws Exception {
		String sql = "UPDATE emp SET ename=?,job=?,hiredate=?,sal=?,comm=? WHERE empno=? ";
		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getEname());
		this.pstmt.setString(2, vo.getJob());
		this.pstmt.setDate(3, new java.sql.Date(vo.getHiredate().getTime()));
		this.pstmt.setDouble(4, vo.getSal());
		this.pstmt.setDouble(5, vo.getComm());
		this.pstmt.setInt(6, vo.getEmpno());
		return this.pstmt.executeUpdate() > 0;
	}

	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		if (ids == null || ids.size() == 0){//要删除的数据为空,没有需要删除的数据;
			return false;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM emp WHERE empno IN(");
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()){
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length() - 1, sql.length()).append(")");
		this.pstmt = conn.prepareStatement(sql.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}

	public Emp findById(Integer id) throws Exception {
		Emp vo = null;
		String sql = "SELECT empno,ename,job,hiredate,sal,comm FROM emp WHERE empno=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()){
			vo = new Emp();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setHiredate(rs.getDate(4));
			vo.setSal(rs.getDouble(5));
			vo.setComm(rs.getDouble(6));
		}
		return vo;
	}

	public List<Emp> findAll() throws Exception {
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT empno,ename,job,hiredate,sal,comm FROM emp";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			Emp vo = new Emp();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setHiredate(rs.getDate(4));
			vo.setSal(rs.getDouble(5));
			vo.setComm(rs.getDouble(6));
			all.add(vo);
		}
		return all;
	}

	public List<Emp> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
		List<Emp> all = new ArrayList<Emp>();
		String sql = "SELECT * FROM "
				+ " (SELECT empno,ename,job,hiredate,sal,comm, ROWNUM rn "
				+ " FROM emp "
				+ " WHERE " + column + " LIKE ? AND ROWNUM<=?) temp "
				+ " WHERE temp.rn>? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,"%" + keyWord + "%");
		this.pstmt.setInt(2, currentPage * lineSize);
		this.pstmt.setInt(3, (currentPage - 1) * lineSize);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			Emp vo = new Emp();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setHiredate(rs.getDate(4));
			vo.setSal(rs.getDouble(5));
			vo.setComm(rs.getDouble(6));
			all.add(vo);
		}
		return all;
	}

	public Integer getAllCount(String column, String keyWord) throws Exception {
		String sql = "SELECT COUNT(empno) FROM emp WHERE " + column + " LIKE ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,"%" + keyWord + "%");
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()){
			return rs.getInt(1);
		}		
		return null;
	}
}
