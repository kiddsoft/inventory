package pers.qfy.dao;

import java.sql.Timestamp;

/**
 * TbBbs entity. @author MyEclipse Persistence Tools
 */

public class TbBbs implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private String author;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public TbBbs() {
	}

	/** full constructor */
	public TbBbs(String title, String content, String author,
			Timestamp createtime) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}