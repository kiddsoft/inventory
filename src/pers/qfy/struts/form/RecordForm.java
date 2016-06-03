package pers.qfy.struts.form;

import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

public class RecordForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String ino;
	private String cno;
	private String cname;
	private String username;
	private String superior;
	private Integer issell;
	private Integer scount;
	private Integer inprice;
	private Integer outprice;
	private Integer gain;
	private Timestamp itime;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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
	public Integer getIssell() {
		return issell;
	}
	public void setIssell(Integer issell) {
		this.issell = issell;
	}
	public Integer getScount() {
		return scount;
	}
	public void setScount(Integer scount) {
		this.scount = scount;
	}
	public Integer getInprice() {
		return inprice;
	}
	public void setInprice(Integer inprice) {
		this.inprice = inprice;
	}
	public Integer getOutprice() {
		return outprice;
	}
	public void setOutprice(Integer outprice) {
		this.outprice = outprice;
	}
	public Integer getGain() {
		return gain;
	}
	public void setGain(Integer gain) {
		this.gain = gain;
	}
	public Timestamp getItime() {
		return itime;
	}
	public void setItime(Timestamp itime) {
		this.itime = itime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
