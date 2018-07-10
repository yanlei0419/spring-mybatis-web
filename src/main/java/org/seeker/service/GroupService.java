package org.seeker.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.seeker.entity.Group;

public interface GroupService {

	List<Group> getList(Group po);

	int getCount(Group po);

	int save(Group po);

	int delete(Group po);

	int update(Group po);

	List<Group> getLoginGroupUserDetail(Group g);

	Group detail(Group po);

	List<Group> getAllGroupPrivs();


	PageInfo<Group> getPageList(Group po);

}
