<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				// 提交表单事件
				$('form').on('submit',function(event){
					// 获取输入框中的用户名和密码并去除空格
					var account = $('input[name="account"]').val().trim();
					var password = $('input[name="password"]').val().trim();
					
					// 检查用户名和密码是否为空
					if(account === "" || password === ""){
						alert("请填写用户名或密码！");
						event.preventDefault();
						return;
					}
					
					//使用 AJAX 提交表单
					$.ajax({
						type: "post",
						url: "UserInfoServlet",
						data: {"flag": "login", "account": account, "password": password},
						dataType: "json",
						success: function(returnValue){
							if(returnValue == 1){
								alert("登录成功！");
								window.location.href = "Chat.jsp";
							} else if(returnValue == 2){
								alert("请在上午八点至下午六点之间登录！！");
							}else if(returnValue == 3){
								alert("用户名或密码错误！请重试！");
							}
						},
						error: function(){
							alert("登录请求失败，请稍后重试。");
						}
					});
					
					event.preventDefault();
				});

				//点击注册按钮跳转到注册页面
				$('#registButton').on('click',function(event){
					event.preventDefault();
					window.location.href = 'Regist.jsp';
				});
			});
		</script>
	</head>
	<body>
		<div class="contain">
			<div class="login">
				<form>
					<ul>
						<li><input name="account" type="text" class="login-one" placeholder="用户名" /></li>
						<li><input name="password" type="password" class="login-two" placeholder="密码" /></li>
						<li>
							&nbsp;&nbsp;<input type="image" src="images/Login.gif" />&nbsp;&nbsp;
							<input type="image" src="images/rer.gif" id="registButton" />
						</li>
					</ul>
				</form>
			</div>
		</div>
	</body>
</html>