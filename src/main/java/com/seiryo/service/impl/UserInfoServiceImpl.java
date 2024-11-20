package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.UserInfoDao;
import com.seiryo.dao.impl.UserInfoDaoImpl;
import com.seiryo.entity.UserInfo;
import com.seiryo.service.UserInfoService;

/**   
* 
* @ClassName: UserInfoServiceImpl.java
* @Description: 用户服务实现层
* @author: matteus
* @date: 2024年10月17日
*
*/
public class UserInfoServiceImpl implements UserInfoService {

	private UserInfoDao userInfoDao = new UserInfoDaoImpl();
	
	/**
	 * @MethodName: getAllUsers
	 * @Description: 获取所有用户
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#getAllUsers()
	 */
	@Override
	public List<UserInfo> getAllUsers() {
		return userInfoDao.getAllUsers();
	}

	/**
	 * @MethodName: login
	 * @Description: 登录
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String account, String password) {
		List<UserInfo> allUsers = userInfoDao.getAllUsers();
		
		for (UserInfo userInfo : allUsers) {
			if (account.equals(userInfo.getAccount())) {
				if (password.equals(userInfo.getPassword())) {
					//若账号密码正确则登录成功，将在线状态设置为1
					userInfoDao.updateUserOnlineStatus(account, 1);
					userInfo.setOnline(1);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @MethodName: getTypeByAccount
	 * @Description: 根据账户获取用户类型
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#getTypeByAccount(java.lang.String)
	 */
	@Override
	public int getTypeByAccount(String account) {
		return userInfoDao.getTypeByAccount(account);
	}

	/**
	 * @MethodName: regist
	 * @Description: 注册
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#regist(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean regist(String account, String password, String sex, String nickname) {
		List<UserInfo> allUsers = userInfoDao.getAllUsers();
		
		for (UserInfo userInfo : allUsers) {
			if (account.equals(userInfo.getAccount())) {
				return false;
			}
		}
		return userInfoDao.regist(account, password, sex, nickname);
	}

	/**
	 * @MethodName: getUserInfoByAccount
	 * @Description: 根据账户获取用户所有信息
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#getUserInfoByAccount(java.lang.String)
	 */
	@Override
	public UserInfo getUserInfoByAccount(String account) {
		return userInfoDao.getUserInfoByAccount(account);
	}

	/**
	 * @MethodName: onlineUserInfos
	 * @Description: 获取所有在线用户
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#onlineUserInfos()
	 */
	@Override
	public List<UserInfo> onlineUserInfos() {
		return userInfoDao.onlineUserInfos();
	}

	/**
	 * @MethodName: logout
	 * @Description: 退出登录
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#logout(java.lang.String)
	 */
	@Override
	public void logout(String account) {
		UserInfo userInfo = userInfoDao.getUserInfoByAccount(account);
		userInfo.setOnline(0);
		userInfoDao.updateUserOnlineStatus(account, 0);
	}

	/**
	 * @MethodName: getNicknameByAccount
	 * @Description: 根据账户获取昵称
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.UserInfoService#getNicknameByAccount(java.lang.String)
	 */
	@Override
	public String getNicknameByAccount(String account) {
		return userInfoDao.getNicknameByAccount(account);
	}
}
