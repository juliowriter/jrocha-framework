package site.core;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

	public static Connection getConnection() throws SQLException {
		Connection ret = null;
		int xx = 0;
		boolean error = false;
		while(xx <= 10)
		{
			error = false;
			try {
				ret = getConnectionPool();
				PreparedStatement pstmt = ret.prepareStatement("SELECT 1");
		        ResultSet rs = pstmt.executeQuery();
		        rs.close();
		        pstmt.close();
			} catch (SQLException e) {
				System.out.println("JRocha - Invalid Database Connection (" + (xx++) + "/10) - error: " + e.getMessage());
				if(xx == 10)
					System.out.println("JRocha - Sorry, can't connect to your database.");
				error = true;
			}
			if(!error)
				break;
		}
		return ret;
	}

	public static Connection getConnectionPool() throws SQLException {
			Connection ret = null;
				InitialContext initContext;
				try {
					initContext = new InitialContext();
					Context envContext = (Context) initContext.lookup("java:/comp/env");
					DataSource ds = (DataSource) envContext.lookup("jdbc/jrochaDB");
					ret = ds.getConnection();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			return ret;
		}

	   public static void closeConnection(Connection con)
		{
			try {
				if (con!=null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public static void cleanUp(Connection con) throws SQLException {
			cleanUp(con, null, null);
		}

		public static void cleanUp(Connection con, Statement stmt) throws SQLException {
			cleanUp(con, stmt, null);
		}

		public static void cleanUp(Statement stmt, ResultSet rs) throws SQLException {
			cleanUp(null, stmt, rs);
		}

		public static void cleanUp(Statement stmt) throws SQLException {
			cleanUp(null, stmt, null);
		}

		public static void cleanUp(ResultSet rs) throws SQLException {
			cleanUp(null, null, rs);
		}

		public static void cleanUp(Connection con, Statement stmt, ResultSet rs) throws SQLException {
			if ( rs != null) {
				rs.close();
			}
			if ( stmt != null) {
				stmt.close();
			}
			if ( con != null && !con.isClosed()) {
				con.close();
			}
		}

		public static Connection getConnectionSingle(String surl, String suser, String spwd) throws SQLException {
			Connection ret = null;
			try {
				ret = DriverManager.getConnection(surl,
													suser,
												   spwd);
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			catch (Exception geral) {
				geral.printStackTrace();
			}
			return ret;
		}
}
