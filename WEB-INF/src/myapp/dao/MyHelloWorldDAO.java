package myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import myapp.MyHelloWorld;
import site.core.ConnectionPool;

public class MyHelloWorldDAO extends ConnectionPool {

			public MyHelloWorld getWelcomeText(long id)
		    {
				MyHelloWorld ret = null;
		    	String sql = "SELECT * FROM myhelloworld_texts WHERE id = ? LIMIT 1";
			    Connection con = null;
			    try{
			       con = getConnection();
			       PreparedStatement pstmt = con.prepareStatement(sql);
			       pstmt.setLong(1, id);
			       ResultSet rs = pstmt.executeQuery();
			       if(rs.next())
			         ret = this.getWelcomeText(rs);
			       cleanUp(pstmt, rs);
			    } catch (SQLException e) {
			      e.printStackTrace();
			    } catch (Exception e) {
			      e.printStackTrace();
			    } finally {
			      closeConnection(con);
			    }
			    return ret;
		    }

			public MyHelloWorld getWelcomeText(ResultSet rs) throws SQLException
		    {
		         MyHelloWorld ret = new MyHelloWorld();
		    	 ret.setId(rs.getLong("id"));
		         ret.setWelcome_text(rs.getString("welcome_text"));
		         return ret;
		    }
			
			public void insert(MyHelloWorld item)
		    {
		    	String sql = "INSERT INTO myhelloworld_texts (welcome_text) VALUES (?)";
			    Connection con = null;
			    try{
			       con = getConnection();
			       PreparedStatement pstmt = con.prepareStatement(sql);
			       pstmt.setString(1, item.getWelcome_text());
			       pstmt.executeUpdate();
			       cleanUp(pstmt);
			    } catch (SQLException e) {
			      e.printStackTrace();
			    } catch (Exception e) {
			      e.printStackTrace();
			    } finally {
			      closeConnection(con);
			    }
		    }

			public void save(MyHelloWorld item)
		    {
		    	String sql = "UPDATE myhelloworld_texts SET welcome_text = ? WHERE id = ? LIMIT 1";
			    Connection con = null;
			    try{
			       con = getConnection();
			       PreparedStatement pstmt = con.prepareStatement(sql);
			       pstmt.setString(1, item.getWelcome_text());
			       pstmt.setLong(2, item.getId());
			       pstmt.executeUpdate();
			       cleanUp(pstmt);
			    } catch (SQLException e) {
			      e.printStackTrace();
			    } catch (Exception e) {
			      e.printStackTrace();
			    } finally {
			      closeConnection(con);
			    }
		    }

		    public void delete(MyHelloWorld item)
		    {
		    	String sql = "DELETE FROM myhelloworld_texts WHERE id = ? LIMIT 1";
			    Connection con = null;
			    try{
			       con = getConnection();
			       PreparedStatement pstmt = con.prepareStatement(sql);
			       pstmt.setLong(1, item.getId());
			       pstmt.executeUpdate();
			       cleanUp(pstmt);
			    } catch (SQLException e) {
			      e.printStackTrace();
			    } catch (Exception e) {
			      e.printStackTrace();
			    } finally {
			      closeConnection(con);
			    }
		    }

		    public Collection<MyHelloWorld> getTextList()
			{
				Collection<MyHelloWorld> ret = new ArrayList<MyHelloWorld>();
		    	String sql = "SELECT * FROM myhelloworld_texts ORDER BY welcome_text LIMIT 1000";
			    Connection con = null;
			    try{
			       con = getConnection();
			       PreparedStatement pstmt = con.prepareStatement(sql);
			       ResultSet rs = pstmt.executeQuery();
			       while(rs.next())
			         ret.add(this.getWelcomeText(rs));
			       cleanUp(pstmt, rs);
			    } catch (SQLException e) {
			      e.printStackTrace();
			    } catch (Exception e) {
			      e.printStackTrace();
			    } finally {
			      closeConnection(con);
			    }
			    return ret;
			}
		    
	 }
