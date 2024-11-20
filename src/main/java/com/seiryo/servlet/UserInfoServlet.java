package com.seiryo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.seiryo.entity.Chat;
import com.seiryo.entity.UserInfo;
import com.seiryo.service.ChatService;
import com.seiryo.service.UserInfoService;
import com.seiryo.service.impl.ChatServiceImpl;
import com.seiryo.service.impl.UserInfoServiceImpl;
import com.seiryo.util.JSONUtil;
import com.seiryo.util.MD5UTIL;
import com.seiryo.util.TimeUtil;

/**   
* 
* @ClassName: UserInfoServlet.java
* @Description: 用户功能Servlet
* @author: matteus
* @date: 2024年10月17日
*
*/
public class UserInfoServlet extends HttpServlet {
	
	private UserInfoService userInfoService = new UserInfoServiceImpl();
	public static ConcurrentHashMap<String, HttpSession> onlineUsers = new ConcurrentHashMap<String, HttpSession>();
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
		
		//接受传入的flag进行后续处理
		String flag = req.getParameter("flag");
		
		if ("login".equals(flag)) {
			//login
			try {
				login(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("regist".equals(flag)) {
			//regist
			try {
				regist(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("online".equals(flag)) {
			try {
				online(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("logout".equals(flag)) {
			try {
				logout(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("clear".equals(flag)) {
			try {
				clear(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @MethodName: login
	 * @Description: 登录方法
	 * @author: matteus
	 * @param: 描述1描述
	 * @return: 返回结果描述
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		password = MD5UTIL.md5(password);
		Integer returnValue = 0;
		PrintWriter out = resp.getWriter();
		
		boolean loginSuccess = userInfoService.login(account, password);
		if (loginSuccess) {
			//获取用户类型
			int userType = userInfoService.getTypeByAccount(account);
			
			//如果是普通用户则判断是否在聊天室开放时间段内
			if (0 == userType) {
				//NormalUser
				boolean validTime = TimeUtil.isWithinLoginTime();
				//在开放时间内则进行下一步
				if (validTime) {
					//获取用户全部信息
					UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
					HttpSession session = req.getSession();
					session.setAttribute("account", userInfo.getAccount());
			        session.setAttribute("nickname", userInfo.getNickname());
			        session.setAttribute("type", userInfo.getType());
			        session.setAttribute("online", userInfo.getOnline());
			        session.setAttribute("sex", userInfo.getSex());
					
			        //讲用户账号作为Key，其他信息存入session并作为Value存入ConcurrentHashMap中
			        onlineUsers.put(account, session);
					//进入主界面
			        List<UserInfo> onlineUserList = userInfoService.onlineUserInfos();
 					session.setAttribute("onlineUsers", onlineUserList);
 					
 					//进入同时传递一个所有聊天记录
 					List<Chat> allChats = chatService.allChats();
 					session.setAttribute("allChats", allChats);
 					returnValue = 1;
				}else {
					returnValue = 2;
				}
				//管理员可以直接登录
			}else if (1 == userType) {
				//Admin
				UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
				HttpSession session = req.getSession();
				session.setAttribute("account", userInfo.getAccount());
		        session.setAttribute("nickname", userInfo.getNickname());
		        session.setAttribute("type", userInfo.getType());
		        session.setAttribute("online", userInfo.getOnline());
		        session.setAttribute("sex", userInfo.getSex());
				
		        onlineUsers.put(account, session);
				//进入主界面
		        List<UserInfo> onlineUserList = userInfoService.onlineUserInfos();
				session.setAttribute("onlineUsers", onlineUserList);
				
				List<Chat> allChats = chatService.allChats();
				session.setAttribute("allChats", allChats);
					
		        req.getRequestDispatcher("Chat.jsp").forward(req, resp);
			}
		}else {
			returnValue = 3;
		}
		out.println(returnValue);
		out.flush();
		out.close();
	}
	
	/**
	 * @MethodName: regist
	 * @Description: 注册方法
	 * @author: matteus
	 * @param: 描述1描述
	 * @return: 返回结果描述
	 */
	private void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String messages = "";
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String sex = req.getParameter("sex");
		String nickname = req.getParameter("nickname");
		
		password = MD5UTIL.md5(password); 
		
		boolean registSuccess = userInfoService.regist(account, password, sex, nickname);
		
		if (registSuccess) {
			messages = "恭喜您！注册成功！";
		}else {
			messages = "用户名重复或注册失败！";
		}
		messages =JSONObject.quote(messages);
		resp.getWriter().println(messages);
	}
	
	/**
	 * @MethodName: online
	 * @Description: 获取在线用户方法
	 * @author: matteus
	 * @param: 描述1描述
	 * @return: 返回结果描述
	 */
	private void online(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<UserInfo> onlineUserList = userInfoService.onlineUserInfos();
		String userArray = "";
		try {
			userArray = JSONUtil.toJSONString(onlineUserList);
			userArray = JSONObject.quote(userArray);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		resp.getWriter().println(userArray);
	}

	/**
	 * @MethodName: logout
	 * @Description: 退出登录方法
	 * @author: matteus
	 * @param: 描述1描述
	 * @return: 返回结果描述
	 */
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	    String account = (String) req.getSession().getAttribute("account");

	    //用户登出
	    userInfoService.logout(account);

	    //onlineUsersMap中移除当前用户
	    onlineUsers.remove(account);

	    //使当前会话失效
	    HttpSession session = req.getSession();
	    session.invalidate();

	    //重定向至登录页面
	    resp.sendRedirect("Login.jsp");
	}

	/**
	 * @MethodName: clear
	 * @Description: 清除所有聊天记录方法
	 * @author: matteus
	 * @param: 描述1描述
	 * @return: 返回结果描述
	 */
	private void clear(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		int userType = (int) req.getSession().getAttribute("type");
		
		//判断用户类型
		if (0 == userType) {
			PrintWriter out = resp.getWriter();
	        out.println("<script type='text/javascript'>");
	        out.println("alert('请联系管理员删除聊天记录！');");
	        out.println("window.history.back();"); 
	        out.println("</script>");
		}else if (1 == userType) {
			boolean deleteSuccess = chatService.deleteAllChats();
			if (deleteSuccess) {
				PrintWriter out = resp.getWriter();
		        out.println("<script type='text/javascript'>");
		        out.println("alert('删除成功！');");
		        out.println("window.history.back();"); 
		        out.println("</script>");
			}
		}
	}
}
