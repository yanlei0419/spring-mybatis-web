package com.seeker.controller.sys;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.seeker.entity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seeker.common.base.controller.BaseController;
import com.seeker.common.util.EncodeUtils;
import com.seeker.common.util.JsonUtil;
import com.seeker.common.util.SysConstant;
import com.seeker.common.util.VeUtil;
import com.seeker.entity.User;
import com.seeker.service.UserService;
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	private Logger l=LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserService us;
	
	
//	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@GetMapping(value="/getList")
	public void getList(HttpServletRequest req, HttpServletResponse resp ,User po) throws IOException{
		PageInfo<User> data=this.us.getPageList(po);
		String result=JsonUtil.toJSONStringByFastjson(data);
		this.printJson(resp, result);
	}

	
	
//	@RequestMapping(value="/handle",method=RequestMethod.POST)
	@PostMapping(value="/handle")
	public String  save(HttpServletRequest req, HttpServletResponse resp ,User po) throws Exception{
		//TODO User 创建人目前没有填写  密码加密
		int result=this.us.save(po);
		String content="添加一条用户信息,账户名>>>>"+po.getUsername();
		String remark=content;
		this.saveSysLog(req, SysConstant.SQL_ADD, content, remark);
		l.debug("user添加[["+result+"]]条数据");
		return "redirect:/jsp/sys/user/list.jsp";
	}
	
	@RequestMapping(value="/handle",method=RequestMethod.GET)
	public String detail(HttpServletRequest req, HttpServletResponse resp ,User po){
		String flag=po.getFlag();
		po=this.us.getDetail(po);
		req.setAttribute("UserPo", po);
		if(null!=flag&&"update".equals(flag)){
			return "/sys/user/update";
		}
		return "/sys/user/detail";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(HttpServletRequest req, HttpServletResponse resp ,User po){
		int result=this.us.update(po);
		String content="修改一条用户信息,账户名>>>>"+po.getUsername();
		String remark=content;
		this.saveSysLog(req, SysConstant.SQL_ADD, content, remark);
		l.debug("user修改[["+result+"]]条数据");
		return "redirect:/jsp/sys/user/list.jsp";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public void delete(HttpServletRequest req, HttpServletResponse resp ,User po) throws IOException{
		int result=this.us.delete(po);
		this.print(resp, result+"");
	}
	
	@RequestMapping(value="/updatePw",method=RequestMethod.POST)
	public void updatePw(HttpServletRequest req, HttpServletResponse resp ,User po) throws  Exception{
		String password=EncodeUtils.EncoderByMd5(SysConstant.initPassWord);
		po.setPassword(password);
		int result=this.us.updatePw(po);
		this.print(resp, result+"");
	}
	
	@RequestMapping(value="/checkUsername",method=RequestMethod.POST)
	public void checkUsername(HttpServletRequest req, HttpServletResponse resp ,User po) throws  Exception{
		int result=this.us.getCount(po);
		this.print(resp, result+"");
	}
	
	
	
	@RequestMapping(value="/group/getList",method=RequestMethod.POST)
	public void getUserGroupList(HttpServletRequest req, HttpServletResponse resp ,User po) throws IOException{
		PageInfo<User> data=this.us.getUserGroupPageList(po);
		String result=JsonUtil.toJSONStringByFastjson(data);
		this.printJson(resp, result);
	}
	
	
	@RequestMapping(value="/updateNewPW",method=RequestMethod.POST)
	public void updateNewPW(HttpServletRequest req, HttpServletResponse resp ,User po) throws  Exception{
		User oldUser=VeUtil.getUserDeatil();
		String oldPassword=EncodeUtils.EncoderByMd5(po.getOldPassword());
		if(!oldUser.getPassword().equals(oldPassword)){//密码错误
			this.printJson(resp, "ERROR");
			return ;
		}
		String password=EncodeUtils.EncoderByMd5(po.getPassword());
		po.setPassword(password);
		po.setId(oldUser.getId());
		int result=this.us.update(po);
		if(result>0){
			this.printJson(resp, "SUCCESS");
		}else{
			this.printJson(resp, "0");
		}
	}
	
	
	@RequestMapping(value="/group/delete",method=RequestMethod.POST)
	public void deleteUserGroup(HttpServletRequest req, HttpServletResponse resp ,User po) throws IOException{
		int result=this.us.deleteUserGroup(po);
		this.print(resp, result+"");
	}
	
	@RequestMapping(value="/group/add",method=RequestMethod.POST)
	public void addUserGroup(HttpServletRequest req, HttpServletResponse resp ,User po) throws IOException{
		int result=this.us.addUserGroup(po);
		this.print(resp, result+"");
	}
	
	
	@RequestMapping(value="/disableEnable",method=RequestMethod.POST)
	public void disableEnable(HttpServletRequest req, HttpServletResponse resp ,User po) throws IOException{
		int result=this.us.update(po);
		this.print(resp, result+"");
	}
	


	{this.setOperateModuleName(SysConstant.SYSTEM);}
}
