package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Language;

public class LanguageDAO extends ConnectionPool {

        private String lang = "BR";

		public Language getLanguage(ResultSet rs) throws SQLException
	    {
			Language ret = new Language();
	        ret.setLng_id(rs.getLong("lng_id"));
	        ret.setLng_from(rs.getString("lng_from"));
	        ret.setLng_to(rs.getString("lng_to"));
	        ret.setLng_tag(rs.getString("lng_tag"));
	        ret.setLng_language(lang);
		    return ret;
	    }

		public Language getLanguage(long id, String language)
	    {
	    	String sql = "SELECT * FROM site_language_" + language.toLowerCase() + " WHERE lng_id = ? LIMIT 1";
		    Connection con = null;
			Language ret = new Language();
			lang = language;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getLanguage(rs);
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

		public Language getLanguage(String from, String language)
	    {
	    	String sql = "SELECT * FROM site_language_" + language.toLowerCase() + " WHERE lng_from = ? LIMIT 1";
		    Connection con = null;
			Language ret = null;
			lang = language;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, from.trim().replaceAll("\n","").replaceAll("\r", ""));
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getLanguage(rs);
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

		public Language getLanguage(String from, String language, String tag)
	    {
	    	String sql = "SELECT * FROM site_language_" + language.toLowerCase() + " WHERE lng_from = ? AND lng_tag = ? LIMIT 1";
		    Connection con = null;
			Language ret = null;
			lang = language;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, from.trim().replaceAll("\n","").replaceAll("\r", ""));
		       pstmt.setString(2, tag);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getLanguage(rs);
		       else
		    	   ret = this.getLanguage(from.trim(), language);
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

		public String[] getTags(String language)
		{
			String[] ret = null;
	    	String sql = "SELECT DISTINCT lng_tag FROM site_language_" + language.toLowerCase() + " ORDER BY lng_tag";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       String tags = "";
		       while(rs.next())
		         tags += rs.getString("lng_tag") + ",";
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

		public List<Language> getList(String filtro, String order, String language)
		{
			List<Language> ret = new ArrayList<Language>();
	    	String sql = "SELECT * FROM site_language_" + language.toLowerCase();
	    	if(filtro!=null && !filtro.equals(""))
	    		sql += " WHERE lng_tag = '" + filtro + "' ";
	    	sql += " ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new LanguageDAO().getLanguage(rs));
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

	    public long insert(Language item, String language)
	    {
	    	String sql = "INSERT INTO site_language_" + language.toLowerCase() + "(lng_from,lng_to) VALUES " +
	    			"(?,?)";
		    Connection con = null;
		    long ret = 0;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getLng_from());
		       pstmt.setString(2, item.getLng_to());
		       pstmt.executeUpdate();
		       cleanUp(pstmt);
		       PreparedStatement pstmtS = con.prepareStatement("SELECT lng_id FROM site_language_" + language.toLowerCase() + " ORDER BY lng_id DESC LIMIT 1");
		       ResultSet rs = pstmtS.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("lng_id");
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

	    public boolean save(Language item, String language)
	    {
	    	String sql = "UPDATE site_language_" + language.toLowerCase() + " SET lng_from = ?, lng_to = ? WHERE lng_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getLng_from());
		       pstmt.setString(2, item.getLng_to());
		       pstmt.setLong(3, item.getLng_id());
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

	    public boolean delete(Language item, String language)
	    {
	    	String sql = "DELETE FROM site_language_" + language.toLowerCase() + " WHERE lng_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getLng_id());
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
}
