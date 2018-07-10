package org.seeker.common.util;

import java.util.List;

import org.seeker.entity.Menu;
import org.seeker.service.GroupService;
import org.seeker.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.seeker.common.base.spring3.SpringContextUtil;
import org.seeker.entity.Group;

@SuppressWarnings("unused")
public class MenuUtil {
	private static Logger l=LoggerFactory.getLogger(MenuUtil.class);
	
	public static List<Menu> getMenu(Menu po){
		MenuService service=(MenuService) SpringContextUtil.getBean("menuService");
		return service.getList(po);
	}
	private static List<Menu> getPrivsMenu(Menu po){
		MenuService service=(MenuService) SpringContextUtil.getBean("menuService");
		return service.getPrivsMenuListByGroupId(po);
	}
	private static Group getGroup(Group g){
		GroupService service=(GroupService) SpringContextUtil.getBean("groupService");
		List<Group> list=service.getList(g);
		if(null==list||list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public static List<Menu> handleMenu(){
		Menu po=new Menu();
		po.setType("1");//菜单
		po.setStatus("1");//启用
		List<Menu> list=getMenu(po);
		for (Menu m1 : list) {
			if(m1!=null &&null!=m1.getPid()&&!"-1".equals(m1.getPid())){
				for (Menu m2 : list) {
					if(m1.getId().equals(m2.getPid())){
						m1.getSons().add(m2);
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 获取层下的全部菜单
	 * @param level
	 * @return
	 */
	public static List<Menu> handleMenu(int level){
		Menu po=new Menu();
		po.setType("1");//菜单
		po.setStatus("1");//启用
		po.setMlevel(level);//启用
		List<Menu> list=getMenu(po);
		po=new Menu();
		po.setType("1");//菜单
		po.setStatus("1");//启用
		for (Menu m1 : list) {
			po.setPid(m1.getId());
			m1.setSons(getMenu(po));
		}
		return list;
	}
	
	public static List<Menu> handleMenuByGroupName(String name){
		Group g=new Group();
		g.setGroupName(name);
		g.setFlag("check");
		g=getGroup(g);
		
		List<Menu> list=LoginUtils.getUserMenu(new String[]{"'"+g.getId()+"'"});
		return list;
	}
	
}
