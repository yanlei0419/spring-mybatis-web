package com.seeker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.seeker.common.util.Uuid;
import com.seeker.entity.Group;
import com.seeker.entity.Menu;
import com.seeker.mapper.GroupMapper;
import com.seeker.mapper.MenuMapper;
import com.seeker.service.GroupService;
@Service("groupService")
public class GroupServiceImpl implements GroupService {
	@Resource
	private GroupMapper dao;
	@Resource
	private MenuMapper  menuMapper;

	@Override
	public List<Group> getList(Group po) {
		return this.dao.getList(po);
	}

	@Override
	public int getCount(Group po) {
		return this.dao.getCount(po);
	}

	public void setDao(GroupMapper dao) {
		this.dao = dao;
	}

	@Override
	public int save(Group po) {
		po.setId(Uuid.getUUID());
		po.setStatus("1");
		return dao.insertSelective(po);
	}

	@Override
	public int delete(Group po) {
		String[] ids=po.getIds();
		if(null==ids||ids.length<1){
			return 0;
		}
		int result=0;
		for (int i = 0; i < ids.length; i++) {
			result+=this.dao.deleteByPrimaryKey(ids[i]);
		}
		return result;
	}

	@Override
	public int update(Group po) {
		return dao.updateByPrimaryKeySelective(po);
	}


	@Override
	public List<Group> getLoginGroupUserDetail(Group g) {
		return dao.getLoginGroupUserDetail(g);
	}

	@Override
	public Group detail(Group po) {
		return dao.selectByPrimaryKey(po.getId());
	}

	@Override
	public List<Group> getAllGroupPrivs() {
		List<Group> list=dao.getList(new Group());
		Menu m=new Menu();
		for (int i = 0; i < list.size(); i++) {
			m.setGroupId("'"+list.get(i).getId()+"'");
			List<Menu> ms = menuMapper.getGroupMenuList(m);
			list.get(i).setMs(ms);
		}
		
		return list;
	}


}
