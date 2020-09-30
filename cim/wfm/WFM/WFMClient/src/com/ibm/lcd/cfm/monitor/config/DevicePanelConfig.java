
package com.ibm.lcd.cfm.monitor.config;


import java.awt.Color;
import java.awt.Font;

public final class DevicePanelConfig {
    final String CLASS_NAME = "DevicePanelConfig";

    public final static String OnlineInstallationNumber = MonitorConfig.getMessage("device.stat.OnlineInstallationNumber");

    public final static String AllDeviceNumber          = MonitorConfig.getMessage("device.stat.AllDeviceNumber");

    public final static String WorkingLotNumber         = MonitorConfig.getMessage("device.stat.WorkingLotNumber");

    public final static String DeliveringLotNumber      = MonitorConfig.getMessage("device.stat.DeliveringLotNumber");

    public final static String NowWorkingLotNumber      = MonitorConfig.getMessage("device.stat.NowWorkingLotNumber");

    public final static String NowDeliveringNumber      = MonitorConfig.getMessage("device.stat.NowDeliveringNumber");

    public final static String TodayWorkingLotNumber    = MonitorConfig.getMessage("device.stat.TodayWorkingLotNumber");

    public final static String TodayDeliveringNumber    = MonitorConfig.getMessage("device.stat.TodayDeliveringNumber");

    public final static String DayWorkingLotNumber      = MonitorConfig.getMessage("device.stat.DayWorkingLotNumber");

    public final static String DayDeliveringNumber      = MonitorConfig.getMessage("device.stat.DayDeliveringNumber");

    public final static String TODAY_DATE               = MonitorConfig.getMessage("device.stat.TODAY_DATE");

    public final static String ECS                      = MonitorConfig.getMessage("device.stat.ECS");

    public final static String TM                       = MonitorConfig.getMessage("device.stat.TM");

    public final static int DeviceTableElementNumber   = 3;

    public final static int DeviceRow                  = 1;

    public final static int DeviceTableElementNumber1  = 13;

    public final static int DeviceRow1                 = 2;

    public final static int TextBoxSize                = 26;

    public final static int TextBoxSizeR               = 26;

    public final static int FontSize                   = 10;

    public final static String[] logicalFontName       = { "Dialog", "DialogInput", "Monospaced", "Serif" };

    public final static String keyOIN                  = "OnlineInstallationNumber";

    public final static String keyADN                  = "AllDeviceNumber";

    public final static String keyWLN                  = "WorkingLotNumber";

    public final static String keyDLN                  = "DeliveringLotNumber";

    public final static String keyNWLN                 = "TodayWorkingLotNumber";

    public final static String keyNDN                  = "TodayDeliveringNumber";

    public final static String keyDWLN                 = "DayWorkingLotNumber";

    public final static String keyDDN                  = "DayDeliveringNumber";


    public final static String keyDG1                  = "DeviceGraph1";

    public final static String keyDG2                  = "DeviceGraph2";

    public final static int CANVAS_DIMENSION_W        = 215;

    public final static int CANVAS_DIMENSION_H        = 165;

    public final static int GRAPH_OFFSET              = 40;

    public final static int GRAPH_HEIGHT              = 112;

    public final static int MAX_VALUE                 = 100;

    public final static int GRAPH_DEVIDE              = 4;

    public final static int GRAPH_WIDTH               = 205;

    public final static int GRAPH_WIDTH_DETAIL_E      = 1;

	public final static String HEADER_NAME_E          = "Equipment Process Count Change";
	public final static String HEADER_NAME_COM1       = "(Past";

	public final static String HEADER_NAME_COM2       = "H)";
	
    public final static int HEADER_WIDTH              = 7;

    public final static int HEADER_HEIGHT             = 19;

    public final static String SUB_STATUS_NAME_E       = "";

    public final static int SUB_STATUS_WIDTH          = 206;

    public final static int SUB_STATUS_HEIGHT         = 36;

    public final static Font FONT_H                    = new Font("Courier", Font.PLAIN, 12);

    public final static Font FONT_S                    = new Font("Courier", Font.PLAIN, 9);

    public final static Font FONT_Y                    = new Font("Courier", Font.PLAIN, 11);

    public final static Font FONT_X                    = new Font("Courier", Font.PLAIN, 9);

    public final static Font FONT_B                    = new Font("Courier", Font.PLAIN, 9);

    public final static Color FONT_COLOR_H             = Color.BLUE;

    public final static Color FONT_COLOR_S             = Color.WHITE;

    public final static Color FONT_COLOR_Y             = Color.BLACK;

    public final static Color FONT_COLOR_X             = Color.BLACK;

    public final static Color FONT_COLOR_B             = new Color(255, 165, 0)/*Color.RED*/;

