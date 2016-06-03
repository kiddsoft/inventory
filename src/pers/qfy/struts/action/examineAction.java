package pers.qfy.struts.action;
//ҵ����˵�action
//���ⲿ��JSP���ã�ҵ����˵�ȫ������
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pers.qfy.dao.*;
import pers.qfy.daotrue.*;
import pers.qfy.struts.form.CommodityForm;
import pers.qfy.struts.form.IndentForm;
import pers.qfy.util.FUtil;

public class examineAction extends BaseAction{
	TrueIndentDAO dao;
	String currPage;
	final String FORMNAME = "commodityForm";
	IndentForm cf = null;
	String userId = null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
	{
		cf = (IndentForm)form;
		dao = new TrueIndentDAO();
    	//���ݸ��ݲ������ж�ִ���ĸ�����
    	String cmd=request.getParameter("command");
    	FUtil.print("examineAction��ǰ����" + cmd);
    	
    	HttpSession session = request.getSession();
    	userId = (String)session.getAttribute("user_id");
    	
    	currPage = request.getParameter("currPage");
		if(currPage==null || currPage=="")
		{
			currPage = "1";
		}
		this.locale = this.getLocale(request);
		this.message = this.getResources(request);
		
		
		ActionForward af=null;
		if(cmd == null){
    		af=mapping.findForward("view");
    	}
		//��ʾ����
    	else if(cmd.equals("view")){
    		af=View(mapping,form,request,response);
    	}
		//��������
    	else if(cmd.equals("singleallow")){
    		ProcessIndent(1, cf.getIno());
        	return mapping.findForward("examineview");
    	}
		//�����ܾ�
    	else if(cmd.equals("singleforbidden")){
    		ProcessIndent(2, cf.getIno());
        	return mapping.findForward("examineview");
    	}
		//����ȫ��
    	else if(cmd.equals("allallow")){
    		af=AllProcess(mapping,form,request,response, 1);
    	}
		//�ܾ�ȫ��
    	else if(cmd.equals("allforbidden")){
    		af=AllProcess(mapping,form,request,response, 2);
    	}
		//��ʷ��¼
    	else if(cmd.equals("history")){
    		af=History(mapping,form,request,response);
    	}
		//ɾ����ʷ��¼
    	else if(cmd.equals("delete")){
    		af=Del(mapping,form,request,response);
    	}
		
		return af;
	}
	public ActionForward View(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//����˵�
		String action = "examine.do?command=view";
		String selectSql = "select * from tb_indent where istate = '0'";
		
    	Map map = getPage(dao, FORMNAME, action, Integer.parseInt(currPage), selectSql, dao.CLASSNAME);
    	
    	SetRegParameter(request, map);

    	return mapping.findForward("viewUI");
    }
	
	public void ProcessIndent(int pro, String ino){
		//��tb_indent���и���ino��ѯ��һ����Ϣ
		TbIndent tb = (TbIndent)dao.QueryForKeyOneResultOne("tb_indent","ino", ino, dao.CLASSNAME);
		if(tb != null){
			//��ѯ������ ����Ӧ ����Ʒ��Ϣ
			TbCommodity commodity = (TbCommodity)dao.QueryCommodityInfo(tb.getCno());
			if(commodity != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Timestamp tp = FUtil.getSystemTime();//��ȡ��ǰϵͳʱ��
				String tm = sdf.format(tp);//��ʱ��ת��Ϊ�ַ���
				String sqlIndent = "update tb_indent set istate = '" + pro +"', endtime='" + tm +  "' where ino='" + tb.getIno() + "'";//������SQL
				String sqlCommodity = null;//��Ʒ��SQL
				
				int gain = 0;//��¼�������֧��
				
				if(pro == 1){//ͬ��Ķ���
					int nNewCount = 0;
					if(tb.getIsoutsell() == 1){
						//���۵Ķ���
						if(tb.getIcount() > commodity.getCount()){
							FUtil.print(cf.getCno() + "��治��");
							return;
						}
						nNewCount = commodity.getCount() - tb.getIcount();					
						gain = commodity.getOutprice() - commodity.getInprice();
					}else{
						//�ɹ��Ķ���
						nNewCount = commodity.getCount() + tb.getIcount();
						gain = 0 - commodity.getInprice();
					}
					sqlCommodity = "update tb_commodity set count='"+ nNewCount + "' where cno='" + commodity.getCno() + "'";
				}

				dao.ExecSql(sqlIndent);
				if(sqlCommodity != null){
					FUtil.print("��Ʒ�������");
					dao.ExecSql(sqlCommodity);
					
					FUtil.print("��ʼ��¼");
					TrueRecordDAO rd = new TrueRecordDAO();
					
					gain = gain * tb.getIcount();//������Ʒ��֧��/���룬�������������ɵó��ܼ�
					rd.AddData(tb.getIno(), tb.getCno(), commodity.getName(), tb.getUsername(), tb.getSuperior(),
							tb.getClientname(),tb.getClientphone(),
							tb.getIsoutsell(), tb.getIcount(), commodity.getInprice(), commodity.getOutprice(), gain, tp);
					FUtil.print("��¼���");
				}
			}
		}
	}
	
	public ActionForward AllProcess(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response, int pro){
		//ȫ��������߾ܾ���
		if(pro == 2){
			String sql = "update tb_indent set istate = '" + pro +"'and superior='" + userId + "'";
			dao.ExecSql(sql);
		}
		else{
			//ȫ��ͬ������������Ҫ�Ȼ�ȡ������ߵ���˶�����Ȼ��������
			//����һЩ�����п������������������
			List<Serializable> li = dao.QueryProess();
			if(li != null && li.size() >0){
				for(int i=0;i < li.size(); i++){
					TbIndent obj = (TbIndent)li.get(i);
					ProcessIndent(1, obj.getIno());
				}
			}
		}
		return mapping.findForward("examineview");
	}
	public ActionForward History(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//��ʷ��¼
		String action = "examine.do?command=history";
		String sql = "select * from tb_indent where istate != '0' and superior='" + userId + "'";
		
    	Map map = getPage(dao, FORMNAME, action, Integer.parseInt(currPage), sql, dao.CLASSNAME);

    	SetRegParameter(request, map);
		return mapping.findForward("historyUI");
	}
	public ActionForward Del(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//ɾ����ʷ��¼
		
		String[] delAllCno = request.getParameterValues("allino");
    	String[] selCno = request.getParameterValues("selectedino");
    	
    	
    	if(selCno != null && selCno.length > 0){
    		//����ѡ�е���ɾ��
    		
			for(int i=0;i<selCno.length;i++){
				FUtil.print("ѡ��ɾ��" + selCno[i]);
				dao.Delete(dao.INO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
    		
			//ɾ��ȫ�� 
    		String sql = "delete from tb_indent where istate!='0' and superior='" + userId + "'";
    		FUtil.print(sql);
    		dao.ExecSql(sql);
		}
    	return mapping.findForward("history");
	}

}
