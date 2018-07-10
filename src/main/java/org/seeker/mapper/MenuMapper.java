package org.seeker.mapper;

import java.util.List;

import org.seeker.entity.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> getList(Menu menu);

	List<Menu> getPrivsMenuListByGroupId(Menu po);

	List<Menu> getGroupMenuList(Menu m);
}