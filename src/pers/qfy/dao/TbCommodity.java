package pers.qfy.dao;

/**
 * TbCommodity entity. @author MyEclipse Persistence Tools
 */

public class TbCommodity implements java.io.Serializable {

	// Fields

	private String cno;
	private String name;
	private Integer inprice;
	private Integer outprice;
	private Integer count;
	private String description;

	// Constructors

	/** default constructor */
	public TbCommodity() {
	}

	/** minimal constructor */
	public TbCommodity(String cno) {
		this.cno = cno;
	}

	/** full constructor */
	public TbCommodity(String cno, String name, Integer inprice,
			Integer outprice, Integer count, String description) {
		this.cno = cno;
		this.name = name;
		this.inprice = inprice;
		this.outprice = outprice;
		this.count = count;
		this.description = description;
	}

	// Property accessors

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}