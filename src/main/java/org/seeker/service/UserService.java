package org.seeker.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.seeker.entity.User;

public interface UserService  {
// interface UserService{
	List<User> getList(User po);
	int getCount(User po);
	int update(User po);
	int delete(User po);
	User getDetail(User po);
	int save(User po)  throws Exception;
	int updatePw(User po);
	List<User> getUserGroupList(User po);
	int getUserGroupCount(User po);
	int addUserGroup(User po);
	int deleteUserGroup(User po);
	User getDetailByUsername(User po) ;
	PageInfo<User> getUserGroupPageList(User po);
	PageInfo<User> getPageList(User po);
}
