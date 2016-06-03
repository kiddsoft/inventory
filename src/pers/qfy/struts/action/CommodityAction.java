package pers.qfy.struts.action;
//这是个负责库存管理的action
//由外部的库存管理JSP页面调用，然后用dao去访问tb_commodity数据库
//库存的相关操作都在里
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import pers.qfy.dao.*;
import pers.qfy.daotrue.*;
import pers.qfy.factory.BaseHibernateDAO;
import pers.qfy.struts.form.*;
import pers.qfy.util.*;

public class CommodityAction extends BaseAction{
	TrueCommodityDAO dao;
	String currPage;
	final String FORMNAME = "commodityForm";
	String action_paging = "commodity.do?command=view";//显示信息页面的分页的组装action语句
	String action_findview = "commodity.do?command=findview";//查询的组装action语句
	String action_clearshow = "commodity.do?command=clearShow";//清空过期商品页面的分页的组装action语句
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
    	dao = new TrueCommodityDAO();
    	
    	//将网页上面的内容，变成代码的内容
    	CommodityForm cf=(CommodityForm)form;

    	//根据根据参数来判断执行哪个操作
    	String cmd=request.getParameter("command");
    	FUtil.print("CommodityAction当前命令" + cmd);

    	//获取当前的页数
    	currPage = request.getParameter("currPage");
		if(currPage==null || currPage=="")
		{
			currPage = "1";
		}
		
		//设置条件查询的界面文字
		this.locale = this.getLocale(request);
		this.message = this.getResources(request);
		titles = new String[2];
    	titles[0] = message.getMessage(locale, "commodity.Id");
    	titles[1] = message.getMessage(locale, "commodity.name");
    	names = new String[2];
    	names[0] = "cno";
    	names[1] = "name";

    	ActionForward af=null;
    	if(cmd == null){
    		af=mapping.findForward("show");
    	}
    	