    public final static boolean IS_SCALE_X            = true;
/*
    public static final String[] time00    = {"", "", "02", "", "04", "", "06", "", "08", "", "10", "", "12", "", "14", "", "16", "", "18", "", "20", "", "22", "", "00"};
    public static final String[] time00_48 = {"", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00"};

    public static final String[] time01    = {"", "", "03", "", "05", "", "07", "", "09", "", "11", "", "13", "", "15", "", "17", "", "19", "", "21", "", "23", "", "01"};
    public static final String[] time01_48 = {"", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01" ,"", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01"};

    public static final String[] time02    = {"", "", "04", "", "06", "", "08", "", "10", "", "12", "", "14", "", "16", "", "18", "", "20", "", "22", "", "00", "", "02"};
    public static final String[] time02_48 = {"", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02"};

    public static final String[] time03    = {"", "", "05", "", "07", "", "09", "", "11", "", "13", "", "15", "", "17", "", "19", "", "21", "", "23", "", "01", "", "03"};
    public static final String[] time03_48 = {"", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03"};

    public static final String[] time04    = {"", "", "06", "", "08", "", "10", "", "12", "", "14", "", "16", "", "18", "", "20", "", "22", "", "00", "", "02", "", "04"};
    public static final String[] time04_48 = {"", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04"};
    public static final String[] time05    = {"", "", "07", "", "09", "", "11", "", "13", "", "15", "", "17", "", "19", "", "21", "", "23", "", "01", "", "03", "", "05"};
    public static final String[] time05_48 = {"", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05"};

    public static final String[] time06    = {"", "", "08", "", "10", "", "12", "", "14", "", "16", "", "18", "", "20", "", "22", "", "00", "", "02", "", "04", "", "06"};
    public static final String[] time06_48 = {"", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06"};

    public static final String[] time07    = {"", "", "09", "", "11", "", "13", "", "15", "", "17", "", "19", "", "21", "", "23", "", "01", "", "03", "", "05", "", "07"};
    public static final String[] time07_48 = {"", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07"};

    public static final String[] time08    = {"", "", "10", "", "12", "", "14", "", "16", "", "18", "", "20", "", "22", "", "00", "", "02", "", "04", "", "06", "", "08"};
    public static final String[] time08_48 = {"", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08"};

    public static final String[] time09    = {"", "", "11", "", "13", "", "15", "", "17", "", "19", "", "21", "", "23", "", "01", "", "03", "", "05", "", "07", "", "09"};
    public static final String[] time09_48 = {"", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09"};

    public static final String[] time10    = {"", "", "12", "", "14", "", "16", "", "18", "", "20", "", "22", "", "00", "", "02", "", "04", "", "06", "", "08", "", "10"};
    public static final String[] time10_48 = {"", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10"};

    public static final String[] time11    = {"", "", "13", "", "15", "", "17", "", "19", "", "21", "", "23", "", "01", "", "03", "", "05", "", "07", "", "09", "", "11"};
    public static final String[] time11_48 = {"", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11"};

    public static final String[] time12    = {"", "", "14", "", "16", "", "18", "", "20", "", "22", "", "00", "", "02", "", "04", "", "06", "", "08", "", "10", "", "12"};
    public static final String[] time12_48 = {"", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12"};

    public static final String[] time13    = {"", "", "15", "", "17", "", "19", "", "21", "", "23", "", "01", "", "03", "", "05", "", "07", "", "09", "", "11", "", "13"};
    public static final String[] time13_48 = {"", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13"};

    public static final String[] time14    = {"", "", "16", "", "18", "", "20", "", "22", "", "00", "", "02", "", "04", "", "06", "", "08", "", "10", "", "12", "", "14"};
    public static final String[] time14_48 = {"", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14"};

    public static final String[] time15    = {"", "", "17", "", "19", "", "21", "", "23", "", "01", "", "03", "", "05", "", "07", "", "09", "", "11", "", "13", "", "15"};
    public static final String[] time15_48 = {"", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15"};

    public static final String[] time16    = {"", "", "18", "", "20", "", "22", "", "00", "", "02", "", "04", "", "06", "", "08", "", "10", "", "12", "", "14", "", "16"};
    public static final String[] time16_48 = {"", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16"};

    public static final String[] time17    = {"", "", "19", "", "21", "", "23", "", "01", "", "03", "", "05", "", "07", "", "09", "", "11", "", "13", "", "15", "", "17"};
    public static final String[] time17_48 = {"", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17"};

    public static final String[] time18    = {"", "", "20", "", "22", "", "00", "", "02", "", "04", "", "06", "", "08", "", "10", "", "12", "", "14", "", "16", "", "18"};
    public static final String[] time18_48 = {"", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18"};

    public static final String[] time19    = {"", "", "21", "", "23", "", "01", "", "03", "", "05", "", "07", "", "09", "", "11", "", "13", "", "15", "", "17", "", "19"};
    public static final String[] time19_48 = {"", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19"};

    public static final String[] time20    = {"", "", "22", "", "00", "", "02", "", "04", "", "06", "", "08", "", "10", "", "12", "", "14", "", "16", "", "18", "", "20"};
    public static final String[] time20_48 = {"", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20", "", "", "", "00", "", "", "", "04", "", "", "", "08", "", "", "", "12", "", "", "", "16", "", "", "", "20"};

    public static final String[] time21    = {"", "", "23", "", "01", "", "03", "", "05", "", "07", "", "09", "", "11", "", "13", "", "15", "", "17", "", "19", "", "21"};
    public static final String[] time21_48 = {"", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21", "", "", "", "01", "", "", "", "05", "", "", "", "09", "", "", "", "13", "", "", "", "17", "", "", "", "21"};

    public static final String[] time22    = {"", "", "00", "", "02", "", "04", "", "06", "", "08", "", "10", "", "12", "", "14", "", "16", "", "18", "", "20", "", "22"};
    public static final String[] time22_48 = {"", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22", "", "", "", "02", "", "", "", "06", "", "", "", "10", "", "", "", "14", "", "", "", "18", "", "", "", "22"};

    public static final String[] time23    = {"", "", "01", "", "03", "", "05", "", "07", "", "09", "", "11", "", "13", "", "15", "", "17", "", "19", "", "21", "", "23"};
    public static final String[] time23_48 = {"", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23", "", "", "", "03", "", "", "", "07", "", "", "", "11", "", "", "", "15", "", "", "", "19", "", "", "", "23"};

    public static final String[] timeNone    = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    public static final String[] timeNone_48 = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

*/
	public final static String HEADER_NAME_D            = "Transfer Change";
    public final static String SUB_STATUS_NAME_D        = "";

    public final static int GRAPH_WIDTH_DETAIL_D       = 0;
}
