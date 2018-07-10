package org.seeker.mapper;

import java.util.List;

import org.seeker.entity.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

	List<Group> getList(Group po);

	int getCount(Group po);

	List<Group> getLoginGroupUserDetail(Group g);
}