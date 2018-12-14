package dao.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * 本类负责数据库的连接和关闭操作,实例化本类的对象就意味着要进行数据库的开发<br>
 * 本类的构造方法可进行数据库驱动加载和数据库连接的取得;
 * @author pp
*/
public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:pp";
	private static final String DBUSER = "scott";
	private static final String PASSWORD = "tiger";
	private Connection conn = null;
	//构造方法负责对conn对象进行实例化;
	
	public DatabaseConnection(){
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public Connection getConnection(){//返回数据库的连接对象Connection实例化对象;
		return this.conn;
	}
	public void close() {//负责数据库的关闭;
		if(this.conn != null){
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
