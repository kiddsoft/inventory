package pers.qfy.struts.action;
//业务审核的action
//由外部的JSP调用，业务审核的全在这里
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
    	//根据根据参数来判断执行哪个操作
    	String cmd=request.getParameter("command");
    	FUtil.print("examineAction当前命令" + cmd);
    	
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
		//显示订单
    	else if(cmd.equals("view")){
    		af=View(mapping,form,request,response);
    	}
		//单个允许
    	else if(cmd.equals("singleallow")){
    		ProcessIndent(1, cf.getIno());
        	return mapping.findForward("examineview");
    	}
		//单个拒绝
    	else if(cmd.equals("singleforbidden")){
    		ProcessIndent(2, cf.getIno());
        	return mapping.findForward("examineview");
    	}
		//允许全部
    	else if(cmd.equals("allallow")){
    		af=AllProcess(mapping,form,request,response, 1);
    	}
		//拒绝全部
    	else if(cmd.equals("allforbidden")){
    		af=AllProcess(mapping,form,request,response, 2);
    	}
		//历史记录
    	else if(cmd.equals("history")){
    		af=History(mapping,form,request,response);
    	}
		//删除历史记录
    	else if(cmd.equals("delete")){
    		af=Del(mapping,form,request,response);
    	}
		
		return af;
	}
	public ActionForward View(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//待审核的
		String action = "examine.do?command=view";
		String selectSql = "select * from tb_indent where istate = '0'";
		
    	Map map = getPage(dao, FORMNAME, action, Integer.parseInt(currPage), selectSql, dao.CLASSNAME);
    	
    	SetRegParameter(request, map);

    	return mapping.findForward("viewUI");
    }
	
	public void ProcessIndent(int pro, String ino){
		//在tb_indent表中根据ino查询出一行信息
		TbIndent tb = (TbIndent)dao.QueryForKeyOneResultOne("tb_indent","ino", ino, dao.CLASSNAME);
		if(tb != null){
			//查询出订单 所对应 的商品信息
			TbCommodity commodity = (TbCommodity)dao.QueryCommodityInfo(tb.getCno());
			if(commodity != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Timestamp tp = FUtil.getSystemTime();//获取当前系统时间
				String tm = sdf.format(tp);//把时间转换为字符串
				String sqlIndent = "update tb_indent set istate = '" + pro +"', endtime='" + tm +  "' where ino='" + tb.getIno() + "'";//订单的SQL
				String sqlCommodity = null;//商品的SQL
				
				int gain = 0;//记录利润或者支出
				
				if(pro == 1){//同意的订单
					int nNewCount = 0;
					if(tb.getIsoutsell() == 1){
						//销售的订单
						if(tb.getIcount() > commodity.getCount()){
							FUtil.print(cf.getCno() + "库存不足");
							return;
						}
						nNewCount = commodity.getCount() - tb.getIcount();					
						gain = commodity.getOutprice() - commodity.getInprice();
					}else{
						//采购的订单
						nNewCount = commodity.getCount() + tb.getIcount();
						gain = 0 - commodity.getInprice();
					}
					sqlCommodity = "update tb_commodity set count='"+ nNewCount + "' where cno='" + commodity.getCno() + "'";
				}

				dao.ExecSql(sqlIndent);
				if(sqlCommodity != null){
					FUtil.print("商品操作完成");
					dao.ExecSql(sqlCommodity);
					
					FUtil.print("开始记录");
					TrueRecordDAO rd = new TrueRecordDAO();
					
					gain = gain * tb.getIcount();//单个商品的支出/收入，乘以数量，即可得出总价
					rd.AddData(tb.getIno(), tb.getCno(), commodity.getName(), tb.getUsername(), tb.getSuperior(),
							tb.getClientname(),tb.getClientphone(),
							tb.getIsoutsell(), tb.getIcount(), commodity.getInprice(), commodity.getOutprice(), gain, tp);
					FUtil.print("记录完成");
				}
			}
		}
	}
	
	public ActionForward AllProcess(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response, int pro){
		//全部允许或者拒绝的
		if(pro == 2){
			String sql = "update tb_indent set istate = '" + pro +"'and superior='" + userId + "'";
			dao.ExecSql(sql);
		}
		else{
			//全部同意的情况，则需要先获取本审核者的审核订单，然后遍历审核
			//以免一些订单中库存数量不足的情况发生
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
		//历史记录
		String action = "examine.do?command=history";
		String sql = "select * from tb_indent where istate != '0' and superior='" + userId + "'";
		
    	Map map = getPage(dao, FORMNAME, action, Integer.parseInt(currPage), sql, dao.CLASSNAME);

    	SetRegParameter(request, map);
		return mapping.findForward("historyUI");
	}
	public ActionForward Del(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//删除历史记录
		
		String[] delAllCno = request.getParameterValues("allino");
    	String[] selCno = request.getParameterValues("selectedino");
    	
    	
    	if(selCno != null && selCno.length > 0){
    		//根据选中的来删除
    		
			for(int i=0;i<selCno.length;i++){
				FUtil.print("选择删除" + selCno[i]);
				dao.Delete(dao.INO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
    		
			//删除全部 
    		String sql = "delete from tb_indent where istate!='0' and superior='" + userId + "'";
    		FUtil.print(sql);
    		dao.ExecSql(sql);
		}
    	return mapping.findForward("history");
	}

}
