package _common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {

	public static Connection dbConn() {
		Connection conn = null;
		
		String dbDrv = "oracle.jdbc.driver.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
		String dbUsr = "jspModel2";
		String dbPwd = "1234";	
		
		try {
			Class.forName(dbDrv);
			
		} catch(Exception e) {
			//e.printStackTrace();
			System.out.println(" = 오라클 드라이버 로딩 연결 실패 = ");
		}
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUsr, dbPwd);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(" = 접속 실패 = ");
		}
		return conn;
	}
	
	
	public static void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
 			if(rs != null) { rs.close(); }
 			if(pstmt != null) { pstmt.close(); }
 			if(conn != null) { conn.close(); }
 			
 			} catch (Exception e) {
 				//e.printStackTrace();
 				System.out.println("DB해제 실패");
 				} 
 			}
	
	
}
