package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Block;

public class BlockDAO extends ConnectionPool {

		public Block getBlock(ResultSet rs) throws SQLException
	    {
			Block ret = new Block();
	        ret.setBlk_id(rs.getLong("blk_id"));
	        ret.setBlk_tag(rs.getString("blk_tag"));
	        ret.setBlk_text(rs.getString("blk_text"));
	        ret.setBlk_name(rs.getString("blk_name"));
		    return ret;
	    }

		public Block getBlock(long id)
	    {
	    	String sql = "SELECT * FROM site_blocks WHERE blk_id = ? LIMIT 1";
		    Connection con = null;
			Block ret = new Block();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getBlock(rs);
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

		public Block getBlock(String tag)
	    {
	    	String sql = "SELECT * FROM site_blocks WHERE blk_tag = ? LIMIT 1";
		    Connection con = null;
			Block ret = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getBlock(rs);
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

		public String[] getTags()
		{
			String[] ret = null;
	    	String sql = "SELECT DISTINCT blk_tag FROM site_blocks ORDER BY blk_tag";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       String tags = "";
		       while(rs.next())
		         tags += rs.getString("blk_tag") + ",";
		       cleanUp(pstmt, rs);
		       if(tags.length() > 0)
		       {
		    	   tags = tags.substring(0,tags.length()-1);
		    	   ret = tags.split(",");
		       }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
		    return ret;
		}

		public List<Block> getList(String filtro, String order)
		{
			List<Block> ret = new ArrayList<Block>();
	    	String sql = "SELECT * FROM site_blocks ";
	    	if(filtro!=null && !filtro.equals(""))
	    		sql += "WHERE blk_tag = '" + filtro + "' ";
	    	sql += "ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new BlockDAO().getBlock(rs));
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

	    public void savePrevious(Block item, String user)
	    {
	    	String sql = "INSERT INTO site_blocks_history (blk_tag,blk_name,blk_text,blk_user,blk_id,blk_datetime) VALUES " +
	    			"(?,?,?,?,?,SYSDATE())";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getBlk_tag());
		       pstmt.setString(2, item.getBlk_name());
		       pstmt.setString(3, item.getBlk_text());
		       pstmt.setString(4, user);
		       pstmt.setLong(5, item.getBlk_id());
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

	    public long insert(Block item)
	    {
	    	String sql = "INSERT INTO site_blocks (blk_tag,blk_name,blk_text) VALUES " +
	    			"(?,?,?)";
		    Connection con = null;
		    long ret = 0;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getBlk_tag());
		       pstmt.setString(2, item.getBlk_name());
		       pstmt.setString(3, item.getBlk_text());
		       pstmt.executeUpdate();
		       cleanUp(pstmt);
		       PreparedStatement pstmtS = con.prepareStatement("SELECT blk_id FROM site_blocks WHERE blk_tag = ? ORDER BY blk_id DESC LIMIT 1");
		       pstmtS.setString(1, item.getBlk_tag());
		       ResultSet rs = pstmtS.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("blk_id");
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

	    public boolean save(Block item)
	    {
	    	String sql = "UPDATE site_blocks SET blk_tag = ?,blk_name = ?,blk_text = ? WHERE blk_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getBlk_tag());
		       pstmt.setString(2, item.getBlk_name());
		       pstmt.setString(3, item.getBlk_text());
		       pstmt.setLong(4, item.getBlk_id());
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

	    public boolean delete(Block item)
	    {
	    	String sql = "DELETE FROM site_blocks WHERE blk_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getBlk_id());
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

	    public void restorePrevious(Block item, long hid)
	    {
	    	String sql = "SELECT * FROM site_blocks_history WHERE bkh_id = ? LIMIT 1";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, hid);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		       {
		    	   Block blk = this.getBlock(rs);
		    	   blk.save();
		       }
		       cleanUp(pstmt);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
	    }

		public String[] getVersions(Block blk)
		{
			String[] ret = null;
	    	String sql = "SELECT bkh_id, blk_datetime, blk_user FROM site_blocks_history WHERE blk_id = ? ORDER BY blk_datetime DESC";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, blk.getBlk_id());
		       ResultSet rs = pstmt.executeQuery();
		       String versions = "";
		       while(rs.next())
		         versions += rs.getString("bkh_id") + ";" + rs.getString("blk_datetime") + ";" + rs.getString("blk_user") + ",";
		       cleanUp(pstmt, rs);
		       if(versions.length() > 0)
		       {
		    	   versions = versions.substring(0,versions.length()-1);
		    	   ret = versions.split(",");
		       }
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
