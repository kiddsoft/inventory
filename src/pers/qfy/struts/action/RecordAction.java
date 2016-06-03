package pers.qfy.struts.action;
//�������ʷ���׵�action
//����ֻ���𱣴� ��ѯ ��ʷ����
//�����һ����ȡ��ǰ�ʽ�Ĺ���
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pers.qfy.dao.TbIndent;
import pers.qfy.dao.TbRecord;
import pers.qfy.daotrue.*;
import pers.qfy.util.FUtil;
import java.util.List;
public class RecordAction extends BaseAction{
	TrueRecordDAO dao;
	String currPage;
	final String FORMNAME = "recordForm";
	String action_paging = "record.do?command=view";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){
		
		dao = new TrueRecordDAO();
		
		//���ݸ��ݲ������ж�ִ���ĸ�����
    	String cmd=request.getParameter("command");
    	FUtil.print("RecordAction��ǰ����" + cmd);

    	//��ȡ��ǰ��ҳ��
    	currPage = request.getParameter("currPage");
		if(currPage==null || currPage=="")
		{
			currPage = "1";
		}

		this.locale = this.getLocale(request);
		this.message = this.getResources(request);

		ActionForward af=null;
		
		if(cmd == null || cmd.equals("")){
    		af=mapping.findForward("show");
    	}
		else if(cmd.equals("view")){
			af = View(mapping,form,request,response);
		}
		
		setMoney(request);
		return af;
	}
	
	public ActionForward View(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
		String sql = "select * from tb_record";
		
		//��������ŵ���ҳ����
    	Map map = getPage(dao, FORMNAME, action_paging, Integer.parseInt(currPage), sql, dao.CLASSNAME);
    	SetRegParameter(request, map);

		//getQuery(request, FORMNAME, action_find);

    	return mapping.findForward("viewUI");
	}
	
	//���õ�ǰ��ʷ�ʽ������
	public void setMoney(HttpServletRequest request){

		//��ѯ��tb_record���ȫ����Ϣ
		List<Serializable> list = dao.QueryAll(dao.TABLENAME, dao.CLASSNAME);
		int money = 0;
		
		if(list != null){
			//ѭ���������ʽ�Ȼ���ۼ�����
			for(int i=0;i<list.size();i++){
				TbRecord tb = (TbRecord)list.get(i);
				money += tb.getGain();
			}
		}
		
		//�浽session�У�Ȼ��jsp��ʾ
		HttpSession session = request.getSession();
		session.setAttribute("money", money);
	}
}
