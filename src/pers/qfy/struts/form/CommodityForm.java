package pers.qfy.struts.form;

import org.apache.struts.action.ActionForm;

public class CommodityForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String cno;
	private String name;
	private Integer inprice;
	private Integer outprice;
	private Integer count;
	private String description;
	private String oldcno;
	
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOldcno() {
		return oldcno;
	}
	public void setOldcno(String oldcno) {
		this.oldcno = oldcno;
	}
}
