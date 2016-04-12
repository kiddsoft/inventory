package pers.qfy.struts.action;

import java.util.List;

import javax.servlet.http.*;
import org.apache.struts.action.*;

import pers.qfy.dao.*;
import pers.qfy.daotrue.TrueCommodityDAO;
import pers.qfy.struts.form.*;
import pers.qfy.util.*;

public class CommodityAction extends Action{
	TrueCommodityDAO dao;
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
    	dao = new TrueCommodityDAO();
    	//根据根据参数来判断执行哪个操作
    	String cmd=request.getParameter("command");
    	FUtil.print("CommodityAction当前命令" + cmd);
    	
    	CommodityForm cf=(CommodityForm)form;

    	ActionForward af=null;
    	dao = new TrueCommodityDAO();
    	if(cmd == null){
    		af=mapping.findForward("show");
    	}
    	else if(cmd.equals("add")){
    		af=Add(mapping,form,request,response);
    	}
    	else if(cmd.equals("view")){
    		af=View(mapping,form,request,response);
    	}
    	else if(cmd.equals("modify")){
    		af=Modify(mapping,form,request,response);
    	}
    	else if(cmd.equals("delete")){
    		af=Del(mapping,form,request,response);
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
    public ActionForward View(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//将查找的内容保存到resultdata中，jsp页面在运行时就会去读取了
    	request.setAttribute("resultdata", null);
    	List<TbCommodity> li = dao.Query("*", "");
    	for(int i=0;i<li.size();i++){
    		TbCommodity obj = li.get(i);
    		FUtil.print(obj.getName() + " 读到的数据 " + obj.getInprice());
    	}
    	request.setAttribute("resultdata", li);
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
}
