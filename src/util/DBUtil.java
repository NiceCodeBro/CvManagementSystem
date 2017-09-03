package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import dao.CVDao;


public class DBUtil {
	private Connection con;
	private static Logger logger = LogManager.getLogger(DBUtil.class);
	public Connection getConnection(){
		try {
			if (con == null || con.isClosed()) {
				try{
					InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("/db.properties");
					Properties properties = new Properties();
					
					properties.load(inputStream);
					String driver = properties.getProperty("driver");
					String url = properties.getProperty("url");
					String user = properties.getProperty("user");
					String password = properties.getProperty("password");
					Class.forName(driver);
					con = DriverManager.getConnection(url,user,password);
					return con;
					
				}catch(IOException e){
					logger.error("getConnection method |IOException: " + e.getMessage());
				}catch(ClassNotFoundException e){
					logger.error("getConnection method |ClassNotFoundException: " + e.getMessage());
				}catch(SQLException e){
					logger.error("getConnection method |SQLException: " + e.getMessage());
				}
			}
		} catch (SQLException e) {
			logger.error("getConnection method |SQLException: " + e.getMessage());
		}
		return con;
		
		
	}
	
	public void closeConnection(Connection con){
		if( con == null) return;
		try{
			con.close();
		}catch(SQLException e){
			logger.error("closeConnection method |SQLException: " + e.getMessage());
		}
	
	}
	public void closePreparedSatement(PreparedStatement pre)
	{
		if( pre == null) return;
		try{
			pre.close();
		}catch(SQLException e){
			logger.error("closePreparedSatement method |SQLException: " + e.getMessage());
		}
	}
	public void closeResultSet(ResultSet rs)
	{
		if( rs == null) return;
		try{
			rs.close();
		}catch(SQLException e){
			logger.error("closeResultSet method |SQLException: " + e.getMessage());
		}
	}
}
