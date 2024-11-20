package com.seiryo.entity;
/**   
* 
* @ClassName: UserInfo.java
* @Description: 用户信息实体类
* @author: matteus
* @date: 2024年10月17日
*
*/
public class UserInfo {
	
	//用户账号
	private String account;
	
	//用户密码
	private String password;
	
	//用户类型
	private Integer type;
	
	//用户在线状态
	private Integer online;
	
	//用户性别
	private String sex;
	
	//用户昵称
	private String nickname;

	//Getters,Setters
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @Constructor: UserInfo
	 * @Description: UserInfo有参构造函数
	 * @author: matteus
	 * @date: 2024年10月17日
	 */
	public UserInfo(String account, String password, Integer type, Integer online, String sex, String nickname) {
		super();
		this.account = account;
		this.password = password;
		this.type = type;
		this.online = online;
		this.sex = sex;
		this.nickname = nickname;
	}

	/**
	 * @Constructor: UserInfo
	 * @Description: UserInfo无参构造函数
	 * @author: matteus
	 * @date: 2024年10月17日
	 */
	public UserInfo() {
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
		return "UserInfo [account=" + account + ", password=" + password + ", type=" + type + ", online=" + online
				+ ", sex=" + sex + ", nickname=" + nickname + "]";
	}
}
