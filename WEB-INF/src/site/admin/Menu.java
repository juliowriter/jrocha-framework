package site.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;

public class Menu extends ConnectionPool {

	private long tar_id;
	private String tar_descricao;
	private String tar_imagem;
	private String tar_texto;
	private String tar_rotina;
	private String tar_tipo;
	private long tar_pai;
	private long tar_ordem;

	public Menu(){ }

	public Menu(long id){
		String sql = "SELECT * FROM res_menu WHERE tar_id = " + id;
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			if(rs.next()){
				this.tar_id = rs.getLong("tar_id");
				this.tar_descricao = (rs.getString("tar_descricao")!=null?rs.getString("tar_descricao"):"");
				this.tar_imagem = (rs.getString("tar_imagem")!=null?rs.getString("tar_imagem"):"");
				this.tar_texto = (rs.getString("tar_texto")!=null?rs.getString("tar_texto"):"");
				this.tar_rotina = (rs.getString("tar_rotina")!=null?rs.getString("tar_rotina"):"");
				this.tar_tipo = (rs.getString("tar_tipo")!=null?rs.getString("tar_tipo"):"");
				this.tar_pai = rs.getLong("tar_pai");
				this.tar_ordem = rs.getLong("tar_ordem");
			}
			cleanUp(pstmt, rs);
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
		finally {
			closeConnection(con); }
	}

	public Menu(ResultSet rs){
		try{
			this.tar_id = rs.getLong("tar_id");
			this.tar_descricao = (rs.getString("tar_descricao")!=null?rs.getString("tar_descricao"):"");
			this.tar_imagem = (rs.getString("tar_imagem")!=null?rs.getString("tar_imagem"):"");
			this.tar_texto = (rs.getString("tar_texto")!=null?rs.getString("tar_texto"):"");
			this.tar_rotina = (rs.getString("tar_rotina")!=null?rs.getString("tar_rotina"):"");
			this.tar_tipo = (rs.getString("tar_tipo")!=null?rs.getString("tar_tipo"):"");
			this.tar_pai = rs.getLong("tar_pai");
			this.tar_ordem = rs.getLong("tar_ordem");
		}
		catch (SQLException e){
			e.printStackTrace(); }
		catch (Exception e){
			e.printStackTrace(); }
	}

	public List<Menu> getFuncionalidade(String strOrdem){
		List<Menu> coll = new ArrayList<Menu>();
		String sql = "SELECT * FROM res_menu WHERE tar_id <> 14 ORDER BY " + strOrdem;
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) coll.add( new Menu(rs));
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

