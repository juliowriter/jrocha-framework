package site.core;

import java.util.List;

import site.core.dao.ImageDAO;

public class Image {

	private long img_id;
	private String img_title;
	private String img_file;
	private String img_tag;
	private String img_language;
	private String img_carousel_caption;
	private String img_carousel_description;
	private int img_size;

	public Image getImage(long id)
	{
		return (new ImageDAO().getImage(id));
	}

	public List<Image> getList(String order)
	{
		return new ImageDAO().getList(order);
	}

	public List<Image> getListByTag(String tag)
	{
		return new ImageDAO().getListByTag(tag);
	}

	public List<Image> getListByTag(String tag, String lng)
	{
		return new ImageDAO().getListByTag(tag, lng);
	}

	public long insert()
	{
		return new ImageDAO().insert(this);
	}

	public boolean save()
	{
		return new ImageDAO().save(this);
	}

	public boolean delete()
	{
		return new ImageDAO().delete(this);
	}

	public String getImg_file() {
		return img_file;
	}

	public void setImg_file(String img_file) {
		this.img_file = img_file;
	}

	public long getImg_id() {
		return img_id;
	}

	public void setImg_id(long img_id) {
		this.img_id = img_id;
	}

	public int getImg_size() {
		return img_size;
	}

	public void setImg_size(int img_size) {
		this.img_size = img_size;
	}

	public String getImg_title() {
		return img_title;
	}

	public void setImg_title(String img_title) {
		this.img_title = img_title;
	}

	public String getImg_tag() {
		return img_tag;
	}

	public void setImg_tag(String img_tag) {
		this.img_tag = img_tag;
	}

	public String getImg_carousel_caption() {
		return img_carousel_caption;
	}

	public void setImg_carousel_caption(String img_carousel_caption) {
		this.img_carousel_caption = img_carousel_caption;
	}

	public String getImg_carousel_description() {
		return img_carousel_description;
	}

	public void setImg_carousel_description(String img_carousel_description) {
		this.img_carousel_description = img_carousel_description;
	}

	public String getImg_language() {
		return img_language;
	}

	public void setImg_language(String img_language) {
		this.img_language = img_language;
	}


}
