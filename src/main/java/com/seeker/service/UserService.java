package com.seeker.service;

import java.util.List;

import com.seeker.entity.User;

public interface UserService  {
//public interface UserService{
	public List<User> getList(User po);
	public int getCount(User po);
	public int update(User po);
	public int delete(User po);
	public User getDetail(User po);
	public int save(User po)  throws Exception;
	public int updatePw(User po);
	public List<User> getUserGroupList(User po);
	public int getUserGroupCount(User po);
	public int addUserGroup(User po);
	public int deleteUserGroup(User po);
	public User getDetailByUsername(User po) ;
}
