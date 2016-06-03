package pers.qfy.struts.action;
//���������action��
//������ɹ��Ķ�������ģ�ֻ��ʹ��pcmd�������������
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import pers.qfy.dao.*;
import pers.qfy.daotrue.*;
import pers.qfy.struts.form.IndentForm;
import pers.qfy.util.FUtil;

/*
 * ����Ҫ������ƣ�ʹ��˫�����ķ�ʽ Ӧ�ò���Ҫʹ��session����¼��
 * */
public class IndentAction extends BaseAction{
	IndentForm cf=null;
	TrueIndentDAO dao = null;
	final String FORMNAME = "indentForm";
	String currPage;
	String pcmd;

	//�ɹ������۵Ķ����������
	public ActionForward execute(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
	{
		dao = new TrueIndentDAO();
		cf = (IndentForm)form;
		currPage = request.getParameter("currPage");
		if(currPage==null || currPage=="")
		{
			currPage = "1";
		}
		this.locale = this.getLocale(request);
		this.message = this.getResources(request);
		
		//command�������ڿ��ƹ���   pcmd���ڱ����������������ۻ��ǲɹ���
		String cmd  = request.getParameter("command");
		pcmd = request.getParameter("pcmd");
		if(pcmd==null)
		{
			HttpSession session = request.getSession(false);
			pcmd = (String)session.getAttribute("pcmd");
		}

		FUtil.print("IndentAction��ǰ����:cmd=" + cmd + "   pcmd=" + pcmd);
		
		ActionForward af=null;
		
		if(pcmd.equals("sell"))
		{
			//���۵�
			//ѡ����Ʒ���
			if(cmd.equals("select"))
			{
				af = Select("forward_sell_selectUI", "forward_sell_addUI", mapping, form, request, response);
			}
			//��Ӷ���
			else if(cmd.equals("add"))
			{
				af = Add(1, mapping, form, request, response);
			}
			//ɾ������ -- ɾ��δ��˵Ķ��� ��ʷ��¼�Ĳ���ɾ��
			else if(cmd.equals("delete"))
			{
				int isSell = 1;//1��ʾ������
				int isHistory = 0;//0��ʾɾ������˵Ķ���
				af = Del( isSell,isHistory, mapping, request);
			}
			//ɾ������ -- ɾ����ʷ��¼��
			else if(cmd.equals("deletehistory")){
				int isSell = 1;//1��ʾ������
				int isHistory = 1;//1��ʾɾ����ʷ��¼�Ķ���
				af = Del( isSell,isHistory, mapping, request);
			}
			//��ʷ��¼
			else if(cmd.equals("history"))
			{
				af = View(1, 0, cmd, "forward_sell_historyUI", mapping, request);
			}
			//����˶���
			else if(cmd.equals("pocessing"))
			{
				af = View(1, 1, cmd, "forward_sell_pocessingUI", mapping, request);
			}
		}
		else
		{
			//�ɹ���
			//ѡ����Ʒ���
			if(cmd.equals("select"))
			{
				af = Select("forward_purchase_selectUI", "forward_purchase_addUI", mapping, form, request, response);
			}
			//��Ӷ���
			else if(cmd.equals("add"))
			{
				af = Add(0, mapping, form, request, response);
			}
			//ɾ������ -- ɾ��δ��˵Ķ���
			else if(cmd.equals("delete"))
			{
				int isSell = 0;//0��ʾ�ɹ�
				int isHistory = 0;//1��ʾɾ����ʷ��¼�Ķ���
				af = Del( isSell,isHistory, mapping, request);
			}
			//ɾ������ -- ɾ����ʷ��¼��
			else if(cmd.equals("deletehistory")){
				int isSell = 0;//0��ʾ�ɹ�
				int isHistory = 1;//1��ʾɾ����ʷ��¼�Ķ���
				af = Del( isSell,isHistory, mapping, request);
			}
			//��ʷ��¼
			else if(cmd.equals("history"))
			{
				af = View(0, 0, cmd, "forward_purchase_historyUI", mapping, request);
			}
			//����˶���
			else if(cmd.equals("pocessing"))
			{
				af = View(0, 1, cmd, "forward_purchase_pocessingUI", mapping, request);
			}
		}
		return af;
	}
	
	
	
	
	//ѡ����ƷID
	//������Ʒ��ţ��ó���Ϣ��Ȼ�󱣴棬��������Ӷ���ҳ��
    public ActionForward Select(String selectforward,String addForward, ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//û�и�ID
    		return mapping.findForward(selectforward);
    	}
    	else{
    		//������Ʒ��Ϣ��Ȼ������addҳ��
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward(addForward);
    	}
    }

    
    
    
    
