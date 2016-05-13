package site.core;

import java.util.List;

import site.core.dao.ChatDAO;

public class Chat {

	private long cht_id;
	private String cht_email;
	private String cht_name;
	private String cht_mensagem;

	public Chat getChat(long id)
	{
		return (new ChatDAO().getChat(id));
	}

	public Chat getChat(String tag)
	{
		return (new ChatDAO().getChat(tag));
	}

	public List<Chat> getList()
	{
		return new ChatDAO().getList();
	}

	public List<Chat> getList(boolean withSession)
	{
		return new ChatDAO().getList(withSession);
	}

	public List<Chat> getList(String order)
	{
		return new ChatDAO().getList(order);
	}

	public boolean insert()
	{
		return new ChatDAO().insert(this);
	}

	public boolean save()
	{
		return new ChatDAO().save(this);
	}

	public boolean delete()
	{
		return new ChatDAO().delete(this);
	}

	public long getCht_id() {
		return cht_id;
	}

	public void setCht_id(long cht_id) {
		this.cht_id = cht_id;
	}

	public String getCht_mensagem() {
		return cht_mensagem;
	}

	public void setCht_mensagem(String cht_mensagem) {
		this.cht_mensagem = cht_mensagem;
	}

	public String getCht_email() {
		return cht_email;
	}

	public void setCht_email(String cht_email) {
		this.cht_email = cht_email;
	}

	public String getCht_name() {
		return cht_name;
	}

	public void setCht_name(String cht_name) {
		this.cht_name = cht_name;
	}

}
