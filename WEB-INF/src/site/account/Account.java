package site.account;

import java.util.Iterator;
import java.util.List;

import site.core.Util;
import site.account.dao.AccountDAO;

public class Account {

	private long prf_id = 0;
	private String prf_email = null;
	private String prf_zipcode = null;
	private String prf_phone = null;
	private String prf_address = null;
	private String prf_number = null;
	private String prf_complement = null;
	private String prf_neighborhood = null;
	private String prf_city = null;
	private String prf_state = null;
	private String prf_token = null;
	private String prf_datetime = null;
	private String prf_firstname = null;
	private String prf_lastname = null;
	private String prf_password = null;
	private String prf_status = null;
	private String prf_birth_date = null;
	private String prf_gender = null;
	private String prf_nickname = null;
	private long cou_id = 0; //used to determine the parent account
	private String prf_language = null;
	private String prf_ip = null;
	private String prf_country = null;
	private String prf_company = null;
	private String prf_website = null;
	private int prf_level = 0; //0 - general user / 1 - agency
	private long prf_credit = 0;
	private String prf_billingday = null;
	private int prf_wizard = 0; //0 - wizard off / 1 - wizard on / 2 - first access and wizard on

	public Account getAccount(long id)
    {
		return (new AccountDAO().getAccount(id));
    }

	public Account getAccount(String email)
    {
		return (new AccountDAO().getAccount(email));
    }

	public Account getAccountToken(String token)
    {
		return (new AccountDAO().getAccountToken(token));
    }

	public List<Account> getAccounts()
    {
		return (new AccountDAO().getAccounts());
    }

	public List<Account> getList(String filtro, String order, String plan)
	{
		return new AccountDAO().getList(filtro, order, plan);
	}

	public List<Account> getAccounts(long parentid)
    {
		return (new AccountDAO().getAccounts(parentid));
    }

	public String getLinks(String lng)
    {
		String ret = "<table class='table-striped'><tbody>";
		Iterator<Account> it = new AccountDAO().getAccounts(this.getPrf_id()).iterator();
		boolean exists = false;
		int count = 0;
		while(it.hasNext()){
		    Account act = it.next();
		    ret += "<tr><td>" +
		           "<a href=\"index?pag=DASHBOARD&par=cou:" + act.getPrf_id() + "\">" + act.getPrf_company() + "</td>" +
		           "<td><a href=\"index?pag=DASHBOARD&par=cou:" + act.getPrf_id() + "\" class=\"btn btn-info btn-lg btn-dashboard\"><i class='glyphicon glyphicon-log-in'></i><span class=\"button-swap\">  _TB_go to this account_TE_</span></a>"
		           		+ " <a href='javascript: excluiLink(" + act.getPrf_id() + ",\"" + act.getPrf_company() + "\")' class=\"btn btn-default btn-lg btn-dashboard\"><i class='glyphicon glyphicon-remove'></i><span class=\"button-swap\">  _TB_remove this link_TE_</span></a></td></tr>";
            exists = true;
            count++;
		}
		if(!exists)
			ret += "<tr><td>_TB_You don't have any link yet._TE_</td><td><a href='index?pag=LINKACCOUNT' class='btn btn-warning'><i class=\"glyphicon glyphicon-plus\"></i><span class=\"button-swap\">  _TB_Create New Link_TE_</span></a></td></tr>";
		ret += "</tbody></table><input type='hidden' id='nf' value='" + count + "'>";
		return ret;
    }

	public boolean insert()
	{
		return new AccountDAO().insert(this);
	}

	public boolean hasOrderToday()
	{
		return new AccountDAO().hasOrderToday(this.getPrf_id());
	}

	public boolean find(String email)
	{
		return new AccountDAO().find(email);
	}

	public boolean linkAccount(long id)
	{
		return new AccountDAO().linkAccount(this, id);
	}

	public Account login(String email, String senha)
	{
		return new AccountDAO().login(email, senha);
	}

	public String resetPasswordToken(String email)
	{
		return new AccountDAO().resetPasswordToken(email);
	}
	
	public String validateToken(String token)
	{
		return new AccountDAO().validateToken(token);
	}
	
	public void removeToken(String token)
	{
		new AccountDAO().removeToken(token);
	}

	public Account login(String token)
	{
		return new AccountDAO().login(token);
	}

	public int getTotal()
	{
		return new AccountDAO().getTotal();
	}

	public int getTotalToday()
	{
		return new AccountDAO().getTotalToday();
	}

	public boolean save()
	{
		return new AccountDAO().save(this);
	}

	public void setBillingDay()
	{
		new AccountDAO().setBillingDay(this);
	}
	
	public int getMyCredits(long prfID)
	{
		return new AccountDAO().getMyCredits(prfID);
	}

	public boolean drawCredits(long prfID, int credits)
	{
		return new AccountDAO().drawCredits(prfID, credits);
	}

	public boolean putCredits(long prfID, int credits)
	{
		return new AccountDAO().putCredits(prfID, credits);
	}

	public boolean hasSubscription()
	{
		return new AccountDAO().hasSubscription(this.getPrf_id());
	}

	public boolean confirm(String stamp)
	{
		return new AccountDAO().confirm(this.getPrf_id(), stamp);
	}

