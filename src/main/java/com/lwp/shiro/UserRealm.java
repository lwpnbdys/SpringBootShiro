package com.lwp.shiro;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.lwp.pojo.User;
import com.lwp.service.UserService;

public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		//给资源授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//info.addStringPermission("user:add");
		//到数据库查询当前登录用户的权限
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		User dbUser = userService.findUserByid(user.getId());
		
		List<String> list = new ArrayList<String>();
		list.add(dbUser.getPerms());
		list.add("user:update");
		info.addStringPermissions(list);
		return info;
	}
	
	//执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken tokens) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		//1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken)tokens;
		User user = userService.findUserByName(token.getUsername());
		//编写shiro判断逻辑，判断用户名和密码
		if(user == null) {
			//用户不存在
			return null;
		}
		
		//2.判断密码
		
		return new SimpleAuthenticationInfo(user,user.getPassword(),"");
	}
	
}
