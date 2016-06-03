package pers.qfy.struts.action;
//���Ǹ�����������action
//���ⲿ�Ŀ�����JSPҳ����ã�Ȼ����daoȥ����tb_commodity���ݿ�
//������ز���������
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
	String action_paging = "commodity.do?command=view";//��ʾ��Ϣҳ��ķ�ҳ����װaction���
	String action_findview = "commodity.do?command=findview";//��ѯ����װaction���
	String action_clearshow = "commodity.do?command=clearShow";//��չ�����Ʒҳ��ķ�ҳ����װaction���
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
    {
    	dao = new TrueCommodityDAO();
    	
    	//����ҳ��������ݣ���ɴ��������
    	CommodityForm cf=(CommodityForm)form;

    	//���ݸ��ݲ������ж�ִ���ĸ�����
    	String cmd=request.getParameter("command");
    	FUtil.print("CommodityAction��ǰ����" + cmd);

    	//��ȡ��ǰ��ҳ��
    	currPage = request.getParameter("currPage");
		if(currPage==null || currPage=="")
		{
			currPage = "1";
		}
		
		//����������ѯ�Ľ�������
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
    		//�������ҳ���ύ������
    		af=Add(mapping,form,request,response);
    	}
    	else if(cmd.equals("view")){
    		//����Ҫ����ʾ��Ʒ�����󣬲�ѯ���ݿ�Ȼ����ʾ
    		af=View("", action_findview, "viewUI", mapping,form,request,response);
    	}
    	else if(cmd.equals("modify")){
    		//������������޸�ҳҳ��  ��ѯ���� Ȼ�������޸�ҳ��
    		af=Modify(mapping,form,request,response);
    	}
    	else if(cmd.equals("delete")){
    		//����ɾ��������
    		af=Del(mapping,form,request,response);
    	}
    	else if(cmd.equals("clearShow")){
    		//ת�����������Ʒ�Ľ��棬��ʾȫ����Ʒ
    		af=ClearShow(mapping,form,request,response);
    	}
    	else if(cmd.equals("clear")){
    		//��ʾ��Ҫ�������Ʒ�Ľ��� ��ʾ������Ʒ
    		af=Clear(mapping,form,request,response);
    	}
    	else if(cmd.equals("cleardo")){
    		//ִ�����������Ʒ����
    		af=Cleardo(mapping,form,request,response);
    	}
    	else if(cmd.equals("findview")){
    		//������ѯ
    		af=Find("commodity.do?command=findview","viewUI",mapping,form,request,response);
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
    public ActionForward View(String sql, String action_find, String forward, ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//�����ҵ����ݱ��浽resultdata�У�jspҳ��������ʱ�ͻ�ȥ��ȡ��
    	//String action = "commodity.do?command=view";
    	if(sql==null || sql.equals("")){
    		sql = "select * from tb_commodity";
    	}

    	//��������ŵ���ҳ����
    	Map map = getPage(dao, FORMNAME, action_paging, Integer.parseInt(currPage), sql, dao.CLASSNAME);
    	SetRegParameter(request, map);

		getQuery(request, FORMNAME, action_find);

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
    //������Ʒ����
    public ActionForward ClearShow(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//��ѯ���ݿ⣬��ȡ��Ʒ����Ϣ����������Ȼ������clearshow.jsp
   	
    	Map map = getPage(dao, FORMNAME, action_clearshow, Integer.parseInt(currPage), "select * from tb_commodity", dao.CLASSNAME);
    	SetRegParameter(request, map);
    	
    	return mapping.findForward("clearShowUI");
    }
    //�޸� ������Ʒ�Ľ���
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
    //ִ�� Ҫ�������Ʒ ����
    public ActionForward Cleardo(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//����Ҫɾ������Ʒ���� Ȼ�����ص���ʾ�Ľ���
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
