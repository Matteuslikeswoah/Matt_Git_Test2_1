
var lastMsg="";
var colors=["00","33","66","99","CC","FF"];
$(document).ready(function(){
	init();
});

$(document).click(function(){
		window.document.title="精彩人生在线实况直播间！"
});

$(document).keydown(function(e){
		e=e?e:event;
		if(e.keyCode==13){
			sendMessage($$("send"));
		}
});
//初始化表情
function init(){
	for(var i=0;i<=84;i++){
		$("#editor_face_menu").append("<img src=\"face/" + i + ".gif\" alt=\"\" onclick=\"setFace(this);\" /> ");
	} 
	
	for(var i=0;i<colors.length;i++){
		for(var j=0;j<colors.length;j++){
			for(var k=0;k<colors.length;k++){
				co="#" + colors[i] + colors[j] + colors[k];
                $("#editor_color_menu").append("<input type=\"button\" style=\"border:0px;margin:1px;width:10px;height:10px;background-color:" + co + "\" onclick=\"setColor(this);\" />");
            }
        }
    }
}



$(document).ready(function() {
    loadChatInfo();
    setInterval(loadChatInfo, 30000); // 每10秒刷新一次
});

function loadChatInfo() {
    $("#content").load("ChatInfo.jsp");
}


//发送信息
function sendMessage(obj){
	var fcolor=$("input[@name=sfcolor]").val();
	var fface=$("input[@name=slist]").val();
	//接受用户发送的聊天内容
	var tomsg=$("input[@name=smess]").val();
	if(tomsg=="" && fface==""){showStip(obj,"表情和文字至少二选一哦！",10,-30);return false;}
    if(lastMsg==""){
    	
    	lastMsg=tomsg;
    }
    if(lastMsg.length>100){showStip(obj,"聊天内容不能多余100字！",10,-30);return false;}
    window.location.href="ChatServlet?flag=send&msg="+lastMsg+"&color="+fcolor;
	loadChatInfo();
}      


//设置对话对象
function setTo(user){
	$("input[@name=stowho]").val(user);
}

//菜单
function showMenu(indexw,obj){
    var point=absolutePoint(obj);
    var ob=((indexw==0)?'#editor_color_menu':'#editor_face_menu');
    var cb=((indexw==0)?'#editor_face_menu':'#editor_color_menu');
    $(ob).css({'border':'1px #dddddd solid','background-color':'#ffffff'});
    if($(ob).is(':visible')){
        $(ob).fadeOut();
    }else{
        $(cb).fadeOut();
        $(ob).fadeIn();
        $(ob).css({"display":"block","left":point.x,"top":(point.y-parseInt($(ob).css('height'))-10)}); 
    }
}

//设置颜色
function setColor(obj){
    $('input[@name=sfcolor]').val($(obj).css('background-color'));
    $('#editor_color_menu').fadeOut();
    $("input[@name=smess]").focus();
}

//设置表情
function setFace(obj){
    $('input[@name=slist]').val($(obj).attr('src'));
    $('#editor_face_menu').fadeOut();
    $("input[@name=smess]").focus();
    showStip("talkface","已选择表情:<img src='" + $(obj).attr('src') + "' style='vertical-align:top;' alt='表情' />",0,-30);
    lastMsg=lastMsg+$("input[@name=smess]").val()+"<img src='"+$(obj).attr('src')+"'>";
}


//显示小提示
function showStip(target,txt,x,y){
  var obj=typeof target=="string" ?$$(target):target
  var point=absolutePoint(obj);
  $("#tit").html(txt);
  $("#stip").click(function(){$(this).fadeOut()});
  $("#stip").css({"cursor":"pointer","top":point.y+y,"left":point.x+x,"background":"url(img/images/2_2.gif) repeat-x"}).fadeIn("fast");
}



function ask(str){
    return window.confirm(str)?true:false;
}

function absolutePoint(element) {
    var result = { x: element.offsetLeft, y: element.offsetTop };
    element = element.offsetParent;
    while (element) {
        result.x += element.offsetLeft;
        result.y += element.offsetTop;
        element = element.offsetParent;
    }
    return result;
}
//用户退出
function  logout(){
	 var reult=ask("是否要退出直播间?");
	 if(reult){
		 window.location.href="UserInfoServlet?flag=logout";
	 }
}

function clearMsg(){
	
	 var reult=ask("是否要删除所有用户聊天记录?");
	 if(reult){
		 window.location.href="UserInfoServlet?flag=clear";
	 }
	
}