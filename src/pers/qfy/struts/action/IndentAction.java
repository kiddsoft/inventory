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

/*
 * 参数要重新设计，使用双参数的方式 应该不需要使用session来记录了
 * */
public class IndentAction extends BaseAction{
	IndentForm cf=null;
	TrueIndentDAO dao = null;

	//采购与销售的都会入进这里
	public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
	{
		dao = new TrueIndentDAO();
		cf = (IndentForm)form;
		
		//command参数用于控制功能   pcmd用于表明动作是属于销售还是采购的
		String cmd  = request.getParameter("command");
		String pcmd = request.getParameter("pcmd");

		FUtil.print("IndentAction当前命令:cmd=" + cmd + "   pcmd=" + pcmd);
		
		ActionForward af=null;
		
		if(pcmd.equals("sell"))
		{
			//销售操作
			if(cmd.equals("select"))
			{
				af = Select_sell( mapping, form, request, response);
			}
			else if(cmd.equals("add"))
			{
				af = Add_sell( mapping, form, request, response);
			}
			else if(cmd.equals("view"))
			{
				af = View_sell( mapping, form, request, response);
			}
			else if(cmd.equals("delete"))
			{
				af = Del( mapping, form, request, response, 1);
			}
		}
		else
		{
			//采购操作
			if(cmd.equals("select"))
			{
				af = Select_purchase( mapping, form, request, response);
			}
			else if(cmd.equals("add"))
			{
				af = Add_purchase( mapping, form, request, response);
			}
			else if(cmd.equals("view"))
			{
				af = View_purchase( mapping, form, request, response);
			}
			else if(cmd.equals("delete"))
			{
				//没处理，普通员工应该没有权限删除订单 领导权限
				af = Del( mapping, form, request, response, 0);
			}
		}
		return af;
	}
	
	//根据商品编号，得出信息，然后保存，再跳入添加订单页面
    public ActionForward Select_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	FUtil.print(cf.getCno());
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//没有该ID
    		return mapping.findForward("forward_sell_selectUI");
    	}
    	else{
    		//保存商品信息，然后跳到add页面
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward("forward_sell_addUI");
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
    	
    	int allPrice  = tb.getOutprice() * cf.getIcount();
    	int isoutsell = 1;//查询销售
    	int istate    = 0;//当前状态设置为未审核
    	Timestamp itime = FUtil.getSystemTime();
    	Timestamp endtime = FUtil.getSystemTime();
    	
    	TbIndent data = new TbIndent(cf.getIno(), cf.getCno(),userId,userdata.getSuperior(),
    			cf.getIcount(),allPrice,isoutsell,istate,itime, endtime);
    	
    	dao.saveDate(data);
    	return mapping.findForward("forward_sell_view");
    }
    
	//查询全部
    public ActionForward View_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//将查找的内容保存到resultdata中，jsp页面在运行时就会去读取了
    	request.setAttribute("indentdata", null);
    	List<TbIndent> li = dao.QueryForKeyOne(dao.TABLENAME, dao.ISOUTSELL, "1", dao.CLASSNAME);
    	request.setAttribute("indentdata", li);
    	return mapping.findForward("forward_sell_viewUI");
    }
    
    //删除
    public ActionForward Del(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response, int isSell){
    	
    	String[] delAllCno = request.getParameterValues("ino");
    	String[] selCno = request.getParameterValues("selectedcno");
    	
    	if(selCno != null && selCno.length > 0){
			FUtil.print("删除所选");
			for(int i=0;i<selCno.length;i++){
				FUtil.print(selCno[i]);
				dao.Delete(dao.INO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
			FUtil.print("删除全部");
			for(int i=0;i<delAllCno.length;i++){
				FUtil.print(delAllCno[i]);
			}
			//根据结果来删除
			dao.Delete(dao.ISOUTSELL, "" + isSell);
		}
    	if(isSell == 1)
    	{
    		return mapping.findForward("forward_sell_view");
    	}
    	return mapping.findForward("forward_purchase_view");
    }
	
    
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
  //根据商品编号，得出信息，然后保存，再跳入添加订单页面
    public ActionForward Select_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	FUtil.print(cf.getCno());
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//没有该ID
    		return mapping.findForward("forward_purchase_selectUI");
    	}
    	else{
    		//保存商品信息，然后跳到add页面
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward("forward_purchase_addUI");
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
    	
    	int allPrice  = tb.getOutprice() * cf.getIcount();
    	int isoutsell = 0;//查询采购
    	int istate    = 0;
    	Timestamp itime = FUtil.getSystemTime();
    	Timestamp endtime = FUtil.getSystemTime();
    	
    	TbIndent data = new TbIndent(cf.getIno(), cf.getCno(),userId,userdata.getSuperior(),
    			cf.getIcount(),allPrice,isoutsell,istate,itime, endtime);
    	
    	dao.saveDate(data);
    	return mapping.findForward("forward_purchase_view");
    }
    
  //查询全部
    public ActionForward View_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//将查找的内容保存到resultdata中，jsp页面在运行时就会去读取了
    	FUtil.print("进行了VIEW");
    	request.setAttribute("indentdata", null);
    	List<TbIndent> li = dao.QueryForKeyOne(dao.TABLENAME, dao.ISOUTSELL, "0", dao.CLASSNAME);
    	FUtil.print("个数是"+li.size());
    	request.setAttribute("indentdata", li);
    	return mapping.findForward("forward_purchase_viewUI");
    }

}
