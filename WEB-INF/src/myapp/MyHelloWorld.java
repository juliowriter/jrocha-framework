package myapp;

import java.util.Collection;
import java.util.Iterator;

import myapp.dao.MyHelloWorldDAO;

public class MyHelloWorld {

	private long id = 0;
	private String welcome_text;
	
	public MyHelloWorld getWelcomeText(long id)
	{
		return new MyHelloWorldDAO().getWelcomeText(id);
	}
	
    public String getMyTextList()
    {
		String ret = "<table class='table table-striped'><tbody>";
        try {
			Iterator<MyHelloWorld> it = new MyHelloWorldDAO().getTextList().iterator();
			while(it.hasNext()){
				MyHelloWorld obj = it.next();
			    ret += "<tr><td><div class=\"row\"><div class=\"col-md-8\">" +
			           "<a href=\"index?pag=MYTEXT&par=id:" + obj.getId() + "\">" + (obj.getWelcome_text()!=null && !obj.getWelcome_text().equals("")?obj.getWelcome_text():"") + "</div>" +
			           "<div class=\"col-md-2\"><a href=\"index?pag=MYTEXT&par=id:" + obj.getId() + "\" style=\"min-width:150px;\" class=\"btn btn-warning btn-dashboard\"><i class='glyphicon glyphicon-edit'></i> _TB_edit_TE_</a><br /><br /></div>" +
		               "<div class=\"col-md-2\"><a href=\"javascript: deleteItem(" + obj.getId() + ",'" + (obj.getWelcome_text()!=null && !obj.getWelcome_text().equals("")?obj.getWelcome_text():"") + "');\" style=\"min-width:150px;\" class=\"btn btn-danger btn-dashboard\"><i class='glyphicon glyphicon-remove'></i> _TB_delete_TE_</a><br /><br /></div>" +
		               "<br /><br /></div></td></tr>";
			}
			ret += "<tr><td><a href='index?pag=MYTEXT' class='btn btn-warning'><i class=\"glyphicon glyphicon-plus\"></i><span class=\"button-swap\"> _TB_New Welcome Text_TE_</span></a></div></div></td></tr>";
			ret += "</tbody></table>";
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
		return ret;
    }
    
	public Collection<MyHelloWorld> getTextList()
	{
		return new MyHelloWorldDAO().getTextList();
	}

	public void insert()
	{
		new MyHelloWorldDAO().insert(this);
	}
	
	public void save()
	{
		new MyHelloWorldDAO().save(this);
	}

	public void delete()
	{
		new MyHelloWorldDAO().delete(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWelcome_text() {
		return welcome_text;
	}

	public void setWelcome_text(String welcome_text) {
		this.welcome_text = welcome_text;
	}
	
}
