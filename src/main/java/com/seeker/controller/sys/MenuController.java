package com.seeker.controller.sys;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seeker.common.base.controller.BaseController;
import com.seeker.common.base.entity.JqueryZTreeNode;
import com.seeker.common.util.JsonUtil;
import com.seeker.entity.Menu;
import com.seeker.service.MenuService;
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {
	@Resource
	private MenuService service;
	
	@RequestMapping(value="/getTree",method=RequestMethod.GET)
	public void getMenuTree(HttpServletRequest req, HttpServletResponse res, Menu po) throws Exception{
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		po.setType("1");
		List<Menu> list=service.getPrivsMenuListByGroupId(po);
		List<JqueryZTreeNode> tree=new ArrayList<JqueryZTreeNode>();
		String base=req.getContextPath();
		boolean checked=false;
		boolean open=true;
		JqueryZTreeNode r=new JqueryZTreeNode("-1", "", "菜单", checked, open);//根节点
		tree.add(r);
		r.setIconOpen(base+"/plugins/zTree/css/zTreeStyle/img/diy/1_open.png");
		r.setIconClose(base+"/plugins/zTree/css/zTreeStyle/img/diy/1_close.png");
		for (Menu m : list) {
			if(null!=m.getGroupId()&&!"".equals(m.getGroupId())){
				checked=true;
			}else{
				checked=false;
			}
			r=new JqueryZTreeNode(m.getId(), m.getPid(), m.getName(), checked, open);
			r.setIcon(base+"/plugins/zTree/css/zTreeStyle/img/diy/3.png");
			tree.add(r);
		}
		
		String json=JsonUtil.toJSONStringByFastjson(tree);
		
		PrintWriter out=res.getWriter();
		out.print(json); 
		out.flush();
		out.close();
	}
	

	@RequestMapping(value="/handleMenuGroup",method=RequestMethod.POST)
	public void addMenuGroup(HttpServletRequest req, HttpServletResponse res, Menu po) throws Exception{
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		int result=this.service.addMenuGroup(po);
		PrintWriter out=res.getWriter();
		out.print(result); 
		out.flush();
		out.close();
	}

}