	public boolean unlink()
	{
		return new AccountDAO().unlink(this.getPrf_id());
	}

	public boolean nickExists(String nick)
	{
		return new AccountDAO().nickExists(nick);
	}

	public String newNick(String email)
	{
		String ret = "";
		String nick = email.substring(0, email.indexOf("@"));
		int count = 1;
		while(true)
		{
			if(!this.nickExists(nick))
			{
				ret = nick;
				break;
			}
			nick = email.substring(0, email.indexOf("@"));
			nick += new Util().formatZero(count, 4);
		}
		return ret;
	}

	public String getPrf_datetime() {
		return prf_datetime;
	}

	public void setPrf_datetime(String prf_datetime) {
		this.prf_datetime = prf_datetime;
	}

	public String getPrf_email() {
		return prf_email;
	}

	public void setPrf_email(String prf_email) {
		this.prf_email = prf_email;
	}

	public long getPrf_id() {
		return prf_id;
	}

	public void setPrf_id(long prf_id) {
		this.prf_id = prf_id;
	}

	public String getPrf_token() {
		return prf_token;
	}

	public void setPrf_token(String prf_token) {
		this.prf_token = prf_token;
	}

	public String getPrf_zipcode() {
		return prf_zipcode;
	}

	public void setPrf_zipcode(String prf_zipcode) {
		this.prf_zipcode = prf_zipcode;
	}

	public String getPrf_firstname() {
		return prf_firstname;
	}

	public void setPrf_firstname(String prf_firstname) {
		this.prf_firstname = prf_firstname;
	}

	public String getPrf_lastname() {
		return prf_lastname;
	}

	public void setPrf_lastname(String prf_lastname) {
		this.prf_lastname = prf_lastname;
	}

	public String getPrf_password() {
		return prf_password;
	}

	public void setPrf_password(String prf_password) {
		this.prf_password = prf_password;
	}

	public String getPrf_status() {
		return prf_status;
	}

	public void setPrf_status(String prf_status) {
		this.prf_status = prf_status;
	}

	public String getPrf_birth_date() {
		return prf_birth_date;
	}

	public void setPrf_birth_date(String prf_birth_date) {
		this.prf_birth_date = prf_birth_date;
	}

	public String getPrf_gender() {
		return prf_gender;
	}

	public void setPrf_gender(String prf_gender) {
		this.prf_gender = prf_gender;
	}

	public String getPrf_nickname() {
		return prf_nickname;
	}

	public void setPrf_nickname(String prf_nickname) {
		this.prf_nickname = prf_nickname;
	}

	public long getCou_id() {
		return cou_id;
	}

	public void setCou_id(long cou_id) {
		this.cou_id = cou_id;
	}

	public String getPrf_language() {
		return prf_language;
	}

	public void setPrf_language(String prf_language) {
		this.prf_language = prf_language;
	}

	public String getPrf_ip() {
		return prf_ip;
	}

	public void setPrf_ip(String prf_ip) {
		this.prf_ip = prf_ip;
	}

	public String getPrf_country() {
		return prf_country;
	}

	public void setPrf_country(String prf_country) {
		this.prf_country = prf_country;
	}

	public int getPrf_level() {
		return prf_level;
	}

	public void setPrf_level(int prf_level) {
		this.prf_level = prf_level;
	}

	public long getPrf_credit() {
		return prf_credit;
	}

	public void setPrf_credit(long prf_credit) {
		this.prf_credit = prf_credit;
	}

	public String getPrf_address() {
		return prf_address;
	}

	public void setPrf_address(String prf_address) {
		this.prf_address = prf_address;
	}

	public String getPrf_city() {
		return prf_city;
	}

	public void setPrf_city(String prf_city) {
		this.prf_city = prf_city;
	}

	public String getPrf_complement() {
		return prf_complement;
	}

	public void setPrf_complement(String prf_complement) {
		this.prf_complement = prf_complement;
	}

	public String getPrf_neighborhood() {
		return prf_neighborhood;
	}

	public void setPrf_neighborhood(String prf_neighborhood) {
		this.prf_neighborhood = prf_neighborhood;
	}

	public String getPrf_number() {
		return prf_number;
	}

	public void setPrf_number(String prf_number) {
		this.prf_number = prf_number;
	}

	public String getPrf_phone() {
		return prf_phone;
	}

	public void setPrf_phone(String prf_phone) {
		this.prf_phone = prf_phone;
	}

	public String getPrf_state() {
		return prf_state;
	}

	public void setPrf_state(String prf_state) {
		this.prf_state = prf_state;
	}

	public String getPrf_company() {
		return prf_company;
	}

	public void setPrf_company(String prf_company) {
		this.prf_company = prf_company;
	}

	public String getPrf_website() {
		return prf_website;
	}

	public void setPrf_website(String prf_website) {
		this.prf_website = prf_website;
	}

	public String getPrf_billingday() {
		return prf_billingday;
	}

	public void setPrf_billingday(String prf_billingday) {
		this.prf_billingday = prf_billingday;
	}

	public int getPrf_wizard() {
		return prf_wizard;
	}

	public void setPrf_wizard(int prf_wizard) {
		this.prf_wizard = prf_wizard;
	}


}
