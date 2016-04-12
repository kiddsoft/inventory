package pers.qfy.struts.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pers.qfy.dao.TbCommodity;
import pers.qfy.dao.TbCommodityDAO;
import pers.qfy.dao.TbIndent;
import pers.qfy.dao.TbUser;
import pers.qfy.daotrue.TrueCommodityDAO;
import pers.qfy.daotrue.TrueIndentDAO;
import pers.qfy.daotrue.TrueUserDao;
import pers.qfy.struts.form.CommodityForm;
import pers.qfy.struts.form.IndentForm;
import pers.qfy.util.FUtil;

public class IndentAction extends Action{
	IndentForm cf=null;
	TrueIndentDAO dao = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
	{
		dao = new TrueIndentDAO();
		cf = (IndentForm)form;
		
		String cmd=request.getParameter("command");
		FUtil.print("IndentAction当前命令" + cmd);
		
		ActionForward af=null;
		if(cmd == null){
			af=mapping.findForward("show");
		}
		else if(cmd.equals("sellselect")){
			af=Select_sell(mapping,form,request,response);
		}
		else if(cmd.equals("selladd")){
			af=Add_sell(mapping,form,request,response);
		}
		else if(cmd.equals("sellview")){
			af=View_sell(mapping,form,request,response);
		}
		//下面的是采购的
		else if(cmd.equals("purchaseselect")){
			af=Select_purchase(mapping,form,request,response);
		}
		else if(cmd.equals("purchaseadd")){
			af=Add_purchase(mapping,form,request,response);
		}
		else if(cmd.equals("purchaseview")){
			af=View_purchase(mapping,form,request,response);
		}
		return af;
	}
	//查询全部
    public ActionForward View_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//将查找的内容保存到resultdata中，jsp页面在运行时就会去读取了
    	request.setAttribute("indentdata", null);
    	List<TbIndent> li = dao.QueryForKeyOne(dao.TABLENAME, dao.ISOUTSELL, "1", dao.CLASSNAME);
    	request.setAttribute("indentdata", li);
    	return mapping.findForward("sellviewUI");
    }
	
	//根据商品编号，得出信息，然后保存，再跳入添加订单页面
    public ActionForward Select_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	FUtil.print(cf.getCno());
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//没有该ID
    		return mapping.findForward("sellselectUI");
    	}
    	else{
    		//保存商品信息，然后跳到add页面
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward("selladdUI");
    	}
    }
    
    //添加订单
    public ActionForward Add_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	String ss = cf.getIno() + " " + cf.getCno() + "" + cf.getIcount();
    	FUtil.print("当前获取内容 " + ss);
    	
    	HttpSession session=request.getSession(false);
    	String userId= (String)session.getAttribute("user_id");
    	TbUser userdata = (TbUser)dao.QueryForKeyOneResultOne("tb_user","username", userId, TbUser.class);
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	
    	int allPrice = tb.getOutprice() * cf.getIcount();
    	int isoutsell = 1;
    	int istate = 0;
    	Timestamp itime = FUtil.getSystemTime();
    	Timestamp endtime = FUtil.getSystemTime();
    	
    	TbIndent data = new TbIndent(cf.getIno(), cf.getCno(),userId,userdata.getSuperior(),
    			cf.getIcount(),allPrice,isoutsell,istate,itime, endtime);
    	
    	dao.saveDate(data);
    	return mapping.findForward("selladdUI");
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
  //查询全部
    public ActionForward View_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//将查找的内容保存到resultdata中，jsp页面在运行时就会去读取了
    	FUtil.print("进行了VIEW");
    	request.setAttribute("indentdata", null);
    	List<TbIndent> li = dao.QueryForKeyOne(dao.TABLENAME, dao.ISOUTSELL, "0", dao.CLASSNAME);
    	FUtil.print("个数是"+li.size());
    	request.setAttribute("indentdata", li);
    	return mapping.findForward("purchaseviewUI");
    }
    
  //根据商品编号，得出信息，然后保存，再跳入添加订单页面
    public ActionForward Select_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	FUtil.print(cf.getCno());
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//没有该ID
    		return mapping.findForward("purchaseselectUI");
    	}
    	else{
    		//保存商品信息，然后跳到add页面
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward("purchaseaddUI");
    	}
    }
    
    //添加订单
    public ActionForward Add_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	String ss = cf.getIno() + " " + cf.getCno() + "" + cf.getIcount();
    	FUtil.print("当前获取内容 " + ss);
    	
    	HttpSession session=request.getSession(false);
    	String userId= (String)session.getAttribute("user_id");
    	TbUser userdata = (TbUser)dao.QueryForKeyOneResultOne("tb_user","username", userId, TbUser.class);
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	
    	int allPrice = tb.getOutprice() * cf.getIcount();
    	int isoutsell = 0;
    	int istate = 0;
    	Timestamp itime = FUtil.getSystemTime();
    	Timestamp endtime = FUtil.getSystemTime();
    	
    	TbIndent data = new TbIndent(cf.getIno(), cf.getCno(),userId,userdata.getSuperior(),
    			cf.getIcount(),allPrice,isoutsell,istate,itime, endtime);
    	
    	dao.saveDate(data);
    	return mapping.findForward("purchaseaddUI");
    }
}
