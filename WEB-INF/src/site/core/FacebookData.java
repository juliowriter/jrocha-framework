package site.core;

public class FacebookData {
	public String name;
	public String email;
	public String birthday;
	public String gender;
	public String password;
	public boolean token;
	public String algorithm;
	public String country;

	public FacebookData (String data)
	{
		this.name = this.getData(data, "name");
		this.email = this.getData(data, "email");
		this.birthday = this.getData(data, "birthday");
		this.gender = this.getData(data, "gender");
		this.password = this.getData(data, "password");
		String tokentest = this.getData(data, "oauth_token");
		tokentest = tokentest.substring(0, tokentest.indexOf("|"));
		this.token = tokentest.equals("167585783277907");
		this.algorithm = this.getData(data, "algorithm");
		this.country = this.getData(data,"country");
	}


	private String getData(String json, String field)
	{
		int idx = json.indexOf("\"" + field + "\":");
		int size = ("\"" + field + "\":").length();
		int idf = json.indexOf("\"", idx + size + 1);
		String ret = json.substring(idx + size + 1, idf);
		return ret;
	}

}
