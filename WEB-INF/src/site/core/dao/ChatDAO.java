package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Chat;

public class ChatDAO extends ConnectionPool {

		public Chat getChat(ResultSet rs) throws SQLException
	    {
			Chat ret = new Chat();
	        ret.setCht_id(rs.getLong("cht_id"));
	        ret.setCht_email(rs.getString("cht_email"));
	        ret.setCht_mensagem(rs.getString("cht_mensagem"));
	        ret.setCht_name(rs.getString("cht_name"));
		    return ret;
	    }

		public Chat getChat(long id)
	    {
	    	String sql = "SELECT * FROM site_chat WHERE cht_id = ? LIMIT 1";
		    Connection con = null;
			Chat ret = new Chat();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getChat(rs);
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

		public Chat getChat(String tag)
	    {
	    	String sql = "SELECT * FROM site_chat WHERE cht_name = ? LIMIT 1";
		    Connection con = null;
			Chat ret = new Chat();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getChat(rs);
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

	    public boolean insert(Chat item)
	    {
	    	String sql = "INSERT INTO site_chat (cht_name,cht_mensagem,cht_email) VALUES " +
	    			"(?,?,?)";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getCht_name());
		       pstmt.setString(2, item.getCht_mensagem());
		       pstmt.setString(3, item.getCht_email());
		       ret = pstmt.executeUpdate() == 1;
		       cleanUp(pstmt);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
		    return ret;
	    }

	    public boolean save(Chat item)
	    {
	    	String sql = "UPDATE site_chat SET cht_name = ?, cht_mensagem = ?, cht_email = ? WHERE cht_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getCht_name());
		       pstmt.setString(2, item.getCht_mensagem());
		       pstmt.setString(3, item.getCht_email());
		       pstmt.setLong(4, item.getCht_id());
		       ret = pstmt.executeUpdate() == 1;
		       cleanUp(pstmt);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
		    return ret;
	    }

	    public boolean delete(Chat item)
	    {
	    	String sql = "DELETE FROM site_chat WHERE cht_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getCht_id());
		       ret = pstmt.executeUpdate() == 1;
		       cleanUp(pstmt);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
		    return ret;
	    }

		public List<Chat> getList()
		{
			List<Chat> ret = new ArrayList<Chat>();
	    	String sql = "SELECT * FROM site_chat WHERE cht_email = 1 ORDER BY cht_mensagem";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getChat(rs));
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

		public List<Chat> getList(boolean withSession)
		{
			List<Chat> ret = new ArrayList<Chat>();
	    	String sql = "SELECT * FROM site_chat WHERE cht_email = ? ORDER BY cht_mensagem";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setInt(1, (withSession?2:1));
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getChat(rs));
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

		public List<Chat> getList(String order)
		{
			List<Chat> ret = new ArrayList<Chat>();
	    	String sql = "SELECT * FROM site_chat ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getChat(rs));
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
