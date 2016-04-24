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
 * ����Ҫ������ƣ�ʹ��˫�����ķ�ʽ Ӧ�ò���Ҫʹ��session����¼��
 * */
public class IndentAction extends BaseAction{
	IndentForm cf=null;
	TrueIndentDAO dao = null;

	//�ɹ������۵Ķ����������
	public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
	{
		dao = new TrueIndentDAO();
		cf = (IndentForm)form;
		
		//command�������ڿ��ƹ���   pcmd���ڱ����������������ۻ��ǲɹ���
		String cmd  = request.getParameter("command");
		String pcmd = request.getParameter("pcmd");

		FUtil.print("IndentAction��ǰ����:cmd=" + cmd + "   pcmd=" + pcmd);
		
		ActionForward af=null;
		
		if(pcmd.equals("sell"))
		{
			//���۲���
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
			//�ɹ�����
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
				//û������ͨԱ��Ӧ��û��Ȩ��ɾ������ �쵼Ȩ��
				af = Del( mapping, form, request, response, 0);
			}
		}
		return af;
	}
	
	//������Ʒ��ţ��ó���Ϣ��Ȼ�󱣴棬��������Ӷ���ҳ��
    public ActionForward Select_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	FUtil.print(cf.getCno());
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//û�и�ID
    		return mapping.findForward("forward_sell_selectUI");
    	}
    	else{
    		//������Ʒ��Ϣ��Ȼ������addҳ��
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward("forward_sell_addUI");
    	}
    }
    
    //��Ӷ���
    public ActionForward Add_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	String ss = cf.getIno() + " " + cf.getCno() + "" + cf.getIcount();
    	FUtil.print("��ǰ��ȡ���� " + ss);
    	
    	HttpSession session=request.getSession(false);
    	String userId= (String)session.getAttribute("user_id");
    	TbUser userdata = (TbUser)dao.QueryForKeyOneResultOne("tb_user","username", userId, TbUser.class);
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	
    	int allPrice  = tb.getOutprice() * cf.getIcount();
    	int isoutsell = 1;//��ѯ����
    	int istate    = 0;//��ǰ״̬����Ϊδ���
    	Timestamp itime = FUtil.getSystemTime();
    	Timestamp endtime = FUtil.getSystemTime();
    	
    	TbIndent data = new TbIndent(cf.getIno(), cf.getCno(),userId,userdata.getSuperior(),
    			cf.getIcount(),allPrice,isoutsell,istate,itime, endtime);
    	
    	dao.saveDate(data);
    	return mapping.findForward("forward_sell_view");
    }
    
	//��ѯȫ��
    public ActionForward View_sell(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//�����ҵ����ݱ��浽resultdata�У�jspҳ��������ʱ�ͻ�ȥ��ȡ��
    	request.setAttribute("indentdata", null);
    	List<TbIndent> li = dao.QueryForKeyOne(dao.TABLENAME, dao.ISOUTSELL, "1", dao.CLASSNAME);
    	request.setAttribute("indentdata", li);
    	return mapping.findForward("forward_sell_viewUI");
    }
    
    //ɾ��
    public ActionForward Del(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response, int isSell){
    	
    	String[] delAllCno = request.getParameterValues("ino");
    	String[] selCno = request.getParameterValues("selectedcno");
    	
    	if(selCno != null && selCno.length > 0){
			FUtil.print("ɾ����ѡ");
			for(int i=0;i<selCno.length;i++){
				FUtil.print(selCno[i]);
				dao.Delete(dao.INO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
			FUtil.print("ɾ��ȫ��");
			for(int i=0;i<delAllCno.length;i++){
				FUtil.print(delAllCno[i]);
			}
			//���ݽ����ɾ��
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
    
  //������Ʒ��ţ��ó���Ϣ��Ȼ�󱣴棬��������Ӷ���ҳ��
    public ActionForward Select_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	FUtil.print(cf.getCno());
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//û�и�ID
    		return mapping.findForward("forward_purchase_selectUI");
    	}
    	else{
    		//������Ʒ��Ϣ��Ȼ������addҳ��
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward("forward_purchase_addUI");
    	}
    }
    
  //��Ӷ���
    public ActionForward Add_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	String ss = cf.getIno() + " " + cf.getCno() + "" + cf.getIcount();
    	FUtil.print("��ǰ��ȡ���� " + ss);
    	
    	HttpSession session=request.getSession(false);
    	String userId= (String)session.getAttribute("user_id");
    	TbUser userdata = (TbUser)dao.QueryForKeyOneResultOne("tb_user","username", userId, TbUser.class);
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	
    	int allPrice  = tb.getOutprice() * cf.getIcount();
    	int isoutsell = 0;//��ѯ�ɹ�
    	int istate    = 0;
    	Timestamp itime = FUtil.getSystemTime();
    	Timestamp endtime = FUtil.getSystemTime();
    	
    	TbIndent data = new TbIndent(cf.getIno(), cf.getCno(),userId,userdata.getSuperior(),
    			cf.getIcount(),allPrice,isoutsell,istate,itime, endtime);
    	
    	dao.saveDate(data);
    	return mapping.findForward("forward_purchase_view");
    }
    
  //��ѯȫ��
    public ActionForward View_purchase(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	//�����ҵ����ݱ��浽resultdata�У�jspҳ��������ʱ�ͻ�ȥ��ȡ��
    	FUtil.print("������VIEW");
    	request.setAttribute("indentdata", null);
    	List<TbIndent> li = dao.QueryForKeyOne(dao.TABLENAME, dao.ISOUTSELL, "0", dao.CLASSNAME);
    	FUtil.print("������"+li.size());
    	request.setAttribute("indentdata", li);
    	return mapping.findForward("forward_purchase_viewUI");
    }

}
