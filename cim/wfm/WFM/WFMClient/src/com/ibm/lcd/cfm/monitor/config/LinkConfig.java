package com.ibm.lcd.cfm.monitor.config;

public final class LinkConfig {

	public static final String ALARM_INFO_LINK = "Alarm Information";
	public static final String[] ALARM_NAME = { "Alarm Monitor", "Alarm History"};

	public static final String[] ALARM_LINK_PATH = { 
			"/ppt_allEqptAlarmList.do", 
			"/ppt_allEqptAlarmHis.do" };

	public static final String[] ALARM_LINK_ACTION_KEY = { "200", "201"};

	public static final String TARGET = "cfmlink";

	public static final String TARGET_BLANK = "_blank";
}
