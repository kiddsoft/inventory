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
	private String leveltype;
	private String superior;
	private Integer isstock;
	private String isstocktype;
	private Integer issell;
	private String isselltype;
	private Integer ismgr;
	private String ismgrtype;
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
		if(level == 0){
			leveltype = "管理员";
		}
		else{
			leveltype = "普通用户";
		}
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
		if(isstock == 1){
			isstocktype = "有";
		}
		else{
			isstocktype = "无";
		}
		this.isstock = isstock;
	}

	public Integer getIssell() {
		return this.issell;
	}

	public void setIssell(Integer issell) {
		if(issell == 1){
			isselltype = "有";
		}
		else{
			isselltype = "无";
		}
		this.issell = issell;
	}

	public Integer getIsmgr() {
		return this.ismgr;
	}

	public void setIsmgr(Integer ismgr) {
		if(ismgr == 1){
			ismgrtype = "有";
		}
		else{
			ismgrtype = "无";
		}
		this.ismgr = ismgr;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getLeveltype() {
		return leveltype;
	}

	public void setLeveltype(String leveltype) {
		this.leveltype = leveltype;
	}

	public String getIsselltype() {
		return isselltype;
	}

	public void setIsselltype(String isselltype) {
		this.isselltype = isselltype;
	}

	public String getIsstocktype() {
		return isstocktype;
	}

	public void setIsstocktype(String isstocktype) {
		this.isstocktype = isstocktype;
	}

	public String getIsmgrtype() {
		return ismgrtype;
	}

	public void setIsmgrtype(String ismgrtype) {
		this.ismgrtype = ismgrtype;
	}

}