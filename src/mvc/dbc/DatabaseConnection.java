package mvc.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver" ;
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:pp" ;
	private static final String USER = "scott" ;
	private static final String PASSWORD = "tiger" ;
	private Connection conn;
	public DatabaseConnection(){
		try{
			Class.forName(DBDRIVER) ;
			this.conn = DriverManager.getConnection(DBURL,USER,PASSWORD) ;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return this.conn ;
	}
	public void close() {
		if(this.conn != null){
			try {
				this.conn.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
