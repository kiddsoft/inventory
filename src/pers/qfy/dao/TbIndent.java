package pers.qfy.dao;

import java.sql.Timestamp;

/**
 * TbIndent entity. @author MyEclipse Persistence Tools
 */

public class TbIndent implements java.io.Serializable {

	// Fields

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

	// Constructors

	/** default constructor */
	public TbIndent() {
	}

	/** minimal constructor */
	public TbIndent(String ino) {
		this.ino = ino;
	}

	/** full constructor */
	public TbIndent(String ino, String cno, String username, String superior,
			Integer icount, Integer price, Integer isoutsell, Integer istate,
			Timestamp itime, Timestamp endtime) {
		this.ino = ino;
		this.cno = cno;
		this.username = username;
		this.superior = superior;
		this.icount = icount;
		this.price = price;
		this.isoutsell = isoutsell;
		this.istate = istate;
		this.itime = itime;
		this.endtime = endtime;
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

	public Integer getIcount() {
		return this.icount;
	}

	public void setIcount(Integer icount) {
		this.icount = icount;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getIsoutsell() {
		return this.isoutsell;
	}

	public void setIsoutsell(Integer isoutsell) {
		this.isoutsell = isoutsell;
	}

	public Integer getIstate() {
		return this.istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public Timestamp getItime() {
		return this.itime;
	}

	public void setItime(Timestamp itime) {
		this.itime = itime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

}