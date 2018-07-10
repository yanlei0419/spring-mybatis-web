package org.seeker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.seeker.common.util.Uuid;
import org.seeker.entity.Group;
import org.seeker.entity.Menu;
import org.seeker.mapper.GroupMapper;
import org.seeker.mapper.MenuMapper;
import org.seeker.service.GroupService;
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

	@Override
	public PageInfo<Group> getPageList(Group po) {
		//引入分页查询，使用PageHelper分页功能
		//在查询之前传入当前页，然后多少记录
		PageHelper.startPage(po.getPage(),po.getRows());
		//startPage后紧跟的这个查询就是分页查询
		//使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
		//pageINfo封装了分页的详细信息，也可以指定连续显示的页数


		return new PageInfo<Group>(this.dao.getList(po));
	}


}
