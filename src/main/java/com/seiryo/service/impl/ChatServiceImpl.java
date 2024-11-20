package com.seiryo.service.impl;

import java.util.List;

import com.seiryo.dao.ChatDao;
import com.seiryo.dao.impl.ChatDaoImpl;
import com.seiryo.entity.Chat;
import com.seiryo.service.ChatService;

/**   
* 
* @ClassName: ChatServiceImpl.java
* @Description: 聊天服务实现层
* @author: matteus
* @date: 2024年10月17日
*
*/
public class ChatServiceImpl implements ChatService {

	private ChatDao chatDao = new ChatDaoImpl();
	
	/**
	 * @MethodName: insertChat
	 * @Description: 插入聊天数据
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.ChatService#insertChat(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean insertChat(String info, String time, String sender, String color) {
		return chatDao.insertChat(info, time, sender, color);
	}

	/**
	 * @MethodName: allChats
	 * @Description: 查询所有聊天记录
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.ChatService#allChats()
	 */
	@Override
	public List<Chat> allChats() {
		return chatDao.allChats();
	}

	/**
	 * @MethodName: deleteAllChats
	 * @Description: 删除所有聊天记录
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see com.seiryo.service.ChatService#deleteAllChats()
	 */
	@Override
	public boolean deleteAllChats() {
		return chatDao.deleteAllChats();
	}

}
