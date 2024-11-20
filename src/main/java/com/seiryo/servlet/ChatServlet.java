package com.seiryo.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seiryo.entity.Chat;
import com.seiryo.service.ChatService;
import com.seiryo.service.UserInfoService;
import com.seiryo.service.impl.ChatServiceImpl;
import com.seiryo.service.impl.UserInfoServiceImpl;
import com.seiryo.util.JSONUtil;

/**   
* 
* @ClassName: ChatServlet.java
* @Description: 聊天功能Servlet
* @author: matteus
* @date: 2024年10月18日
*
*/
public class ChatServlet extends HttpServlet {
	
	private UserInfoService userInfoService = new UserInfoServiceImpl();
	private ChatService chatService = new ChatServiceImpl();
	
	/**
	 * @MethodName: service
	 * @Description: 服务方法
	 * @author: matteus
	 * @date: 2024年10月18日
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//防止乱码处理
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//接受flag便于处理请求
		String flag = req.getParameter("flag");
		if ("send".equals(flag)) {
			try {
				send(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (flag == null || "show".equals(flag)) {
			try {
				showMessage(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @MethodName: send
	 * @Description: 发送信息功能
	 * @author: matteus
	 * @param: 描述1描述
	 * @return: 返回结果描述
	 */
	private void send(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//实例化Session
		HttpSession session = req.getSession();
		
		//获取当前时间并格式化
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String message = req.getParameter("msg");
		String color = req.getParameter("color");
		String time = dateFormat.format(currentDate);
		String sender = (String) session.getAttribute("account");
		
		boolean sendSuccess = chatService.insertChat(message, time, sender, color);
		
		if (sendSuccess) {
			System.out.println("Success!");
			showMessage(req, resp);
		}
	}
	
	/**
	 * @MethodName: showMessage
	 * @Description: 显示所有信息
	 * @author: matteus
	 * @param: 描述1描述
	 * @return: 返回结果描述
	 */
	private void showMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		// 获取所有聊天记录
	    List<Chat> allChats = chatService.allChats();
	    List<Map<String, Object>> chatData = new ArrayList<>();

	    for (Chat chat : allChats) {
	        Map<String, Object> chatMap = new HashMap<>();
	        chatMap.put("nickname", userInfoService.getNicknameByAccount(chat.getSender()));
	        chatMap.put("color", chat.getColor());
	        chatMap.put("info", chat.getInfo());
	        chatMap.put("time", chat.getTime());
	        chatData.add(chatMap);
	    }

	    // 将聊天记录转化为 JSON 字符串并写入响应
	    String jsonResponse = JSONUtil.toJSONString(chatData);
	    resp.getWriter().write(jsonResponse);
	}
}
