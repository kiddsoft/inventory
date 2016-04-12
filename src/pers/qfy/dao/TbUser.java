package pers.qfy.dao;

import java.sql.Timestamp;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */

public class TbUser implements java.io.Serializable {

	// Fields

	private String username;
	private String password;
	private String name;
	private Integer level;
	private String superior;
	private Integer isstock;
	private Integer issell;
	private Integer ismgr;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public TbUser() {
	}

	/** minimal constructor */
	public TbUser(String username) {
		this.username = username;
	}

	/** full constructor */
	public TbUser(String username, String password, String name, Integer level,
			String superior, Integer isstock, Integer issell, Integer ismgr,
			Timestamp createtime) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.level = level;
		this.superior = superior;
		this.isstock = isstock;
		this.issell = issell;
		this.ismgr = ismgr;
		this.createtime = createtime;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getSuperior() {
		return this.superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public Integer getIsstock() {
		return this.isstock;
	}

	public void setIsstock(Integer isstock) {
		this.isstock = isstock;
	}

	public Integer getIssell() {
		return this.issell;
	}

	public void setIssell(Integer issell) {
		this.issell = issell;
	}

	public Integer getIsmgr() {
		return this.ismgr;
	}

	public void setIsmgr(Integer ismgr) {
		this.ismgr = ismgr;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}