    	else if(cmd.equals("add")){
    		//处理添加页面提交的内容
    		af=Add(mapping,form,request,response);
    	}
    	else if(cmd.equals("view")){
    		//处理要求显示商品的请求，查询数据库然后显示
    		af=View("", action_findview, "viewUI", mapping,form,request,response);
    	}
    	else if(cmd.equals("modify")){
    		//处理请求进入修改页页面  查询数据 然后跳到修改页面
    		af=Modify(mapping,form,request,response);
    	}
    	else if(cmd.equals("delete")){
    		//处理删除的请求
    		af=Del(mapping,form,request,response);
    	}
    	else if(cmd.equals("clearShow")){
    		//转到清理过期物品的界面，显示全部物品
    		af=ClearShow(mapping,form,request,response);
    	}
    	else if(cmd.equals("clear")){
    		//显示需要清理的物品的界面 显示单个物品
    		af=Clear(mapping,form,request,response);
    	}
    	else if(cmd.equals("cleardo")){
    		//执行清理过期物品操作
    		af=Cleardo(mapping,form,request,response);
    	}
    	else if(cmd.equals("findview")){
    		//条件查询
    		af=Find("commodity.do?command=findview","viewUI",mapping,form,request,response);
    	}
    	return af;
    }
    

    //添加商品
    public ActionForward Add(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	CommodityForm cf=(CommodityForm)form;

    	request.setAttribute("error", null);
    	HttpSession session = request.getSession(false);
    	String oldcno = (String)session.getAttribute("modifycommodityvcno");
    	if(oldcno == null){
    		//这是添加商品
    		List<TbCommodity> li = dao.Query(dao.CNO, cf.getCno());
        	if(li.size() == 0){
        		//没有该商品的编号，直接插入
        		dao.Add(cf);
        		return mapping.findForward("show");
        	}
        	request.setAttribute("error", "error");//设一个值，看到有值就说明是出错了
    	}
    	else{
    		//这是修改商品
    		dao.UpdateForCno(oldcno, cf);
    		return mapping.findForward("show");
    	}
    	return mapping.findForward("addUI");
    }
    
    //查询全部
    public ActionForward View(String sql, String action_find, String forward, ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//将查找的内容保存到resultdata中，jsp页面在运行时就会去读取了
    	//String action = "commodity.do?command=view";
    	if(sql==null || sql.equals("")){
    		sql = "select * from tb_commodity";
    	}

    	//将结果集放到分页条中
    	Map map = getPage(dao, FORMNAME, action_paging, Integer.parseInt(currPage), sql, dao.CLASSNAME);
    	SetRegParameter(request, map);

		getQuery(request, FORMNAME, action_find);

    	return mapping.findForward("viewUI");
    }
    
    //进入修改商品 页面
    public ActionForward Modify(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	CommodityForm cf=(CommodityForm)form;
    	List<TbCommodity> li = dao.Query(dao.CNO, cf.getCno());
    	if(li.size() >0){
    		TbCommodity obj = li.get(0);
    		request.setAttribute("modifycommoditylist", obj);
    		return mapping.findForward("addUI");
    	}
    	return mapping.findForward("show");
    }
    //删除商品
    public ActionForward Del(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){

    	String[] delAllCno = request.getParameterValues("allcno");
    	String[] selCno = request.getParameterValues("selectedcno");
    	
    	if(selCno != null && selCno.length > 0){
			FUtil.print("删除所选");
			for(int i=0;i<selCno.length;i++){
				FUtil.print(selCno[i]);
				dao.Delete(dao.CNO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
			FUtil.print("删除全部");
			for(int i=0;i<delAllCno.length;i++){
				FUtil.print(delAllCno[i]);
			}
			dao.DeleteAll("tb_commodity");
		}
		
    	return mapping.findForward("show");
    }
    //清理商品界面
    public ActionForward ClearShow(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//查询数据库，获取商品表信息，存起来，然后跳到clearshow.jsp
   	
    	Map map = getPage(dao, FORMNAME, action_clearshow, Integer.parseInt(currPage), "select * from tb_commodity", dao.CLASSNAME);
    	SetRegParameter(request, map);
    	
    	return mapping.findForward("clearShowUI");
    }
    //修改 清理商品的界面
    public ActionForward Clear(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	CommodityForm cf=(CommodityForm)form;
    	FUtil.print(cf.getCno());
    	List<TbCommodity> li = dao.Query(dao.CNO, cf.getCno());
    	if(li.size() >0){
    		TbCommodity obj = li.get(0);
    		request.setAttribute("list", obj);
    		return mapping.findForward("clearUI");
    	}
    	return mapping.findForward("clearView");
    }
    //执行 要清理的商品 操作
    public ActionForward Cleardo(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//保存要删除的商品数量 然后跳回到显示的界面
    	CommodityForm cf=(CommodityForm)form;
    	
    	int count = cf.getCount();
    	int delcount = Integer.parseInt(cf.getDelcount());
    	FUtil.print(cf.getCno() + " " + count + " " + delcount);
    	
    	int newCount = count - delcount;
    	
    	HttpSession session = request.getSession(false);
    	
    	TbCommodity data = (TbCommodity)dao.QueryForKeyOneResultOne(dao.TABLENAME, "cno", cf.getCno(), dao.CLASSNAME);
    	TrueRecordDAO rd = new TrueRecordDAO();
    	int gain = 0 - data.getInprice()*delcount;
    	rd.AddData(FUtil.getRandomString(10), data.getCno(), data.getName(), (String)session.getAttribute("user_id"), 
    			(String)session.getAttribute("user_id"),"", "", 2, delcount, data.getInprice(), data.getOutprice(), gain, FUtil.getSystemTime());
    	
    	String sql = "update tb_commodity set count='" + newCount + "' where cno='" + cf.getCno() + "'";
    	dao.ExecSql(sql);
    	
    	return mapping.findForward("clearView");
    }
    public ActionForward Find(String action, String forward, ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	
    	String sql = "select * from tb_commodity";
    	CommodityForm cf=(CommodityForm)form;

    	if(cf.getCno().length()>0 && cf.getName().length()>0)
    	{
    		sql += " where cno='" + cf.getCno() + "' and name='" + cf.getName() + "'";
    	}
    	else if(cf.getCno().length()>0){
    		sql += " where cno='" + cf.getCno() + "'";
    	}
    	else if(cf.getName().length()>0){
    		sql += " where name='" + cf.getName() + "'";
    	}
    	FUtil.print(sql);
    	
    	
    	return View(sql, action, forward, mapping,form,request,response);
    }
}
