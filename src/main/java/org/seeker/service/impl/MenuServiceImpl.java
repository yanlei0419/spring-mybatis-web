package org.seeker.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.seeker.common.util.Uuid;
import org.seeker.entity.GroupMenu;
import org.seeker.entity.Menu;
import org.seeker.mapper.GroupMenuMapper;
import org.seeker.mapper.MenuMapper;
import org.seeker.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	private Logger l=LoggerFactory.getLogger(this.getClass());
	@Resource
	private MenuMapper menuMapper;
	@Resource
	private GroupMenuMapper gmDao;

	@Override
	public List<Menu> getList(Menu po) {
		return this.menuMapper.getList(po);
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public List<Menu> getPrivsMenuListByGroupId(Menu po) {
		return this.menuMapper.getPrivsMenuListByGroupId(po);
	}

	@Override
	public int addMenuGroup(Menu po) {
		String[] ids=po.getIds();
		if(null==ids){
			return -1;
		}
		l.debug("添加菜单权限,菜单ids>>>"+Arrays.toString(ids));
		String groupId=po.getGroupId();
		int delReulst=this.gmDao.deleteByGroupId(groupId);
		l.debug("删除该组["+groupId+"]权限全部菜单,共>>>"+delReulst);
		GroupMenu gm=null;
		int result=0;
		for (String s : ids) {
			gm=new GroupMenu();
			gm.setId(Uuid.getUUID());
			gm.setMenuid(s);
			gm.setGroupid(groupId);
			result+=this.gmDao.insert(gm);
		}
		l.debug("添加该组["+groupId+"]权限菜单,共>>>"+result);
		return result;
	}

	public void setGmDao(GroupMenuMapper gmDao) {
		this.gmDao = gmDao;
	}

	@Override
	public List<Menu> getGroupMenuList(Menu m) {
		return menuMapper.getGroupMenuList(m);
	}

}
