package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String driverClassName;
	private static final String url;
	private static final String username;
	private static final String password;
	private static Connection conn;
	static{
		driverClassName = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://127.0.0.1:3306/jjdev?useUnicode=true&characterEncoding=euckr";
		username="root";
		password = "java0000";
	}
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driverClassName);
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
}
