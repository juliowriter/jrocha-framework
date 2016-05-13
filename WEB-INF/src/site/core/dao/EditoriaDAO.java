package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Editoria;

public class EditoriaDAO extends ConnectionPool {

		public Editoria getEditoria(ResultSet rs) throws SQLException
	    {
			Editoria ret = new Editoria();
	        ret.setEdt_id(rs.getLong("edt_id"));
	        ret.setEdt_status(rs.getInt("edt_status"));
	        ret.setEdt_titulo(rs.getString("edt_titulo"));
	        ret.setEdt_tag(rs.getString("edt_tag"));
		    return ret;
	    }

		public Editoria getEditoria(long id)
	    {
	    	String sql = "SELECT * FROM site_editorias WHERE edt_id = ? LIMIT 1";
		    Connection con = null;
			Editoria ret = new Editoria();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getEditoria(rs);
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

		public Editoria getEditoria(String tag)
	    {
	    	String sql = "SELECT * FROM site_editorias WHERE edt_tag = ? LIMIT 1";
		    Connection con = null;
			Editoria ret = new Editoria();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getEditoria(rs);
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

	    public long insert(Editoria item)
	    {
	    	String sql = "INSERT INTO site_editorias (edt_tag,edt_titulo,edt_status) VALUES " +
	    			"(?,?,?)";
		    Connection con = null;
		    long ret = 0;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getEdt_tag());
		       pstmt.setString(2, item.getEdt_titulo());
		       pstmt.setInt(3, item.getEdt_status());
		       pstmt.executeUpdate();
		       cleanUp(pstmt);
		       PreparedStatement pstmtS = con.prepareStatement("SELECT edt_id FROM site_editorias WHERE edt_tag = ? ORDER BY edt_id DESC LIMIT 1");
		       pstmtS.setString(1, item.getEdt_tag());
		       ResultSet rs = pstmtS.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("edt_id");
		       cleanUp(pstmtS, rs);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
		    return ret;
	    }

	    public boolean save(Editoria item)
	    {
	    	String sql = "UPDATE site_editorias SET edt_tag = ?, edt_titulo = ?, edt_status = ? WHERE edt_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getEdt_tag());
		       pstmt.setString(2, item.getEdt_titulo());
		       pstmt.setInt(3, item.getEdt_status());
		       pstmt.setLong(4, item.getEdt_id());
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

	    public boolean delete(Editoria item)
	    {
	    	String sql = "DELETE FROM site_editorias WHERE edt_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getEdt_id());
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

		public List<Editoria> getList()
		{
			List<Editoria> ret = new ArrayList<Editoria>();
	    	String sql = "SELECT * FROM site_editorias WHERE edt_status = 1 ORDER BY edt_titulo";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getEditoria(rs));
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

		public List<Editoria> getList(boolean withSession)
		{
			List<Editoria> ret = new ArrayList<Editoria>();
	    	String sql = "SELECT * FROM site_editorias WHERE edt_status = ? ORDER BY edt_titulo";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setInt(1, (withSession?2:1));
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getEditoria(rs));
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

		public List<Editoria> getList(String order)
		{
			List<Editoria> ret = new ArrayList<Editoria>();
	    	String sql = "SELECT * FROM site_editorias ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getEditoria(rs));
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