    //��Ӷ��� 	����isoutsell��ֵ���ж������ۻ��ǲɹ�
    public ActionForward Add(int isoutsell, ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	
    	HttpSession session=request.getSession(false);
    	String userId= (String)session.getAttribute("user_id");
    	TbUser userdata = (TbUser)dao.QueryForKeyOneResultOne("tb_user","username", userId, TbUser.class);
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	
    	int allPrice  = tb.getOutprice() * cf.getIcount();
    	int istate    = 0;//��ǰ״̬����Ϊδ���
    	Timestamp itime = FUtil.getSystemTime();
    	Timestamp endtime = FUtil.getSystemTime();
    	

    	TbIndent data = new TbIndent(cf.getIno(), cf.getCno(), userId, userdata.getSuperior(),
    			cf.getIcount(), allPrice, cf.getClientname(),
    			cf.getClientphone(), isoutsell, istate,
    			itime, endtime);

		
    	dao.saveDate(data);
    	if(isoutsell == 1){
    		return mapping.findForward("forward_sell_pocessing");
    	}
    	return mapping.findForward("forward_purchase_pocessing");    	
    }

    
    
    
    
    
    //ɾ��
    public ActionForward Del( int isOutsell, int isDelHistory, ActionMapping mapping,HttpServletRequest request){
    	
    	String[] delAllCno = request.getParameterValues("allino");
    	String[] selCno = request.getParameterValues("selectedino");
    	
    	String sql = "delete from tb_indent where isoutsell='" + isOutsell;
    	
    	if(isDelHistory == 1){
    		//ɾ����ʷ��¼
    		sql += "' and istate !='0'";
    	}
    	else{
    		sql += "' and istate='0'";
    	}
    	FUtil.print(sql);

    	if(selCno != null && selCno.length > 0){
    		//����ѡ�е���ɾ��
			for(int i=0;i<selCno.length;i++){
				dao.Delete(dao.INO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
    		
			//ɾ��ȫ��  �ɹ�������  δ��˵Ķ���
    		dao.ExecSql(sql);
		}
    	if(isOutsell == 1)
    	{
    		return mapping.findForward("forward_sell_view");
    	}
    	return mapping.findForward("forward_purchase_view");
    }
    
    
    
    
    //�б�
    //��ʾ��ʷ��¼ ���ߴ���˵Ķ�����¼
    public ActionForward View(int isoutsell, int isprocessing, String cmd, String forWardName, ActionMapping mapping, HttpServletRequest request)
    {
    	String sql = "select * from tb_indent where isoutsell='" + isoutsell + "' and istate ";
    	if(isprocessing == 1){
    		//��ѯ����˵�, istateΪ0ʱ��ʾδ���
    		sql += "= '0'";
    	}
    	else{
    		//��ѯ�Ѿ���˵�
    		sql += "!= '0'";
    	}
    	String action = "indent.do?command=" + cmd + "&pcmd=" + pcmd;

    	Map map = getPage(dao, FORMNAME, action, Integer.parseInt(currPage), sql, dao.CLASSNAME);
    	SetRegParameter(request, map);

    	return mapping.findForward(forWardName);
    }
}