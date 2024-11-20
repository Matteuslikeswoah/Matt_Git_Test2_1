package com.seiryo.entity;
/**   
* 
* @ClassName: Chat.java
* @Description: 聊天相关实体类
* @author: matteus
* @date: 2024年10月17日
*
*/
public class Chat {

	//信息ID
	private Integer id;
	
	//信息内容
	private String info;
	
	//发送时间
	private String time;
	
	//发送人
	private String sender;
	
	//发送信息颜色
	private String color;

	//Getters,Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @Constructor: Chat
	 * @Description: Chat有参构造函数
	 * @author: matteus
	 * @date: 2024年10月17日
	 */
	public Chat(Integer id, String info, String time, String sender, String color) {
		super();
		this.id = id;
		this.info = info;
		this.time = time;
		this.sender = sender;
		this.color = color;
	}

	/**
	 * @Constructor: Chat
	 * @Description: Chat无参构造函数
	 * @author: matteus
	 * @date: 2024年10月17日
	 */
	public Chat() {
		super();
	}

	/**
	 * @MethodName: toString
	 * @Description: toString方法
	 * @author: matteus
	 * @date: 2024年10月17日
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chat [id=" + id + ", info=" + info + ", time=" + time + ", sender=" + sender + ", color=" + color + "]";
	}
}
