<%@ page import="java.time.LocalTime" %>
<%
    //固定允许聊天的时间范围
    LocalTime LOGIN_START_TIME = LocalTime.of(8, 0); 
    LocalTime LOGIN_END_TIME = LocalTime.of(18, 0);
    LocalTime currentTime = LocalTime.now();

    //获取用户类型
    Integer userType = (Integer) session.getAttribute("type");

    //检查当前时间是否在允许的聊天时间范围内
    if ((currentTime.isBefore(LOGIN_START_TIME) || currentTime.isAfter(LOGIN_END_TIME)) && (userType != null && userType == 0)) {
        //提示用户聊天室未开放，并跳转到登录页面
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>精彩人生在线聊天室</title>
		<link href="inc/chat.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
	    <script type="text/javascript" src="inc/jquery.js"></script>
	    <script type="text/javascript" src="inc/js.js"></script>
	    <script type="text/javascript" src="inc/chat.js"></script>
	</head>
	<body style="background:#ffffff; margin:10px auto; text-align:center;">
		<!-- ImageReady Slices (talk.psd) -->
		<table id="mainF" width="1014" border="0" cellpadding="0" cellspacing="0">
		    <tr>
		        <td colspan="3" style="background:url(img/images/talk_1.gif); text-align:left;">
		            <!--登录信息模版 开始-->
		            <div style="margin:5px 0 0 10px;">
		                <div style="float:right; margin-right:5px; width:46px; height:17px; background:url(images/close.png)" onclick="logout()"></div>
		            </div>
		            <!--登录信息模版 结束-->
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="24" alt="" /></td>
		    </tr>
		    <tr>
		        <td colspan="3" style="background:url(img/images/talk_2.gif); text-align:left;">
		            <!--广告模版 开始-->
		            <div style="margin:5px 0 0 10px; color:red; font-weight:bold;"></div> 
		            <!--广告模版 结束-->
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="20" alt="" /></td>
		    </tr>
		    <tr>
		        <td colspan="3" style="background:url(img/images/talk_3.gif); text-align:left;">
		            <!--功能按钮列表 开始-->
		            <div style="margin:5px 0 0 10px;">
		                <center>
		                    <span style="margin-top:20px; font-size:20px;">
 							<% 
                                // 获取用户类型
                                userType = (Integer) session.getAttribute("type");
                                String welcomeMessage = (userType != null && userType == 1) ? "管理员您好！" : "用户：" + session.getAttribute("nickname") + " 你好，欢迎使用精彩人生在线聊天室";
                            %>
                            <%= welcomeMessage %>		                   
                             </span>
		                </center>
		            </div> 
		            <!--功能按钮列表 结束-->
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="43" alt="" /></td>
		    </tr>
		    <tr>
		        <td colspan="2" rowspan="2" style="background:url(img/images/talk_4.gif); text-align:left;">
		            <!--聊天信息显示窗口 开始-->
		            <div id="content">
		           <!-- <iframe width="600px" height="10000px" frameborder="0" src="ChatInfo.jsp"></iframe>-->
		            </div>
		            <!--聊天信息显示窗口 结束-->
		        </td>
		        <td style="background:url(img/images/talk_5.gif); text-align:left;">
		            <!--广告模版 开始-->
		            <div style="margin:10px 0 0 10px; width:195px; line-height:150%;">
		                <iframe src="Notice.jsp" scrolling="no" frameborder="0" height="150px" width="200px"></iframe>
		            </div> 
		            <!--广告模版 结束-->
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="163" alt="" /></td>
		    </tr>
		    <tr>
		        <td rowspan="4" style="background:url(img/images/talk_6.gif); text-align:left;">
		            <!--在线用户列表开始-->
		            <div id="user_online">
		                <iframe width="170px" height="320px" frameborder="0" src="Online.jsp"></iframe>
		            </div>
		            <!--在线用户列表结束-->
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="290" alt="" /></td>
		    </tr>
		    <tr>
		        <td style="background:url(img/images/talk_7.gif); text-align:left;">
		            <!--信息内容选项 开始-->
		            <div id="user_box">&nbsp;	
		                <input type="hidden" name="sfcolor" />
		                <a href="#" onclick="showMenu(0,this);"><img id="talkcolor" src="img/color.gif" alt="选择聊天颜色" style="margin-top:1px;" /></a>
		                <input type="hidden" name="slist" />
		                <a href="#" onclick="showMenu(1,this);"><img id="talkface" src="img/face.gif" alt="选择聊天表情" style="margin-top:1px;" /></a>
		            </div>
		            <!--信息内容选项 结束-->
		        </td>
		        <td>
		            <a href="javascript:clearMsg()"><img src="img/images/clear.gif" width="76" height="22" alt="" /></a>
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="22" alt="" /></td>
		    </tr>
		    <tr>
		        <td colspan="2" style="background:url(img/images/talk_9.gif); text-align:left;">
		            <!--信息输入框 开始-->
		            <div id="talkcontent">
		                <input name="smess" type="text" style="height:20px; width:750px;" />
		            </div>
		            <!--信息输入框 结束-->
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="31" alt="" /></td>
		    </tr>
		    <tr>
		        <td rowspan="2" style="background:url(img/images/talk_10.gif); text-align:left;">
		            <img src="img/images/talk_10.gif" width="724" height="38" alt="" />
		        </td>
		        <td rowspan="2">
		            <!--信息发送按钮 开始-->
		            <a id="send" href="#" onclick="sendMessage(this);"><img src="img/images/talk_11.gif" width="76" height="38" alt="" /></a>
		            <!--信息发送按钮 结束-->
		        </td>
		        <td><img src="img/images/spacer.gif" width="1" height="35" alt="" /></td>
		    </tr>
		    <tr>
		        <td><img src="img/images/talk_12.gif" width="214" height="3" alt="" /></td>
		        <td><img src="img/images/spacer.gif" width="1" height="3" alt="" /></td>
		    </tr>
		</table>
		<!-- End ImageReady Slices -->
		
		<!-- 功能板块开始 -->
		<div id="editor_color_menu"></div>
		<!--表情模块开始-->
		<div id="editor_face_menu"></div>
		<!--表情模块结束-->
		<div id="stip">
		    <table border="0" cellpadding="0" cellspacing="0">
		        <tr>
		            <td><img src="img/images/2_1.gif" width="16" height="38" alt="" /></td>
		            <td style="background:url(img/images/2_2.gif) repeat-x middle; vertical-align:top; font-size:12px;">
		                <div id="tit" style="margin-top:6px;"></div>
		            </td>
		            <td><img src="img/images/2_3.gif" width="18" height="38" alt="" /></td>
		        </tr>
		    </table>
		</div>
		
		<div style="display:none;"></div>
		<!-- 功能板块结束 -->
		<div><iframe width="170px" height="300px" frameborder="0" src="Logout.jsp"></iframe></div>
	</body>
</html>