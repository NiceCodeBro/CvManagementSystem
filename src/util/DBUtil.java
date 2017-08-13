package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {
	
	public Connection getConnection(){
		Connection con = null;
		InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("/db.properties");
		Properties properties = new Properties();
		try{
			properties.load(inputStream);
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	
	public void closeConnection(Connection con){
		if( con == null) return;
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	
	}
}
