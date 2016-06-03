package pers.qfy.struts.action;
//订单管理的action类
//销售与采购的都是这里的，只是使用pcmd这个参数来区分
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import pers.qfy.dao.*;
import pers.qfy.daotrue.*;
import pers.qfy.struts.form.IndentForm;
import pers.qfy.util.FUtil;

/*
 * 参数要重新设计，使用双参数的方式 应该不需要使用session来记录了
 * */
public class IndentAction extends BaseAction{
	IndentForm cf=null;
	TrueIndentDAO dao = null;
	final String FORMNAME = "indentForm";
	String currPage;
	String pcmd;

	//采购与销售的都会入进这里
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
		
		//command参数用于控制功能   pcmd用于表明动作是属于销售还是采购的
		String cmd  = request.getParameter("command");
		pcmd = request.getParameter("pcmd");
		if(pcmd==null)
		{
			HttpSession session = request.getSession(false);
			pcmd = (String)session.getAttribute("pcmd");
		}

		FUtil.print("IndentAction当前命令:cmd=" + cmd + "   pcmd=" + pcmd);
		
		ActionForward af=null;
		
		if(pcmd.equals("sell"))
		{
			//销售的
			//选择商品编号
			if(cmd.equals("select"))
			{
				af = Select("forward_sell_selectUI", "forward_sell_addUI", mapping, form, request, response);
			}
			//添加订单
			else if(cmd.equals("add"))
			{
				af = Add(1, mapping, form, request, response);
			}
			//删除订单 -- 删除未审核的订单 历史记录的不能删除
			else if(cmd.equals("delete"))
			{
				int isSell = 1;//1表示是销售
				int isHistory = 0;//0表示删除待审核的订单
				af = Del( isSell,isHistory, mapping, request);
			}
			//删除订单 -- 删除历史记录的
			else if(cmd.equals("deletehistory")){
				int isSell = 1;//1表示是销售
				int isHistory = 1;//1表示删除历史记录的订单
				af = Del( isSell,isHistory, mapping, request);
			}
			//历史记录
			else if(cmd.equals("history"))
			{
				af = View(1, 0, cmd, "forward_sell_historyUI", mapping, request);
			}
			//待审核订单
			else if(cmd.equals("pocessing"))
			{
				af = View(1, 1, cmd, "forward_sell_pocessingUI", mapping, request);
			}
		}
		else
		{
			//采购的
			//选择商品编号
			if(cmd.equals("select"))
			{
				af = Select("forward_purchase_selectUI", "forward_purchase_addUI", mapping, form, request, response);
			}
			//添加订单
			else if(cmd.equals("add"))
			{
				af = Add(0, mapping, form, request, response);
			}
			//删除订单 -- 删除未审核的订单
			else if(cmd.equals("delete"))
			{
				int isSell = 0;//0表示采购
				int isHistory = 0;//1表示删除历史记录的订单
				af = Del( isSell,isHistory, mapping, request);
			}
			//删除订单 -- 删除历史记录的
			else if(cmd.equals("deletehistory")){
				int isSell = 0;//0表示采购
				int isHistory = 1;//1表示删除历史记录的订单
				af = Del( isSell,isHistory, mapping, request);
			}
			//历史记录
			else if(cmd.equals("history"))
			{
				af = View(0, 0, cmd, "forward_purchase_historyUI", mapping, request);
			}
			//待审核订单
			else if(cmd.equals("pocessing"))
			{
				af = View(0, 1, cmd, "forward_purchase_pocessingUI", mapping, request);
			}
		}
		return af;
	}
	
	
	
	
	//选择商品ID
	//根据商品编号，得出信息，然后保存，再跳入添加订单页面
    public ActionForward Select(String selectforward,String addForward, ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	if(tb == null){
    		//没有该ID
    		return mapping.findForward(selectforward);
    	}
    	else{
    		//保存商品信息，然后跳到add页面
    		request.setAttribute("indent_info", tb);
    		return mapping.findForward(addForward);
    	}
    }

    
    
    
    
    //添加订单 	根据isoutsell的值来判断是销售还是采购
    public ActionForward Add(int isoutsell, ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response){
    	
    	HttpSession session=request.getSession(false);
    	String userId= (String)session.getAttribute("user_id");
    	TbUser userdata = (TbUser)dao.QueryForKeyOneResultOne("tb_user","username", userId, TbUser.class);
    	TbCommodity tb = (TbCommodity)dao.QueryCommodityInfo(cf.getCno());
    	
    	int allPrice  = tb.getOutprice() * cf.getIcount();
    	int istate    = 0;//当前状态设置为未审核
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

    
    
    
    
    
    //删除
    public ActionForward Del( int isOutsell, int isDelHistory, ActionMapping mapping,HttpServletRequest request){
    	
    	String[] delAllCno = request.getParameterValues("allino");
    	String[] selCno = request.getParameterValues("selectedino");
    	
    	String sql = "delete from tb_indent where isoutsell='" + isOutsell;
    	
    	if(isDelHistory == 1){
    		//删除历史记录
    		sql += "' and istate !='0'";
    	}
    	else{
    		sql += "' and istate='0'";
    	}
    	FUtil.print(sql);

    	if(selCno != null && selCno.length > 0){
    		//根据选中的来删除
			for(int i=0;i<selCno.length;i++){
				dao.Delete(dao.INO, selCno[i]);
			}
		}
    	else if(delAllCno != null && delAllCno.length > 0){
    		
			//删除全部  采购或销售  未审核的订单
    		dao.ExecSql(sql);
		}
    	if(isOutsell == 1)
    	{
    		return mapping.findForward("forward_sell_view");
    	}
    	return mapping.findForward("forward_purchase_view");
    }
    
    
    
    
    //列表
    //显示历史记录 或者待审核的订单记录
    public ActionForward View(int isoutsell, int isprocessing, String cmd, String forWardName, ActionMapping mapping, HttpServletRequest request)
    {
    	String sql = "select * from tb_indent where isoutsell='" + isoutsell + "' and istate ";
    	if(isprocessing == 1){
    		//查询待审核的, istate为0时表示未审核
    		sql += "= '0'";
    	}
    	else{
    		//查询已经审核的
    		sql += "!= '0'";
    	}
    	String action = "indent.do?command=" + cmd + "&pcmd=" + pcmd;

    	Map map = getPage(dao, FORMNAME, action, Integer.parseInt(currPage), sql, dao.CLASSNAME);
    	SetRegParameter(request, map);

    	return mapping.findForward(forWardName);
    }
}