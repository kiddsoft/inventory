package pers.qfy.struts.form;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private Integer level;
	private String superior;
	private Integer isstock;
	private Integer issell;
	private Integer ismgr;
	private Timestamp createtime;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getSuperior() {
		return superior;
	}
	public void setSuperior(String superior) {
		this.superior = superior;
	}
	public Integer getIsstock() {
		return isstock;
	}
	public void setIsstock(Integer isstock) {
		this.isstock = isstock;
	}
	public Integer getIssell() {
		return issell;
	}
	public void setIssell(Integer issell) {
		this.issell = issell;
	}
	public Integer getIsmgr() {
		return ismgr;
	}
	public void setIsmgr(Integer ismgr) {
		this.ismgr = ismgr;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
