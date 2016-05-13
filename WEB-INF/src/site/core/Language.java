package site.core;

import java.util.List;

import site.core.dao.LanguageDAO;

public class Language {

	private long lng_id = 0;
	private String lng_from;
	private String lng_to;
	private String lng_language;
	private String lng_tag;

	public Language getLanguage(long id, String language)
	{
	  return (new LanguageDAO().getLanguage(id, language));
	}

	public Language getLanguage(String from, String language)
	{
	  return (new LanguageDAO().getLanguage(from, language));
	}

	public Language getLanguage(String from, String language, String tag)
	{
	  return (new LanguageDAO().getLanguage(from, language, tag));
	}

	public String[] getTags(String language) {
		return new LanguageDAO().getTags(language);
	}

	public List<Language> getList(String filtro, String order, String language)
	{
		return new LanguageDAO().getList(filtro, order, language);
	}

	public long insert(String language)
	{
		return new LanguageDAO().insert(this, language);
	}

	public boolean save(String language)
	{
		return new LanguageDAO().save(this, language);
	}

	public boolean delete(String language)
	{
		return new LanguageDAO().delete(this, language);
	}

	public String getLng_language() {
		return lng_language;
	}

	public void setLng_language(String lng_language) {
		this.lng_language = lng_language;
	}
	public String getLng_from() {
		return lng_from;
	}
	public void setLng_from(String lng_from) {
		this.lng_from = lng_from;
	}
	public long getLng_id() {
		return lng_id;
	}
	public void setLng_id(long lng_id) {
		this.lng_id = lng_id;
	}
	public String getLng_to() {
		return lng_to;
	}
	public void setLng_to(String lng_to) {
		this.lng_to = lng_to;
	}

	public String getLng_tag() {
		return lng_tag;
	}

	public void setLng_tag(String lng_tag) {
		this.lng_tag = lng_tag;
	}



}
