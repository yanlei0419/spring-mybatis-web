package org.seeker.mapper;

import java.util.List;

import org.seeker.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	int getCount(User po);

	List<User> getList(User po);

	List<User> getUserGroupList(User po);

	int getUserGroupCount(User po);

	List<User> getDetailByUsername(String username);
}