package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBCP {
	// ORACLE
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@192.168.0.26:1521:xe";
	private final String USER = "zinus";
	private final String PASS = "1234";
	
	// MYSQL
//	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
//	private final String URL = "jdbc:mysql://localhost:3306/musthave?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
//	private final String USER = "musthave";
//	private final String PASS = "1234";
	
	public Connection getConn() {
		Connection conn = null;
		
		/* Oracle 설정 (name, url, username, password) 체크
		<Resource
			name="orcl/oracle"
			auth="Container"
			type="javax.sql.DataSource"
			driverClassName="oracle.jdbc.OracleDriver"
			url="jdbc:oracle:thin:@localhost:1521:orcl"
			username="scott"
			password="tiger"
			maxActive="50"
			maxWait="-1" />
		*/
		
		try {
			// context.xml 의 Resource에 설정된 데이터에 접근하여 DataSource 얻기
			// java:comp/env/ + name 값 세팅
			DataSource ds = (DataSource) new InitialContext().lookup("java:comp/env/orcl/oracle");
			// Connection
			conn = ds.getConnection();
			
		} catch (NamingException | SQLException e) {
			conn = getJDBC();
		}
		
		return conn;
	}
	
	public Connection getJDBC() {
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 사용한 자원 반환
	public void close(Connection conn, Statement st) {
		try {
			if (st != null) st.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
