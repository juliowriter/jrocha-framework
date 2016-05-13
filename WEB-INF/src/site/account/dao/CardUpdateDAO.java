package site.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import site.core.ConnectionPool;
import site.core.Encription;
import site.account.CardUpdate;


public class CardUpdateDAO extends ConnectionPool {

	public CardUpdate getCardUpdate(long id)
    {
		CardUpdate ret = null;
    	String sql = "SELECT * FROM site_card_update WHERE crd_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getCardUpdate(rs);
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

	public CardUpdate getCardUpdate(ResultSet rs) throws SQLException
    {
         CardUpdate ret = new CardUpdate();
    	 ret.setCrd_id(rs.getLong("crd_id"));
         ret.setCrd_token(rs.getString("crd_token"));
         ret.setCrd_name(rs.getString("crd_name"));
         ret.setCrd_number(rs.getString("crd_number"));
         ret.setCrd_expiration(rs.getString("crd_expiration"));
         ret.setCrd_cvv(rs.getString("crd_cvv"));
	     return ret;
    }

    public boolean insert(CardUpdate item)
    {
    	String sql = "INSERT INTO site_card_update (crd_token,crd_name,crd_number,crd_expiration,crd_cvv) VALUES (?,?,?,?,?)";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getCrd_token());
	       pstmt.setString(2, item.getCrd_name());
	       pstmt.setString(3, new Encription().encode(item.getCrd_number()));
	       pstmt.setString(4, item.getCrd_expiration());
	       pstmt.setString(5, item.getCrd_cvv());
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
