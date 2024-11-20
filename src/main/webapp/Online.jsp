<%@page import="com.seiryo.entity.UserInfo"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>在线用户列表页面</title>
		<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		var start = function(){
			 $.ajax({
				 type:"post",
				 url:"UserInfoServlet",
				 data:{"flag":"online"},
				 dataType:"json",
				 success:function(userArray){
					 //将传递回来的数据转化成 JSON 数组
					 var userList = eval(JSON.parse(userArray));
					 $("#info").html("");
					 var info = "";
					 for(var i = 0; i < userList.length; i++){
						 if(userList[i].type == 0){
							 if(userList[i].online == 1){
								 info = info + "<div style='height:32px;width:120px'>";
								 if(userList[i].sex == '男'){
									 info = info + "<div style='width:32px;height:32px;background:url(images/g.png);float:left'></div>";
								 }else{
									 info = info + "<div style='width:32px;height:32px;background:url(images/b.png);float:left'></div>";
								 }
								 info = info + "<div style='font-size:14px;color:red'>" + userList[i].nickname + "</div>";
								 info = info + "</div>";
							 }
						 }
					 }
					 $("#info").append(info);
				 },
				 error:function(){
					 alert("网络有误！");
				 }
			 });
		}
	     window.setInterval(start,500);
		</script>
		<style>
		* { padding: 0px; margin: 0px; }
		</style>
	</head>
	<body id ="info">
	</body>
</html>
