package com.seiryo.util;

import java.time.LocalTime;

/**   
* 
* @ClassName: TimeUtil.java
* @Description: 时间工具类
* @author: matteus
* @date: 2024年10月17日
*
*/
public class TimeUtil {
	//固定允许登录的时间范围：早上8点到下午6点
	public static final LocalTime LOGIN_START_TIME = LocalTime.of(8, 0); 
	public static final LocalTime LOGIN_END_TIME = LocalTime.of(18, 0); 
    
	public static boolean isWithinLoginTime() {
		LocalTime currentTime = LocalTime.now();
		if (currentTime.isAfter(LOGIN_START_TIME) && currentTime.isBefore(LOGIN_END_TIME)) {
            return true; 
        } else {
            return false; 
        }
	}
}
