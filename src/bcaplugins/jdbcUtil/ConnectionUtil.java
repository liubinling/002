package bcaplugins.jdbcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import bcaplugins.handlers.OpenFilesListAction;

public class ConnectionUtil {
	private final static Logger logger=OpenFilesListAction.getLogger();
	public static Connection getConnection(){
		Properties props=new Properties();
		InputStream in=ConnectionUtil.class.getResourceAsStream("/bcaplugins/dataSource/DataSource.properties");
		try {
			props.load(in);
			String driverName=props.getProperty("driverName");
			String url=props.getProperty("url");
			String userName=props.getProperty("userName");
			String passWord=props.getProperty("passWord");
			Class.forName(driverName);
			Connection con=DriverManager.getConnection(url, userName, passWord);
			return con;
		} catch (Exception e) {
			logger.info("获取数据库连接时发生异常，异常信息：  "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
