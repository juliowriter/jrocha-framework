//Generated by ThateJwizard

package site.admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;


/**
* Creates a Profile object
*/
public class Profile extends ConnectionPool {

  /**
	 *
	 */
  private long prf_id;
  private String prf_nome;
  private String prf_categoria;
  private String prf_tipo;

  /**
  * Empty constructor
  */
  public Profile(){}

  /**
  * Constructor by Primary Key
  */
  public Profile(long prf_id) {
    String sql = "SELECT * FROM res_profiles WHERE prf_id = ?";

    Connection con = null;

    try{
       con = getConnection();
       PreparedStatement pstmt = con.prepareStatement(sql);
       pstmt.setLong(1, prf_id);
       ResultSet rs = pstmt.executeQuery();
       if(rs.next()){
         this.prf_id = rs.getLong("prf_id");
         this.prf_nome = rs.getString("prf_nome");
         this.prf_categoria = rs.getString("prf_categoria");
         this.prf_tipo = rs.getString("prf_tipo");
       }
       cleanUp(pstmt, rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection(con);
    }
  }

  /**
  * Constructor by ResultSet
  */
  public Profile(ResultSet rs) {
    try{
       this.prf_id = rs.getLong("prf_id");
         this.prf_nome = rs.getString("prf_nome");
         this.prf_categoria = rs.getString("prf_categoria");
         this.prf_tipo = rs.getString("prf_tipo");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
  * @return Total number of records in res_profiles
  */
  public long getCount(){
    long ret = 0;
    String sql = "SELECT COUNT(*) AS TOTAL FROM res_profiles";
    Connection con = null;
    try{
       con = getConnection();
       PreparedStatement pstmt = con.prepareStatement(sql);
       ResultSet rs = pstmt.executeQuery();
       rs.next();
       ret = rs.getLong("TOTAL");
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

  /**
  * @param The column to order by
  * @return A list of Perfil objects
  */
  public List<Profile> getList(String arg0){
    List<Profile> coll = new ArrayList<Profile>();
    String sql = "SELECT * FROM res_profiles ORDER BY ?";
    Connection con = null;
    try{
       con = getConnection();
       PreparedStatement pstmt = con.prepareStatement(sql);
       pstmt.setString(1, arg0);
       ResultSet rs = pstmt.executeQuery();
       while(rs.next()) coll.add( new Profile(rs));
       cleanUp(pstmt, rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection(con);
    }
    return coll;
  }

  /**
  * @param Start row
  * @param Total rows
  * @param The column to order by
  * @return A list of Perfil objects, starting in Start row and with Total rows
  */
  public List<Profile> getList(long arg0, long arg1, String arg2){
    List<Profile> coll = new ArrayList<Profile>();
    String sql = "SELECT * FROM res_profiles ORDER BY ? LIMIT ?,?";
    Connection con = null;
    try{
       con = getConnection();
       PreparedStatement pstmt = con.prepareStatement(sql);
       pstmt.setString(1, arg2);
       pstmt.setLong(2, arg0);
       pstmt.setLong(3, arg1);
       ResultSet rs = pstmt.executeQuery();
       while(rs.next()) coll.add( new Profile(rs));
       cleanUp(pstmt, rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection(con);
    }
    return coll;
  }

  /**
  * @param Start row
  * @param Total rows
  * @param The column to order by
  * @param ASC for ascendant, DESC for descendant
  * @return A list of Perfil objects, starting in Start row and with Total rows
  */
  public List<Profile> getList(long arg0, long arg1, String arg2, String arg3){
    List<Profile> coll = new ArrayList<Profile>();
    String sql = "SELECT * FROM res_profiles ORDER BY ? " + arg3 + " LIMIT ?,?";
    Connection con = null;
    try{
       con = getConnection();
       PreparedStatement pstmt = con.prepareStatement(sql);
       pstmt.setString(1, arg2);
       pstmt.setLong(2, arg0);
       pstmt.setLong(3, arg1);
       ResultSet rs = pstmt.executeQuery();
       while(rs.next()) coll.add( new Profile(rs));
       cleanUp(pstmt, rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection(con);
    }
    return coll;
  }

  /**
  * @param Start row
  * @param Total rows
  * @param The column to order by
  * @param ASC for ascendant, DESC for descendant
  * @param A filter for the list
  * @return A list of Perfil objects, starting in Start row and with Total rows
  */
  public List<Profile> getList(long arg0, long arg1, String arg2, String arg3, String arg4){
    List<Profile> coll = new ArrayList<Profile>();
    String sql = "SELECT * FROM res_profiles WHERE " + arg4 + " ORDER BY ? " + arg3 + " LIMIT ?,?";
    Connection con = null;
    try{
       con = getConnection();
       PreparedStatement pstmt = con.prepareStatement(sql);
       pstmt.setString(1, arg2);
       pstmt.setLong(2, arg0);
       pstmt.setLong(3, arg1);
       ResultSet rs = pstmt.executeQuery();
       while(rs.next()) coll.add( new Profile(rs));
       cleanUp(pstmt, rs);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeConnection(con);
    }
    return coll;
  }

  /**
  * @return true if success, false if not
  */
  public boolean save(){
     boolean ret = false;
     String sql = "";
     Connection con = null;
     PreparedStatement pstmt = null;
     try{
        con = getConnection();
        if(this.prf_id <= 0)
        {
		    sql = "INSERT INTO res_profiles ( prf_nome,prf_categoria,prf_tipo )";
		    sql += "VALUES (?,?,?)";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1,this.getPrf_nome());
	        pstmt.setString(2,this.getPrf_categoria());
	        pstmt.setString(3,this.getPrf_tipo());
	        cleanUp(pstmt);
        } else {
            sql = "UPDATE res_profiles SET ";
            sql += "prf_nome = ?,prf_categoria = ?,prf_tipo = ?";
            sql += " WHERE prf_id = ?";
            pstmt = con.prepareStatement(sql);
	        pstmt.setString(1,this.getPrf_nome());
	        pstmt.setString(2,this.getPrf_categoria());
	        pstmt.setString(3,this.getPrf_tipo());
	        pstmt.setLong(4, this.prf_id);
	        cleanUp(pstmt);
        }
        int rows = pstmt.executeUpdate();
      	ret = (rows > 0);
        if(this.prf_id <= 0)
        {
             sql = "SELECT prf_id FROM res_profiles ORDER BY prf_id DESC LIMIT 1";
             pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             rs.next();
             this.prf_id = rs.getLong("prf_id");
 	         cleanUp(pstmt, rs);
        }
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

  /**
  * @param The Primary Key
  * @return true if success, false if not
  */
  public boolean delete(){
     boolean ret = false;
     String sql = "DELETE FROM res_profiles WHERE prf_id = ?";
     Connection con = null;
     try{
        con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setLong(1, this.getPrf_id());
        int rows = pstmt.executeUpdate();
      	ret = (rows > 0);
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

	public List<Profile> getPerfil(String strOrdem){
		List<Profile> coll = new ArrayList<Profile>();
		String sql = "SELECT * FROM res_profiles WHERE prf_tipo = '0' ORDER BY " + strOrdem;
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) coll.add( new Profile(rs));
			cleanUp(pstmt, rs);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con);
		}
		return coll;
	}

	public List<Profile> getPerfilPermissoes(long tar_id, String strOrdem){
		List<Profile> coll = new ArrayList<Profile>();
		String sql = "SELECT res_profiles.* FROM res_profiles LEFT JOIN res_profilexmenu ON res_profilexmenu.prf_id = res_profiles.prf_id WHERE res_profiles.prf_tipo = '0' AND res_profilexmenu.tar_id = ? ORDER BY " + strOrdem;
		Connection con = null;
		try{
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, tar_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) coll.add( new Profile(rs));
			cleanUp(pstmt, rs);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con);
		}
		return coll;
	}

	public List<Profile> getPerfilPermissoesNao(long tar_id, String strOrdem){
		List<Profile> coll = new ArrayList<Profile>();
		String sql = "SELECT res_profilexmenu.prf_id FROM res_profilexmenu WHERE res_profilexmenu.tar_id = ?";
		Connection con = null;
		String ids = "";
		try{
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, tar_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) ids += rs.getString("prf_id") + ",";
			ids = (!ids.equals("")?ids.substring(0,ids.length()-1):"0");
			sql = "SELECT res_profiles.* FROM res_profiles WHERE res_profiles.prf_id NOT IN (" + ids + ") ORDER BY " + strOrdem;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) coll.add( new Profile(rs));
			cleanUp(pstmt, rs);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con);
		}
		return coll;
	}

	public List<Profile> getPerfilUsuarios(long usu_id, String strOrdem){
		List<Profile> coll = new ArrayList<Profile>();
		String sql = "SELECT res_profiles.* FROM res_profiles LEFT JOIN res_userxprofile ON res_userxprofile.prf_id = res_profiles.prf_id WHERE res_profiles.prf_tipo = '0' AND res_userxprofile.usu_id = ? ORDER BY " + strOrdem;
		Connection con = null;
		try{
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, usu_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) coll.add( new Profile(rs));
			cleanUp(pstmt, rs);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con);
		}
		return coll;
	}

	public List<Profile> getPerfilUsuariosNao(long usu_id, String strOrdem){
		List<Profile> coll = new ArrayList<Profile>();
		String sql = "SELECT res_userxprofile.prf_id FROM res_userxprofile WHERE res_userxprofile.usu_id = ?";
		Connection con = null;
		String ids = "";
		try{
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, usu_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) ids += rs.getString("prf_id") + ",";
			ids = (!ids.equals("")?ids.substring(0,ids.length()-1):"0");
			sql = "SELECT res_profiles.* FROM res_profiles WHERE res_profiles.prf_id NOT IN (" + ids + ") ORDER BY " + strOrdem;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) coll.add( new Profile(rs));
			cleanUp(pstmt, rs);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con);
		}
		return coll;
	}

	public void setPermissoes(String[] funcionalidades)
	{
		this.removePermissoes(this.prf_id);
		for(int xx = 0; xx < funcionalidades.length; xx++)
		{
			this.permissaoTarefa(Long.parseLong(funcionalidades[xx]));
		}
	}

	public void permissaoTarefa(long tarefaId)
	{
		String sql = "INSERT INTO res_profilexmenu (";
		sql += "tar_id, ";
		sql += "prf_id, ";
		sql += "pxt_status ";
		sql += ") VALUES (";
		sql += tarefaId + ", ";
		sql += this.prf_id + ", ";
		sql += "'0')";
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			pstmt.executeUpdate(sql);
			cleanUp(pstmt);
		}
		catch (SQLException e){}
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con); }
	}

	public void removePermissoes(long perfilId)
	{
		String sql = "DELETE FROM res_profilexmenu WHERE prf_id = " + perfilId;
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			pstmt.executeUpdate(sql);
			cleanUp(pstmt);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con); }
	}

	public boolean getAcesso(long tarefaId)
	{
		boolean ret = false;
		String sql = "SELECT prf_id FROM res_profilexmenu WHERE prf_id = " + this.prf_id + " AND tar_id = " + tarefaId + " LIMIT 1";
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			ret = rs.next();
			cleanUp(pstmt, rs);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con);
		}
		return ret;
	}

  public long getPrimaryKey(){
     return this.prf_id;
  }

  public void setPrimaryKey(long arg0){
     this.prf_id = arg0;
  }

  public long getPrf_id(){
   return this.prf_id; }

  public String getPrf_nome(){
   return this.prf_nome; }

  public String getPrf_categoria(){
   return this.prf_categoria; }

  public String getPrf_tipo(){
   return this.prf_tipo; }

  public void setPrf_id(long arg0){
   this.prf_id = arg0; }

  public void setPrf_nome(String arg0){
   this.prf_nome = arg0; }

  public void setPrf_categoria(String arg0){
   this.prf_categoria = arg0; }

  public void setPrf_tipo(String arg0){
   this.prf_tipo = arg0; }

}

