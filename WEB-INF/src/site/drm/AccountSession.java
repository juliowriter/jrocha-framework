package site.drm;

import java.util.List;

import site.drm.dao.AccountSessionDAO;
import site.core.Config;

public class AccountSession {

	private long ssn_id;
	private long cli_id;
	private String ssn_datetime;
	private String ssn_token;
	private String ssn_expire;
	private String ssn_ip;
	private long shf_id;
	private String ssn_logout;
	private int ssn_reason;
	private String ssn_userid;

	public AccountSession getSession(long id)
	{
		return (new AccountSessionDAO().getAccountSession(id));
	}

	public AccountSession getSession(String token)
	{
		return (new AccountSessionDAO().getAccountSession(token));
	}

	public AccountSession newSession(long shelfID, long cliID, int minutes)
	{
		return (new AccountSessionDAO().newSession(shelfID, cliID, minutes));
	}

	public AccountSession newSession(long cliID, long linkID, int minutes, String ip)
	{
		return (new AccountSessionDAO().newSession(cliID, linkID, minutes, ip));
	}

	public List<AccountSession> getSessionsExpired()
	{
		return (new AccountSessionDAO().getSessionsExpired());
	}

	public String getUrl()
	{
        String domain = new Config().getConfig("domain").getCfg_value();
	    return "http://" + domain + "/myshelf/" + this.getSsn_token().toLowerCase();
	}

	public boolean createLink(String origem, String link)
	{
		return (new AccountSessionDAO().createLink(origem, link));
	}

	public boolean isValid()
	{
		return (new AccountSessionDAO().isValid(this.getSsn_id()));
	}

	public boolean keepAlive()
	{
		return (new AccountSessionDAO().keepAlive(this.getSsn_id()));
	}

	public boolean endSession()
	{
		return (new AccountSessionDAO().endSession(this));
	}

	public boolean updateUser()
	{
		return (new AccountSessionDAO().updateUser(this));
	}

	public boolean endSession(int reason)
	{
		return (new AccountSessionDAO().endSession(this, reason));
	}

	public AccountSession lastValid(long shelfID)
	{
		return (new AccountSessionDAO().lastValid(shelfID));
	}

	public int[] verifyAccess(long cliID, long linkID, String ip)
	{
		return (new AccountSessionDAO().verifyAccess(cliID, linkID, ip));
	}

	public long getShf_id() {
		return shf_id;
	}

	public void setShf_id(long shf_id) {
		this.shf_id = shf_id;
	}

	public String getSsn_datetime() {
		return ssn_datetime;
	}

	public void setSsn_datetime(String ssn_datetime) {
		this.ssn_datetime = ssn_datetime;
	}

	public String getSsn_expire() {
		return ssn_expire;
	}

	public void setSsn_expire(String ssn_expire) {
		this.ssn_expire = ssn_expire;
	}

	public long getSsn_id() {
		return ssn_id;
	}

	public void setSsn_id(long ssn_id) {
		this.ssn_id = ssn_id;
	}

	public String getSsn_token() {
		return ssn_token;
	}

	public void setSsn_token(String ssn_token) {
		this.ssn_token = ssn_token;
	}

	public String getSsn_ip() {
		return ssn_ip;
	}

	public void setSsn_ip(String ssn_ip) {
		this.ssn_ip = ssn_ip;
	}

	public String getSsn_logout() {
		return ssn_logout;
	}

	public void setSsn_logout(String ssn_logout) {
		this.ssn_logout = ssn_logout;
	}

	public int getSsn_reason() {
		return ssn_reason;
	}

	public void setSsn_reason(int ssn_reason) {
		this.ssn_reason = ssn_reason;
	}

	public long getCli_id() {
		return cli_id;
	}

	public void setCli_id(long cli_id) {
		this.cli_id = cli_id;
	}

	public String getSsn_userid() {
		return ssn_userid;
	}

	public void setSsn_userid(String ssn_userid) {
		this.ssn_userid = ssn_userid;
	}

}
