package site.core;

import java.util.List;

import site.core.dao.EditoriaDAO;

public class Editoria {

	private long edt_id;
	private String edt_titulo;
	private String edt_tag;
	private int edt_status;

	public Editoria getEditoria(long id)
	{
		return (new EditoriaDAO().getEditoria(id));
	}

	public Editoria getEditoria(String tag)
	{
		return (new EditoriaDAO().getEditoria(tag));
	}

	public List<Editoria> getList()
	{
		return new EditoriaDAO().getList();
	}

	public List<Editoria> getList(boolean withSession)
	{
		return new EditoriaDAO().getList(withSession);
	}

	public List<Editoria> getList(String order)
	{
		return new EditoriaDAO().getList(order);
	}

	public long insert()
	{
		return new EditoriaDAO().insert(this);
	}

	public boolean save()
	{
		return new EditoriaDAO().save(this);
	}

	public boolean delete()
	{
		return new EditoriaDAO().delete(this);
	}

	public long getEdt_id() {
		return edt_id;
	}

	public void setEdt_id(long edt_id) {
		this.edt_id = edt_id;
	}

	public int getEdt_status() {
		return edt_status;
	}

	public void setEdt_status(int edt_status) {
		this.edt_status = edt_status;
	}

	public String getEdt_titulo() {
		return edt_titulo;
	}

	public void setEdt_titulo(String edt_titulo) {
		this.edt_titulo = edt_titulo;
	}

	public String getEdt_tag() {
		return edt_tag;
	}

	public void setEdt_tag(String edt_tag) {
		this.edt_tag = edt_tag;
	}

}
