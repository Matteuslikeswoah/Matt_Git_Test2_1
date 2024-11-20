package com.seiryo.dao;
/**   
* 
* @ClassName: ChatDao.java
* @Description: 聊天数据接口
* @author: matteus
* @date: 2024年10月17日
*
*/

import java.util.List;

import com.seiryo.entity.Chat;

public interface ChatDao {
	
	//插入聊天数据
	boolean insertChat(String info, String time, String sender, String color);
	
	//获取所有聊天记录
	List<Chat> allChats();

	//删除聊天记录
	boolean deleteAllChats();
}
