package org.seeker.controller.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.seeker.common.base.controller.BaseController;
import org.seeker.common.base.entity.JqueryZTreeNode;
import org.seeker.common.util.JsonUtil;
import org.seeker.common.util.SysConstant;
import org.seeker.entity.Group;
import org.seeker.service.GroupService;

@Controller
@RequestMapping(value="/group")
public class GroupController extends BaseController {
	private Logger l=LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private GroupService gs;
	
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	public void getTreeList(HttpServletRequest req, HttpServletResponse resp, Group po) throws Exception{
		List<Group> list=this.gs.getList(po);
		
		List<JqueryZTreeNode> tree=new ArrayList<JqueryZTreeNode>();
		String base=req.getContextPath();
		JqueryZTreeNode r=new JqueryZTreeNode("-1", "", "角色", true, true);//根节点
		r.setChkDisabled(true);
		r.setIcon(base+"/plugins/easyui/themes/icons/group.png");
		
		r.setIconOpen(base+"/plugins/easyui/themes/icons/group_add.png");
		r.setIconClose(base+"/plugins/easyui/themes/icons/group_delete.png");
		
//		r.setIconOpen(base+"/plugins/zTree/css/zTreeStyle/img/diy/1_open.png");
//		r.setIconClose(base+"/plugins/zTree/css/zTreeStyle/img/diy/1_close.png");
		
		tree.add(r);
		for (Group g : list) {
			 r=new JqueryZTreeNode(g.getId(), "-1",g.getGroupName(), true, true);//根节点
			 r.setIcon(base+"/plugins/easyui/themes/icons/rolegroup.png");
			 tree.add(r);
		}
		String result=JsonUtil.toJSONStringByFastjson(tree);
		this.print(resp, result);
	}
	
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	public void getList(HttpServletRequest req, HttpServletResponse resp, Group po) throws Exception{

		PageInfo<Group> data=this.gs.getPageList(po);
		String result=JsonUtil.toJSONStringByFastjson(data);
		this.print(resp, result);
	}
	
	@RequestMapping(value="/handle",method=RequestMethod.POST)
	public String Add(HttpServletRequest req, HttpServletResponse res, Group po){
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		int result=this.gs.save(po);
		String content="添加一条角色信息,账户名>>>>"+po.getGroupName();
		String remark=content;
		this.saveSysLog(req, SysConstant.SQL_ADD, content, remark);
		l.debug("Group添加[["+result+"]]条数据");
		return "redirect:/jsp/sys/group/list.jsp";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String Update(HttpServletRequest req, HttpServletResponse res, Group po){
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		int result=this.gs.update(po);
		String content="修改一条用户信息,账户名>>>>"+po.getGroupName();
		String remark=content;
		this.saveSysLog(req, SysConstant.SQL_UPDATE, content, remark);
		l.debug("user修改[["+result+"]]条数据");
		return "redirect:/jsp/sys/group/list.jsp";
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(HttpServletRequest req, HttpServletResponse res, Group po){
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		po=this.gs.detail(po);
		req.setAttribute("GroupPo", po);
		return "/sys/group/update";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void delete(HttpServletRequest req, HttpServletResponse resp, Group po) throws Exception{
		int result=this.gs.delete(po);
		this.print(resp, result+"");
	}
	
	@RequestMapping(value="/disableEnable",method=RequestMethod.POST)
	public void disableEnable(HttpServletRequest req, HttpServletResponse resp, Group po) throws Exception{
		int result=this.gs.update(po);
		this.print(resp, result+"");
	}
	
	@RequestMapping(value="/checkGroupName",method=RequestMethod.GET)
	public void checkGroupName(HttpServletRequest req, HttpServletResponse resp, Group po) throws IOException{
		int result=this.gs.getCount(po);
		this.print(resp, result+"");
	}
	
	{this.setOperateModuleName(SysConstant.SYSTEM);}
	
}
