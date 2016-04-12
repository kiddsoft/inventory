package pers.qfy.struts.form;

import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

public class IndentForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ino;
	private String cno;
	private String username;
	private String superior;
	private Integer icount;
	private Integer price;
	private Integer isoutsell;
	private Integer istate;
	private Timestamp itime;
	private Timestamp endtime;

	public String getIno() {
		return ino;
	}
	public void setIno(String ino) {
		this.ino = ino;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSuperior() {
		return superior;
	}
	public void setSuperior(String superior) {
		this.superior = superior;
	}
	public Integer getIcount() {
		return icount;
	}
	public void setIcount(Integer icount) {
		this.icount = icount;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getIsoutsell() {
		return isoutsell;
	}
	public void setIsoutsell(Integer isoutsell) {
		this.isoutsell = isoutsell;
	}
	public Integer getIstate() {
		return istate;
	}
	public void setIstate(Integer istate) {
		this.istate = istate;
	}
	public Timestamp getItime() {
		return itime;
	}
	public void setItime(Timestamp itime) {
		this.itime = itime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
