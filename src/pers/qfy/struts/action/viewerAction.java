package pers.qfy.struts.action;

import java.util.List;

import javax.servlet.http.*;
import org.apache.struts.action.*;

import pers.qfy.dao.*;
import pers.qfy.daotrue.TrueCommodityDAO;
import pers.qfy.struts.form.*;
import pers.qfy.util.*;

public class viewerAction extends Action{
	TrueCommodityDAO dao;
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
    	dao = new TrueCommodityDAO();
    	//���ݸ��ݲ������ж�ִ���ĸ�����
    	String cmd=request.getParameter("command");
    	FUtil.print("CommodityAction��ǰ����" + cmd);
    	
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

    //�����Ʒ
    public ActionForward Add(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	CommodityForm cf=(CommodityForm)form;

    	request.setAttribute("error", null);
    	HttpSession session = request.getSession(false);
    	String oldcno = (String)session.getAttribute("modifycommodityvcno");
    	if(oldcno == null){
    		//���������Ʒ
    		List<TbCommodity> li = dao.Query(dao.CNO, cf.getCno());
        	if(li.size() == 0){
        		//û�и���Ʒ�ı�ţ�ֱ�Ӳ���
        		dao.Add(cf);
        		return mapping.findForward("show");
        	}
        	request.setAttribute("error", "error");//��һ��ֵ��������ֵ��˵���ǳ�����
    	}
    	else{
    		//�����޸���Ʒ
    		dao.UpdateForCno(oldcno, cf);
    		return mapping.findForward("show");
    	}
    	return mapping.findForward("addUI");
    }
    
    //��ѯȫ��
    public ActionForward View(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//�����ҵ����ݱ��浽resultdata�У�jspҳ��������ʱ�ͻ�ȥ��ȡ��
    	request.setAttribute("resultdata", null);
    	List<TbCommodity> li = dao.Query("*", "");
    	for(int i=0;i<li.size();i++){
    		TbCommodity obj = li.get(i);
    		FUtil.print(obj.getName() + " ���������� " + obj.getInprice());
    	}
    	request.setAttribute("resultdata", li);
    	return mapping.findForward("viewUI");
    }
    
    //�����޸���Ʒ ҳ��
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
    //ɾ����Ʒ
    public ActionForward Del(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){

    	String[] delAllCno = request.getParameterValues("allcno");
    	String[] selCno = request.getParameterValues("selectedcno");
    	
    	if(selCno != null && selCno.length > 0){
			FUtil.print("ɾ����ѡ");
			for(int i=0;i<selCno.length;i++){
				FUtil.print(selCno[i]);
				dao.Delete(dao.CNO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
			FUtil.print("ɾ��ȫ��");
			for(int i=0;i<delAllCno.length;i++){
				FUtil.print(delAllCno[i]);
			}
			dao.DeleteAll("tb_commodity");
		}
		
    	return mapping.findForward("show");
    }
}
