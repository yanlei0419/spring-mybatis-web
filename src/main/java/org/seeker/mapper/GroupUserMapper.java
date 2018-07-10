package org.seeker.mapper;

import org.seeker.entity.GroupUser;

public interface GroupUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(GroupUser record);

    int insertSelective(GroupUser record);

    GroupUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GroupUser record);

    int updateByPrimaryKey(GroupUser record);

	int delete(GroupUser gu);
}