package pers.qfy.dao;

import java.sql.Timestamp;

/**
 * TbRecord entity. @author MyEclipse Persistence Tools
 */

public class TbRecord implements java.io.Serializable {

	// Fields

	private String ino;
	private String cno;
	private String cname;
	private String username;
	private String superior;
	private String clientname;
	private String clientphone;
	private Integer issell;
	private String isselltype;
	private Integer scount;
	private Integer inprice;
	private Integer outprice;
	private Integer gain;
	private Timestamp itime;

	// Constructors

	/** default constructor */
	public TbRecord() {
	}

	/** minimal constructor */
	public TbRecord(String ino) {
		this.ino = ino;
	}

	/** full constructor */
	public TbRecord(String ino, String cno, String cname, String username,
			String superior, String clientname, String clientphone,
			Integer issell, Integer scount, Integer inprice, Integer outprice,
			Integer gain, Timestamp itime) {
		this.ino = ino;
		this.cno = cno;
		this.cname = cname;
		this.username = username;
		this.superior = superior;
		this.clientname = clientname;
		this.clientphone = clientphone;
		this.issell = issell;
		this.scount = scount;
		this.inprice = inprice;
		this.outprice = outprice;
		this.gain = gain;
		this.itime = itime;
	}

	// Property accessors

	public String getIno() {
		return this.ino;
	}

	public void setIno(String ino) {
		this.ino = ino;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSuperior() {
		return this.superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getClientname() {
		return this.clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getClientphone() {
		return this.clientphone;
	}

	public void setClientphone(String clientphone) {
		this.clientphone = clientphone;
	}

	public Integer getIssell() {
		return this.issell;
	}

	public void setIssell(Integer issell) {
		if(issell == 1){
			isselltype = "œ˙ €";
		}
		else{
			isselltype = "≤…π∫";
		}
		this.issell = issell;
	}

	public Integer getScount() {
		return this.scount;
	}

	public void setScount(Integer scount) {
		this.scount = scount;
	}

	public Integer getInprice() {
		return this.inprice;
	}

	public void setInprice(Integer inprice) {
		this.inprice = inprice;
	}

	public Integer getOutprice() {
		return this.outprice;
	}

	public void setOutprice(Integer outprice) {
		this.outprice = outprice;
	}

	public Integer getGain() {
		return this.gain;
	}

	public void setGain(Integer gain) {
		this.gain = gain;
	}

	public Timestamp getItime() {
		return this.itime;
	}

	public void setItime(Timestamp itime) {
		this.itime = itime;
	}

	public String getIsselltype() {
		return isselltype;
	}

	public void setIsselltype(String isselltype) {
		this.isselltype = isselltype;
	}

}