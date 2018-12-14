package mvc.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractDAOImpl{
	protected Connection conn;
	public AbstractDAOImpl(Connection conn) {
		this.conn = conn ;
	}
	//tableName=要删除数据的表名称;idName=取得要删除数据所在表的主键列名称;
	public boolean removeHandle(Set<?> ids,String tableName,String idName) throws Exception {
		if (ids.size() == 0){
			return false;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM ").append(tableName).append(" WHERE ").append(idName).append(" IN ( ");
		Iterator<?> iter =ids.iterator();
		while(iter.hasNext()) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length() - 1 , sql.length()).append(" )");
		PreparedStatement pstmt = this.conn.prepareStatement(sql.toString());
		return pstmt.executeUpdate() == ids.size();
	}
	
	public Integer countHandle(String tableName,String column, String keyWord) throws Exception {
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + column + " LIKE ?";
		PreparedStatement pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyWord+"%");
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		} else {
			return 0;
		}
	}
}

/*
本程序主要解决的操作时删除数据操作的代码重复问题,所以考虑定义一个公共的工具类,以减少代码的删除中所带来的麻烦.
之所以使用抽象类,是不希望此类可以直接被使用.
*/