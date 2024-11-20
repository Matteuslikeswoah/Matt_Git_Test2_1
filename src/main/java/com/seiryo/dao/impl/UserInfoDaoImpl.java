package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.UserInfoDao;
import com.seiryo.entity.UserInfo;
import com.seiryo.util.DBUtil;

/**   
* 
* @ClassName: UserInfoDaoImpl.java
* @Description: 用户数据实现层
* @author: matteus
* @date: 2024年10月17日
*
*/
public class UserInfoDaoImpl implements UserInfoDao {

	/**
	 * @MethodName: getAllUsers
	 * @Description: 获取所有用户
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.UserInfoDao#getAllUsers()
	 */
	@Override
	public List<UserInfo> getAllUsers() {
		List<UserInfo> allUsers = new ArrayList<UserInfo>();
		ResultSet rs = null;
		String sql = "SELECT * FROM USERINFO";
		try {
			rs = DBUtil.executeQuery(sql);
			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setAccount(rs.getString("account"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setType(rs.getInt("type"));
				userInfo.setOnline(rs.getInt("online"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setNickname(rs.getString("nickname"));
				
				allUsers.add(userInfo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}

	/**
	 * @MethodName: getTypeByAccount
	 * @Description: 根据账户获取用户类型
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.UserInfoDao#getTypeByAccount(java.lang.String)
	 */
	@Override
	public int getTypeByAccount(String account) {
		String sql = "SELECT type FROM USERINFO WHERE account = ?";
		ResultSet rs = null;
		int userType = -1;
		try {
			rs = DBUtil.executeQuery(sql, account);
			
			if (rs.next()) {
				userType = rs.getInt("type");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userType;
	}

	/**
	 * @MethodName: regist
	 * @Description: 注册
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.UserInfoDao#regist(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean regist(String account, String password, String sex, String nickname) {
		String sql = "INSERT INTO USERINFO (account, password, type, online, sex, nickname) VALUES (?, ?, ?, ?, ?, ?)";
		
		int type = 0;
	    int online = 0;
	    
	    try {
	    	boolean result = DBUtil.executeUpdate(sql, account, password, type, online, sex, nickname);
	    	return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @MethodName: getUserInfoByAccount
	 * @Description: 根据账户获取用户所有信息
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.UserInfoDao#getUserInfoByAccount(java.lang.String)
	 */
	@Override
	public UserInfo getUserInfoByAccount(String account) {
	    UserInfo userInfo = null;  
	    String sql = "SELECT * FROM USERINFO WHERE account = ?";
	    ResultSet rs = null;
	    
	    try {
	        rs = DBUtil.executeQuery(sql, account);
	        
	        if (rs.next()) {
	            userInfo = new UserInfo();  
	            userInfo.setAccount(account);
	            userInfo.setPassword(rs.getString("password"));
	            userInfo.setType(rs.getInt("type"));
	            userInfo.setOnline(rs.getInt("online"));
	            userInfo.setSex(rs.getString("sex"));
	            userInfo.setNickname(rs.getString("nickname"));
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally { 
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return userInfo;  
	}


	/**
	 * @MethodName: onlineUserInfos
	 * @Description: 获取所有在线用户
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.UserInfoDao#onlineUserInfos()
	 */
	@Override
	public List<UserInfo> onlineUserInfos() {
		List<UserInfo> onlineUsers = new ArrayList<UserInfo>();
		String sql = "SELECT * FROM USERINFO WHERE online = 1";
		ResultSet rs = null;
		
		try {
			rs = DBUtil.executeQuery(sql);
			
			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setAccount(rs.getString("account"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setType(rs.getInt("type"));
				userInfo.setOnline(rs.getInt("online"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setNickname(rs.getString("nickname"));	
				
				onlineUsers.add(userInfo);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return onlineUsers;
	}

	/**
	 * @MethodName: updateUserOnlineStatus
	 * @Description: 更新用户在线状态
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.UserInfoDao#updateUserOnlineStatus(java.lang.String, int)
	 */
	@Override
	public void updateUserOnlineStatus(String account, int onlineStatus) {
		String sql = "UPDATE USERINFO SET online = ? WHERE account = ?";
		try {
			DBUtil.executeUpdate(sql, onlineStatus, account);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @MethodName: getNicknameByAccount
	 * @Description: 根据账户获取昵称
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.UserInfoDao#getNicknameByAccount(java.lang.String)
	 */
	@Override
	public String getNicknameByAccount(String account) {
		String sql = "SELECT nickname FROM USERINFO WHERE account = ?";
		ResultSet rs = null;
		String nickname = " ";
		try {
			rs = DBUtil.executeQuery(sql, account);
			
			if (rs.next()) {
				nickname = rs.getString("nickname");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nickname;
	}
}
