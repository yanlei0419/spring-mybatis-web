package com.seeker.mapper;

import com.seeker.entity.GroupMenu;

public interface GroupMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupMenu record);

    int insertSelective(GroupMenu record);

    GroupMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupMenu record);

    int updateByPrimaryKey(GroupMenu record);

	int deleteByGroupId(String groupId);
}