	public long getTotalFuncionalidades(){
		long ret = 0;
		String sql = "SELECT COUNT(*) AS TOTAL FROM res_menu";
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			rs.next();
			ret = rs.getLong("TOTAL");
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

	public List<Menu> getFuncionalidade(long lngInicio, long lngMaximo, String strOrdem){
		List<Menu> coll = new ArrayList<Menu>();
		String sql = "SELECT * FROM res_menu WHERE tar_id <> 14 ORDER BY " + strOrdem + " LIMIT " + lngInicio + "," + lngMaximo;
		Connection con = null;
		try{
			con = getConnection();
			Statement pstmt = con.createStatement();
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) coll.add( new Menu(rs));
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

 	public List<Menu> getFuncionalidadePerfil(String profile){
		ArrayList<Menu> coll = new ArrayList<Menu>();
		String perfis = "0";
		if(profile!=null)
		{
			perfis += "," + profile;
		}
		String  sql = "SELECT DISTINCT res_menu.* FROM res_menu LEFT JOIN res_profilexmenu " +
					  "ON res_profilexmenu.tar_id = res_menu.tar_id " +
					  "WHERE res_profilexmenu.prf_id IN (" + perfis + ") AND " +
			    	  "res_menu.tar_pai = 0 AND " +
					  "res_profilexmenu.pxt_status <> 'X' ORDER BY tar_ordem";
		ResultSet rs = null;
		Connection con = null;
		Statement pstmt = null;
		try{
			con = getConnection();
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql);
			while(rs.next()) coll.add(new Menu(rs));
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

	public List<Menu> getFuncionalidadePai(){
		ArrayList<Menu> coll = new ArrayList<Menu>();
		String  sql = "SELECT * FROM res_menu " +
					  "WHERE tar_pai = 0 ORDER BY tar_ordem";
		ResultSet rs = null;
		Connection con = null;
		Statement pstmt = null;
		try{
			con = getConnection();
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql);
			while(rs.next()) coll.add(new Menu(rs));
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

	public List<Menu> getFuncionalidade(long id, String profile){
		String perfis = "0";
		if(profile!=null)
		{
			perfis += "," + profile;
		}
		ArrayList<Menu> coll = new ArrayList<Menu>();
		String  sql = "SELECT res_menu.* FROM res_menu LEFT JOIN res_profilexmenu " +
					  "ON res_profilexmenu.tar_id = res_menu.tar_id " +
					  "WHERE res_profilexmenu.prf_id IN (" + perfis + ") AND " +
					  "res_menu.tar_pai = " + id + " AND " +
					  "res_profilexmenu.pxt_status <> 'X' ORDER BY tar_ordem";
		ResultSet rs = null;
		Connection con = null;
		Statement pstmt = null;
		try{
			con = getConnection();
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql);
			while(rs.next()) coll.add(new Menu(rs));
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

	public boolean temFilhas(long id){
		String  sql = "SELECT tar_id FROM res_menu WHERE tar_pai = " + id + " LIMIT 1";
		ResultSet rs = null;
		Connection con = null;
		Statement pstmt = null;
		boolean ret=false;
		try{
			con = getConnection();
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql);
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

	public void permissaoTarefa(long perfilId)
	{
		String sql = "INSERT INTO res_profilexmenu (";
		sql += "tar_id, ";
		sql += "prf_id, ";
		sql += "pxt_status ";
		sql += ") VALUES (";
		sql += this.tar_id + ", ";
		sql += perfilId + ", ";
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

	public void setPermissoes(String[] perfis)
	{
		this.removePermissoes(this.tar_id);
		if(perfis!=null)
		{
			for(int xx = 0; xx < perfis.length; xx++)
			{
				this.permissaoTarefa(Long.parseLong(perfis[xx]));
			}
		}
	}

	public void removePermissoes(long tarefaId)
	{
		String sql = "DELETE FROM res_profilexmenu WHERE tar_id = " + tarefaId;
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


	public List<Menu> getFuncionalidadePermissoes(long prf_id, String strOrdem){
		List<Menu> coll = new ArrayList<Menu>();
		String sql = "SELECT res_menu.* FROM res_menu LEFT JOIN res_profilexmenu ON res_profilexmenu.tar_id = res_menu.tar_id WHERE res_profilexmenu.prf_id = ? ORDER BY " + strOrdem;
		Connection con = null;
		try{
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, prf_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) coll.add( new Menu(rs));
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

	public List<Menu> getFuncionalidadePermissoesNao(long prf_id, String strOrdem){
		List<Menu> coll = new ArrayList<Menu>();
		String sql = "SELECT res_profilexmenu.tar_id FROM res_profilexmenu WHERE res_profilexmenu.prf_id = ?";
		Connection con = null;
		String ids = "";
		try{
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, prf_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) ids += rs.getString("tar_id") + ",";
			ids = (!ids.equals("")?ids.substring(0,ids.length()-1):"0");
			sql = "SELECT res_menu.* FROM res_menu WHERE res_menu.tar_id NOT IN (" + ids + ") ORDER BY " + strOrdem;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) coll.add( new Menu(rs));
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
	        if(this.tar_id <= 0)
		{
		    sql = "INSERT INTO res_menu ( tar_descricao,tar_texto,tar_rotina,tar_tipo,tar_ordem,tar_pai )";
		    sql += "VALUES (?,?,?,?,?,?)";
	            pstmt = con.prepareStatement(sql);

	        pstmt.setString(1,this.getTar_descricao());
	        pstmt.setString(2,this.getTar_texto());
	        pstmt.setString(3,this.getTar_rotina());
	        pstmt.setString(4,this.getTar_tipo());
	        pstmt.setLong(5, this.getTar_ordem());
	        pstmt.setLong(6,this.getTar_pai());


		} else {
	            sql = "UPDATE res_menu SET ";
	            sql += "tar_descricao = ?,tar_texto = ?,tar_rotina = ?,tar_tipo = ?,tar_ordem = ?,tar_pai = ?";
	            sql += " WHERE tar_id = ?";
	            pstmt = con.prepareStatement(sql);

	        pstmt.setString(1,this.getTar_descricao());
	        pstmt.setString(2,this.getTar_texto());
	        pstmt.setString(3,this.getTar_rotina());
	        pstmt.setString(4,this.getTar_tipo());
	        pstmt.setLong(5, this.getTar_ordem());
	        pstmt.setLong(6,this.getTar_pai());
	        pstmt.setLong(7, this.tar_id);
		}
	        int rows = pstmt.executeUpdate();
	      	ret = (rows > 0);
	        if(this.tar_id <= 0)
		{
	             sql = "SELECT tar_id FROM res_menu ORDER BY tar_id DESC LIMIT 1";
	             ResultSet rs = pstmt.executeQuery(sql);
	             rs.next();
	             this.tar_id = rs.getLong("tar_id");
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
	     String sql = "DELETE FROM res_menu WHERE tar_id = ?";
	     Connection con = null;
	     try{
	        con = getConnection();
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setLong(1, this.getTar_id());
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

	public boolean getAcesso(long perfilId)
	{
		boolean ret = false;
		String sql = "SELECT prf_id FROM res_profilexmenu WHERE prf_id = " + perfilId + " AND tar_id = " + this.tar_id + " LIMIT 1";
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

	public long getTar_id() {
		return this.tar_id; }

	public String getTar_descricao() {
		return this.tar_descricao; }

	public String getTar_texto() {
		return this.tar_texto; }

	public String getTar_rotina() {
		return this.tar_rotina; }

	public String getTar_tipo() {
		return this.tar_tipo; }

	public long getTar_pai() {
		return this.tar_pai; }

	public long getTar_ordem() {
		return this.tar_ordem; }

	public void setTar_id(long var) {
		tar_id = var; }

	public void setTar_descricao(String var) {
		tar_descricao = var; }

	public void setTar_texto(String var) {
		tar_texto = var; }

	public void setTar_rotina(String var) {
		tar_rotina = var; }

	public void setTar_tipo(String var) {
		tar_tipo = var; }

	public void setTar_pai(long var) {
		tar_pai = var; }

	public void setTar_ordem(long var) {
		tar_ordem = var; }

	public String getTar_imagem() {
		return tar_imagem;
	}

	public void setTar_imagem(String string) {
		tar_imagem = string;
	}
}