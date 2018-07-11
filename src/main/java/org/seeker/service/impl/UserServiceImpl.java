package org.seeker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.seeker.entity.Group;
import org.seeker.entity.GroupUser;
import org.seeker.entity.Menu;
import org.seeker.entity.User;
import org.seeker.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.seeker.common.util.DateUtil;
import org.seeker.common.util.EncodeUtils;
import org.seeker.common.util.LoginUtils;
import org.seeker.common.util.SysConstant;
import org.seeker.common.util.Uuid;
import org.seeker.mapper.GroupUserMapper;
import org.seeker.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService,UserDetailsService {
	private Logger l=LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserMapper dao;
	@Resource
	private GroupUserMapper guDao;


	@Override
	public List<User> getList(User po) {
		return this.dao.getList(po);
	}

	@Override
	public int getCount(User po) {
		return this.dao.getCount(po);
	}

	@Override
	public int update(User po) {
		return this.dao.updateByPrimaryKeySelective(po);
	}

	@Override
	public int delete(User po) {
		String[] ids=po.getIds();
		if(null==ids){
			return 0;
		}
		int result=0;
		for (int i = 0; i < ids.length; i++) {
			result+=this.dao.deleteByPrimaryKey(ids[i]);
		}
//		return this.dao.deleteByPrimaryKey(po.getId());
		return result;
	}

	@Override
	public User getDetail(User po) {
		return this.dao.selectByPrimaryKey(po.getId());
	}
	@Override
	public User getDetailByUsername(User po) {
		List<User> list= this.dao.getDetailByUsername(po.getUsername());
		
		if(null==list){
			return null;
		}
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}



	@Override
	public PageInfo<User> getPageList(User po) {
		PageHelper.startPage(po.getPage(),po.getRows());
		return new PageInfo<User>(this.dao.getList(po));
	}
	@Override
	public PageInfo<User> getUserGroupPageList(User po) {
		PageHelper.startPage(po.getPage(),po.getRows());
		return new PageInfo<User>(this.dao.getList(po));
	}
	@Override
	public int save(User po) throws Exception {
		po.setId(Uuid.getUUID());
		String password=EncodeUtils.EncoderByMd5(SysConstant.initPassWord);
		po.setPassword(password);
		po.setStatus("1");
		po.setCreateTime(DateUtil.getTime());
//		po.setCreateBy(DateUtil.getTime());
		return this.dao.insertSelective(po);
	}
	
	public void setDao(UserMapper dao) {
		this.dao = dao;
	}

	@Override
	public int updatePw(User po) {
		String[] ids=po.getIds();
		if(null==ids){
			return 0;
		}
		int result=0;
		for (int i = 0; i < ids.length; i++) {
			po.setId(ids[i]);
			result+= this.dao.updateByPrimaryKeySelective(po);
		}
		return result;
	}

	@Override
	public List<User> getUserGroupList(User po) {
		return dao.getUserGroupList(po);
	}

	@Override
	public int getUserGroupCount(User po) {
		return dao.getUserGroupCount(po);
	}

	@Override
	public int addUserGroup(User po) {
		String[] ids=po.getIds();
		if(null==ids){
			return 0;
		}
		int result=0;
		GroupUser gu=null;
		for (int i = 0; i < ids.length; i++) {
			gu=new GroupUser();
			gu.setId(Uuid.getUUID());
			gu.setUserid(ids[i]);
			gu.setGroupid(po.getGroupId());
			result+= this.guDao.insertSelective(gu);
		}
		return result;
	}

	@Override
	public int deleteUserGroup(User po) {
		String[] ids=po.getIds();
		if(null==ids){
			return 0;
		}
		int result=0;
		GroupUser gu=null;
		for (int i = 0; i < ids.length; i++) {
			gu=new GroupUser();
			gu.setUserid(ids[i]);
			gu.setGroupid(po.getGroupId());
			result= this.guDao.delete(gu);
		}
		return result;
	}
	
	public void setGuDao(GroupUserMapper guDao) {
		this.guDao = guDao;
	}

//	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		l.debug("权限配置开始");
		if(null!=username&&SysConstant.SA.equals(EncodeUtils.EncoderByMd5(username))){
			return LoginUtils.getAdminUser();
		}
		User user=null;
		List<User> list= this.dao.getDetailByUsername(username);
		if(null!=list){
			if(list.size()==1){
				user=list.get(0);
			}else if(list.size()>=1){
				throw new AccessDeniedException("用户获取存在问题!!!,请联系管理员");
			}else{
				throw new AccessDeniedException("用户不存在!!!");
//				user= null;
			}
		}else{
			throw new AccessDeniedException("请重新登录系统!!!,或联系管理员!!!");
		}
		user.addAuthoritie("ROLE_USER");//赋予用户权限
		if("0".equals(user.getStatus())){
			user.setEnabled(false);
//			throw new AccessDeniedException("用户被禁用!!!");
		}
		
		List<Group> glist = LoginUtils.getUserGroup(user.getId());
		l.info("查询帐户{"+user.getUsername()+"}角色信息");
		//获取该用户的菜单信息
		boolean flag=true;
		if(null==glist||glist.size()==0){
			flag=false;
			l.info("帐户{"+user.getUsername()+"}没有角色信息");
		}
		String[] groupIds=new String[glist.size()];
		String[] groupNames=new String[glist.size()];
//		user.addAuthoritie("ROLE_USER");
		
		
		for (int i = 0; i < glist.size(); i++) {
			groupIds[i]="'"+glist.get(i).getId()+"'";
			groupNames[i]=glist.get(i).getGroupName();
			user.addAuthoritie(groupNames[i]);
			if(null!=groupNames[i]&&groupNames[i].indexOf("管理员")!=-1){
				user.addAuthoritie("ROLE_ADMIN");
			}
//			if(null!=groupNames[i]&&groupNames[i].indexOf("用户")!=-1){
//			}
		}
		l.info("查询帐户{"+user.getPassword()+"} 菜单信息");
		
		
		
		if(flag){
			List<Menu> mlist = LoginUtils.getUserMenu(groupIds);
			user.setMenus(mlist);
			user.setGroups(glist);
		}
		
		return user;
	}
}


