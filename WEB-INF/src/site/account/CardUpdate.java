package site.account;

import site.account.dao.CardUpdateDAO;

public class CardUpdate {

	private long crd_id = 0;
	private String crd_token = null;
	private String crd_name = null;
	private String crd_number = null;
	private String crd_expiration = null;
	private String crd_cvv = null;

	public CardUpdate getCardUpdate(long id)
    {
		return (new CardUpdateDAO().getCardUpdate(id));
    }

	public boolean insert()
	{
		return new CardUpdateDAO().insert(this);
	}

	public long getCrd_id() {
		return crd_id;
	}

	public void setCrd_id(long crd_id) {
		this.crd_id = crd_id;
	}

	public String getCrd_token() {
		return crd_token;
	}

	public void setCrd_token(String crd_token) {
		this.crd_token = crd_token;
	}

	public String getCrd_name() {
		return crd_name;
	}

	public void setCrd_name(String crd_name) {
		this.crd_name = crd_name;
	}

	public String getCrd_number() {
		return crd_number;
	}

	public void setCrd_number(String crd_number) {
		this.crd_number = crd_number;
	}

	public String getCrd_expiration() {
		return crd_expiration;
	}

	public void setCrd_expiration(String crd_expiration) {
		this.crd_expiration = crd_expiration;
	}

	public String getCrd_cvv() {
		return crd_cvv;
	}

	public void setCrd_cvv(String crd_cvv) {
		this.crd_cvv = crd_cvv;
	}

}
