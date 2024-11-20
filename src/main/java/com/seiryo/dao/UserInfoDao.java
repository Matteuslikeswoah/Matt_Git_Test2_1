package com.seiryo.dao;
/**   
* 
* @ClassName: UserInfoDao.java
* @Description: 用户数据接口层
* @author: matteus
* @date: 2024年10月17日
*
*/

import java.util.List;

import com.seiryo.entity.UserInfo;

public interface UserInfoDao {
	
	//获取所有用户
	List<UserInfo> getAllUsers();
	
	//根据账户获取用户类型
	int getTypeByAccount(String account);
	
	//注册
	boolean regist(String account, String password, String sex, String nickname);
	
	//根据账户获取用户所有信息
	UserInfo getUserInfoByAccount(String account);
	
	//获取所有在线用户
	List<UserInfo> onlineUserInfos();
	
	//更新用户在线状态
	void updateUserOnlineStatus(String account, int onlineStatus);
	
	//根据账户获取昵称
	String getNicknameByAccount(String account);
}
