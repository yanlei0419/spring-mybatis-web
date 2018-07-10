package org.seeker.common.util;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.seeker.common.base.spring3.SpringContextUtil;
import org.seeker.entity.Group;
import org.seeker.entity.Menu;
import org.seeker.entity.User;
import org.seeker.service.GroupService;
import org.seeker.service.MenuService;

public class LoginUtils {
	private static final Logger l=LoggerFactory.getLogger(LoginUtils.class);

	private static List<Menu> getAdminMenu() {
		Menu m=new Menu();
		m.setMlevel(2);
		List<Menu> ms=MenuUtil.getMenu(m);
		for (int i = 0; i < ms.size(); i++) {
			m.setMlevel(3);
			m.setPid(ms.get(i).getId());
			List<Menu> sons=MenuUtil.getMenu(m);
			ms.get(i).setSons(sons);
		}
		return ms;
	}
	
	public static User getAdminUser(){
		User u=new User();
		u.setUsername("admin");
		u.setName("超级管理员");
		u.setPassword(SysConstant.SPW);
		u.setMenus(getAdminMenu());
//		u.setGroups(getUserGroup(null));
//		for (int i = 0; i < u.getGroups().size(); i++) {
//			u.addAuthoritie(u.getGroups().get(i).getGroupName());
//		}
		u.addAuthoritie("ROLE_ADMIN");
		u.addAuthoritie("ROLE_USER");
		return u;
	}

	public static List<Group> getUserGroup(String userId) {
		GroupService gservice=(GroupService) SpringContextUtil.getBean("groupService");
		//角色信息处理
		//获取组信息和用户关系
		Group g=new Group();
		if(null!=userId){
			g.setUserId(userId);
			g.setStatus("1");
		}
		List<Group> glist=gservice.getLoginGroupUserDetail(g);//通过用户名获取该用户的全部组信息
		return glist;
	}

	public static List<Menu> getUserMenu(String[] groupIds) {

		MenuService mservice=(MenuService) SpringContextUtil.getBean("menuService");
		//菜单信息处理
		Menu m=new Menu();
		m.setGroupId(VeUtil.toStringArray(groupIds));
		m.setMlevel(2);
		m.setStatus("1");
		List<Menu> mlist=mservice.getGroupMenuList(m);
		if(null==mlist){
			l.info("该用户没有菜单信息没有菜单信息");
		}
		List<Menu> sons=null;
		for (int i = 0; i < mlist.size(); i++) {
			m.setPid(mlist.get(i).getId());
			m.setMlevel(3);
			m.setType("1");//菜单
			sons=mservice.getGroupMenuList(m);
			mlist.get(i).setSons(sons);
		}
		return mlist;
	}
	
	
	
	
}
