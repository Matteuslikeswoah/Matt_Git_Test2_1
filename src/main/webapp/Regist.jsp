<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册页面</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script  src="js/jquery-1.11.0.min.js"></script>
		<script>
		      $(function(){
		            $("#img").click(function(){
		                // 获取输入的各项值
		                var $account = $("#account").val();
		                var $password = $("#password").val();
		                var $sex = $("#sex").val();
		                var $nickname = $("#nickname").val();
		                
		                // 验证账号、密码和性别的格式
		                if ($account.length < 5 || $account.length > 12) {
		                    alert("账号长度在5-12之间");
		                    return false;
		                } else if ($password.length < 5 || $password.length > 12) {
		                    alert("密码长度在5-12之间");
		                    return false;
		                } else if ($sex !== "男" && $sex !== "女") {
		                    alert("性别只能是男或女");
		                    return false;
		                }

		                //发送AJAX请求
		                $.ajax({
		                	type: "post",
		                	url: "UserInfoServlet",
		                	data: {
		                		  "flag": "regist",
		                		  "account": $account,
		                		  "password": $password,
		                		  "sex": $sex,
		                		  "nickname": $nickname
		                		},
		                	dataType: "JSON",
		                	success: function(returnValue) {
		                		var cleanReturnValue = returnValue.replace(/(^")|("$)/g, '');
		                		alert(cleanReturnValue);
		                		if (cleanReturnValue === "恭喜您！注册成功！") {
		                			window.location.href = "Login.jsp";
		                		}
		                	},
		                	error: function() {
		                		alert("您的网络有误！请稍候再试！");
		                	}
		                });
		            });  
		      });
		</script>
	</head>
	<body>
			<div class="reg">
				<div class="reg-b">
				  <table width="65%">
				    <tr>
				      <td width="26%" class="text1">用户名：</td>
				      <td width="74%">
				        <input name="account" type="text" class="box" id="account" size="26"  />
				    </td>
				    </tr>
				    <tr>
				      <td class="text1">密码：</td>
				      <td><input name="password" type="password" class="box" id="password" size="26" /></td>
				    </tr>
				    <tr>
				      <td class="text1">性别：</td>
				      <td><input name="sex" type="text" class="box" id="sex" size="26"  /></td>
				    </tr>
				    <tr>
				      <td class="text1">昵称：</td>
				      <td><input name="nickname" type="text" class="box" id="nickname" size="26" /></td>
				    </tr>
				    <tr>
				      <td>&nbsp;</td>
				      <td><input name="input" type="image"  id="img" src="images/confirm.jpg" /></td>
				    </tr>
				  </table>
				</div>
			</div>
	</body>
</html>