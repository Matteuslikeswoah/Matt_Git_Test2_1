package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.UserInfo;

/**   
* 
* @ClassName: UserInfoService.java
* @Description: 用户服务接口层
* @author: matteus
* @date: 2024年10月17日
*
*/
public interface UserInfoService {
	
	//获取所有用户
	List<UserInfo> getAllUsers();
	
	//登录
	boolean login(String account, String password);
	
	//根据账户获取用户类型
	int getTypeByAccount(String account);
	
	//注册
	boolean regist(String account, String password, String sex, String nickname);
	
	//根据账户获取用户所有信息
	UserInfo getUserInfoByAccount(String account);
	
	//获取所有在线用户
	List<UserInfo> onlineUserInfos();
	
	//退出登录
	void logout(String account);
	
	//根据账户获取昵称
	String getNicknameByAccount(String account);
}
