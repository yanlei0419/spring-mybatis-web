package com.seeker.service;

import java.util.List;

import com.seeker.entity.Menu;

public interface MenuService {
	List<Menu> getList(Menu po);

	List<Menu> getPrivsMenuListByGroupId(Menu po);

	int addMenuGroup(Menu po);

	List<Menu> getGroupMenuList(Menu m);
}
