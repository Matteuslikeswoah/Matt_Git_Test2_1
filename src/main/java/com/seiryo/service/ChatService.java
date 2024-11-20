package com.seiryo.service;

import java.util.List;

import com.seiryo.entity.Chat;

/**   
* 
* @ClassName: ChatService.java
* @Description: 聊天服务接口层
* @author: matteus
* @date: 2024年10月17日
*
*/
public interface ChatService {
	
	//插入聊天数据
	boolean insertChat(String info, String time, String sender, String color);
	
	//查询所有聊天记录
	List<Chat> allChats();
	
	//删除所有聊天记录
	boolean deleteAllChats();

}
