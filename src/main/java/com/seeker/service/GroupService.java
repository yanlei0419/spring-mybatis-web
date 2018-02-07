package com.seeker.service;

import java.util.List;

import com.seeker.entity.Group;

public interface GroupService {

	public List<Group> getList(Group po);

	public int getCount(Group po);

	public int save(Group po);

	public int delete(Group po);

	public int update(Group po);

	public List<Group> getLoginGroupUserDetail(Group g);

	public Group detail(Group po);

	public List<Group> getAllGroupPrivs();

}
