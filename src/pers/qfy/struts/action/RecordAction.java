package pers.qfy.struts.action;
//这个是历史交易的action
//这里只负责保存 查询 历史交易
//添加了一个获取当前资金的功能
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
		
		//根据根据参数来判断执行哪个操作
    	String cmd=request.getParameter("command");
    	FUtil.print("RecordAction当前命令" + cmd);

    	//获取当前的页数
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
		
		//将结果集放到分页条中
    	Map map = getPage(dao, FORMNAME, action_paging, Integer.parseInt(currPage), sql, dao.CLASSNAME);
    	SetRegParameter(request, map);

		//getQuery(request, FORMNAME, action_find);

    	return mapping.findForward("viewUI");
	}
	
	//设置当前历史资金的数额
	public void setMoney(HttpServletRequest request){

		//查询出tb_record表的全部信息
		List<Serializable> list = dao.QueryAll(dao.TABLENAME, dao.CLASSNAME);
		int money = 0;
		
		if(list != null){
			//循环遍历出资金，然后累加起来
			for(int i=0;i<list.size();i++){
				TbRecord tb = (TbRecord)list.get(i);
				money += tb.getGain();
			}
		}
		
		//存到session中，然后供jsp显示
		HttpSession session = request.getSession();
		session.setAttribute("money", money);
	}
}
