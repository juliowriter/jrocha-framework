package site.mail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.mail.Mailtogo;



public class MailtogoDAO extends ConnectionPool {

	public Mailtogo getMailtogo(long id)
    {
		Mailtogo ret = null;
    	String sql = "SELECT * FROM site_mailtogo WHERE mtg_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next()){
	         ret = new Mailtogo();
	    	 ret.setMtg_id(rs.getLong("mtg_id"));
	         ret.setMtg_address(rs.getString("mtg_address"));
	         ret.setMtg_from(rs.getString("mtg_from"));
	         ret.setMtg_replyto(rs.getString("mtg_replyto"));
	         ret.setMtg_postdate(rs.getString("mtg_postdate"));
	         ret.setMtg_status(rs.getString("mtg_status"));
	         ret.setMtg_subject(rs.getString("mtg_subject"));
	         ret.setMtg_text(rs.getString("mtg_text"));
	         ret.setPrf_id(rs.getLong("prf_id"));
	       }
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

    public Mailtogo getMailtogo(ResultSet rs) throws SQLException
    {
    	Mailtogo ret = new Mailtogo();
	   	ret.setMtg_id(rs.getLong("mtg_id"));
	    ret.setMtg_address(rs.getString("mtg_address"));
        ret.setMtg_from(rs.getString("mtg_from"));
        ret.setMtg_replyto(rs.getString("mtg_replyto"));
	    ret.setMtg_postdate(rs.getString("mtg_postdate"));
	    ret.setMtg_status(rs.getString("mtg_status"));
	    ret.setMtg_subject(rs.getString("mtg_subject"));
	    ret.setMtg_text(rs.getString("mtg_text"));
	    ret.setPrf_id(rs.getLong("prf_id"));
	    return ret;
    }

    public boolean insert(Mailtogo mailtogo)
    {
    	String sql = "INSERT INTO site_mailtogo (mtg_address,mtg_from,mtg_replyto,mtg_status,mtg_subject," +
    			"mtg_text,prf_id,mtg_postdate) VALUES " +
    			"(?,?,?,?,?,?,?,SYSDATE())";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, mailtogo.getMtg_address());
	       pstmt.setString(2, mailtogo.getMtg_from());
	       pstmt.setString(3, mailtogo.getMtg_replyto());
	       pstmt.setString(4, mailtogo.getMtg_status());
	       pstmt.setString(5, mailtogo.getMtg_subject());
	       pstmt.setString(6, mailtogo.getMtg_text());
	       pstmt.setLong(7, mailtogo.getPrf_id());
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

    public boolean save(Mailtogo mailtogo)
    {
    	String sql = "UPDATE site_mailtogo SET mtg_status = ? WHERE mtg_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, mailtogo.getMtg_status());
	       pstmt.setLong(2, mailtogo.getMtg_id());
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

    public List<Mailtogo> getQueue()
    {
        List<Mailtogo> ret = new ArrayList<Mailtogo>();
    	String sql = "SELECT * FROM site_mailtogo WHERE mtg_status = 'Q' LIMIT 100";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next()) ret.add(this.getMailtogo(rs));
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
