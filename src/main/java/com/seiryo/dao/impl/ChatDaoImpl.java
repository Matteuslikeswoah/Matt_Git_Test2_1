package com.seiryo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seiryo.dao.ChatDao;
import com.seiryo.entity.Chat;
import com.seiryo.util.DBUtil;

/**   
* 
* @ClassName: ChatDaoImpl.java
* @Description: 聊天数据实现层
* @author: matteus
* @date: 2024年10月17日
*
*/
public class ChatDaoImpl implements ChatDao {

	/**
	 * @MethodName: insertChat
	 * @Description: 插入聊天数据
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.ChatDao#insertChat(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean insertChat(String info, String time, String sender, String color) {
		String sql = "INSERT INTO CHAT (info, time, sender, color) VALUES (?, ?, ?, ?)";
		try {
			return	DBUtil.executeUpdate(sql, info, time, sender, color);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @MethodName: allChats
	 * @Description: 获取所有聊天记录
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.ChatDao#allChats()
	 */
	@Override
	public List<Chat> allChats() {
		List<Chat> allChats = new ArrayList<Chat>();
		String sql = "SELECT * FROM CHAT";
		ResultSet rs = null;
		
		try {
			
			rs = DBUtil.executeQuery(sql);
			
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setId(rs.getInt("id"));
				chat.setInfo(rs.getString("info"));
				chat.setTime(rs.getString("time"));
				chat.setSender(rs.getString("sender"));
				chat.setColor(rs.getString("color"));
				
				allChats.add(chat);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allChats;
	}

	/**
	 * @MethodName: deleteAllChats
	 * @Description: 删除聊天记录
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.dao.ChatDao#deleteAllChats()
	 */
	@Override
	public boolean deleteAllChats() {
	    String sql = "DELETE FROM CHAT";  

	    try {
	        return DBUtil.executeUpdate(sql);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
