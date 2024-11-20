<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>显示聊天内容页面</title>
    <script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    function loadChatContent() {
        $.ajax({
            type: "post",
            url: "ChatServlet",
            data: { "flag": "show" },
            dataType: "json",
            success: function(chatList) {
                var chatHtml = "";
                if (chatList.length === 0) {
                    chatHtml = "<div style='font-size:14px;color:#666;'>当前无聊天记录，请开始聊天吧！</div>";
                } else {
                    for (var i = 0; i < chatList.length; i++) {
                        var chat = chatList[i];
                        chatHtml += "<div style='font-size:14px;color:#666;'>";
                        chatHtml += chat.nickname + "说：";
                        chatHtml += "<span style='color:" + chat.color + "'>" + chat.info + "</span>&nbsp;&nbsp;";
                        chatHtml += chat.time + "</div>";
                    }
                }
                $("#chatContent").html(chatHtml);  //更新聊天记录内容
            },
            error: function() {
                alert("加载聊天记录失败，请检查网络！");
            }
        });
    }

        //每10秒自动刷新聊天内容
        window.setInterval(loadChatContent, 10000);

        //页面加载完成后首次加载聊天内容
        $(document).ready(loadChatContent);
    </script>
</head>
<body>
    <!-- 用于显示聊天记录的区域 -->
    <div id="chatContent"></div>
</body>
</html>