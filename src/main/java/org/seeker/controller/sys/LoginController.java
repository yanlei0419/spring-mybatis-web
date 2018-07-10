package org.seeker.controller.sys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.seeker.common.base.controller.BaseController;
import org.seeker.common.base.spring3.SpringContextUtil;
import org.seeker.common.util.EncodeUtils;
import org.seeker.common.util.LoginUtils;
import org.seeker.common.util.SysConstant;
import org.seeker.entity.Group;
import org.seeker.entity.Menu;
import org.seeker.entity.User;
import org.seeker.service.UserService;
@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController {
	private Logger l=LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/handle",method=RequestMethod.GET)
	public void login(HttpServletRequest req, HttpServletResponse resp,User loginUser) throws Exception{
		UserService us=(UserService) SpringContextUtil.getBean("userService");
		User user=us.getDetailByUsername(loginUser);
		
		String password=EncodeUtils.EncoderByMd5(loginUser.getPassword());
		if(user==null) {
			this.print(resp, "NULL");
		}
		
		if(!user.getPassword().equals(password)) {//密码不相同
			this.print(resp, "PASSWORD");
			return;
		}
		if(!"1".equals(user.getStatus())) {//状态为冻结
			this.print(resp, "ERROR");
			return;
		}
		//获取角色信息
		List<Group> glist = LoginUtils.getUserGroup(user.getId());
		String[] groupIds=new String[glist.size()];
		for (int i = 0; i < glist.size(); i++) {
			groupIds[i]="'"+glist.get(i).getId()+"'";
		}
		//获取菜单信息
		List<Menu> mlist = LoginUtils.getUserMenu(groupIds);
		user.setMenus(mlist);
		//将信息存入session中,跳到主页
		HttpSession session = req.getSession();
		user.setSessionId(session.getId());
		session.setAttribute(SysConstant.sessionUser, user);
		session.setAttribute(SysConstant.sessionUser, user);
		this.print(resp, "SUCCESS");
	}
//	@RequestMapping(value="/handle",method=RequestMethod.GET)
//	public String login(HttpServletRequest req, HttpServletResponse resp,User loginUser) throws Exception{
//		User user=VeUtil.getUserDeatil();
//		UserService us=(UserService) SpringContextUtil.getBean("userService");
//		User user=us.getDetailByUsername(loginUser);
//		
//		//将信息存入session中,跳到主页
//		HttpSession session = req.getSession();
//		user.setSessionId(session.getId());
//		session.setAttribute(SysConstant.sessionUser, user);
//		this.print(resp, "SUCCESS");
//		return "redirect:/index.jsp";
//	}
	
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public String loginOut(HttpServletRequest req, HttpServletResponse resp, String username){
		//通过用户名将session中的账户名删除掉,跳转到登录页面
		l.debug("退出系统");

		HttpSession httpSession = req.getSession();
		String id = httpSession.getId();
		long creationTime = httpSession.getCreationTime();
		Date createDate= new Date(creationTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creationTimes = format.format(createDate);
		long lastTime = httpSession.getLastAccessedTime();
		String lastTimes = format.format(lastTime);
		int maxIncative = httpSession.getMaxInactiveInterval();
		String content="session编号："+id+"创建时间："+creationTimes+"最后访问时间:"+lastTimes+"有效期："+maxIncative/60+"分钟";
		l.debug("session编号："+id+"创建时间："+creationTimes+"最后访问时间:"+lastTimes+"有效期："+maxIncative/60+"分钟");
		//增加退出时的日志记录
		super.saveSysLog(req,SysConstant.LOGOUT, content, "");
		httpSession.removeAttribute(SysConstant.sessionUser);
		httpSession.invalidate();
		return "redirect:/login.jsp";
	}
	
	{this.setOperateModuleName(SysConstant.SYSTEM);}
	
	public static void main(String[] args) {
		System.out.println(new StandardPasswordEncoder("123456"));
		
	}
}
