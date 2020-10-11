package com.ibm.lcd.cfm.monitor.status;

import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JPanel;

import com.ibm.lcd.cfm.monitor.config.DevicePanelConfig;
import com.ibm.lcd.cfm.monitor.util.GraphMaker4;
import com.ibm.lcd.cfm.monitor.util.GraphMakerCommonDataSet;

public class JSPHisPanel extends Applet {

	private static final long serialVersionUID = 4681940535029290516L;

	String[] data_labelX;
	String[] data_labelX_Sub;
	int disphourlen;
	int graphtype;
	String groupid;

	String labelMin = "m";
	private final int iMinDispValue = 30;

	public void paint(Graphics g) {

		JPanel pl = new JPanel();
		pl.setLayout(new GridLayout(1, 1));

		int idlength = Integer.parseInt(getParameter("idlength"));
		disphourlen = Integer.parseInt(getParameter("disphourlen"));
		graphtype = Integer.parseInt(getParameter("graphtype"));
		String nowsqltime = getParameter("nowsqltime");
		groupid = getParameter("groupid");
		GraphMakerCommonDataSet ds = new GraphMakerCommonDataSet();

		String[] data_labelY = new String[idlength + 1];
		data_labelY[0] = "";

		GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet ds2_first = new GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet();
		ds2_first.setBarColor(Color.WHITE);
		ds2_first.setRate(0);
		ds2_first.setText("");
		ds2_first.setTextColor(Color.WHITE);
		ds.setDetailList(ds2_first);
		ds.setList(ds.getDetailList());
		ds.clearDetailList();

		for (int i = 0; i < idlength; i++) {
			String id = getParameter("id" + String.valueOf(i));
			String time = getParameter("time" + String.valueOf(i));
			String stat = getParameter("stat" + String.valueOf(i));

			String[] timearray = time.split(",");
			String[] statarray = stat.split(",");

			data_labelY[i + 1] = id;

			GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet ds2 = null;
			for (int m = 0; m < timearray.length; m++) {
				ds2 = new GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet();
				ds2.setRate((int) Double.parseDouble(timearray[m]));

				if (statarray[m].equals("RUN")) {
					ds2.setBarColor(new Color(69, 248, 114));
				} else if (statarray[m].equals("ENG")) {
					ds2.setBarColor(new Color(111, 185, 196));
				} else if (statarray[m].equals("IDLE")) {
					ds2.setBarColor(new Color(255, 134, 255));
				} else if (statarray[m].equals("DOWN")) {
					ds2.setBarColor(new Color(246, 101, 87));
				} else {
					ds2.setBarColor(Color.BLACK);
				}
				double comment = (Double.parseDouble(timearray[m]) + 0.001) * 60 * disphourlen / 10000;
				comment = Math.floor((comment) * 100);
				int comment_min = (int) Math.floor(comment / 100);
				ds2.setText(String.valueOf(comment_min) + labelMin);

				if (comment_min < iMinDispValue) {
					ds2.setText("");
				}
				ds2.setTextColor(Color.BLACK);
				ds.setDetailList(ds2);
			}
			ds.setList(ds.getDetailList());
			ds.clearDetailList();
		}
		ds.setDataLabelY(data_labelY);
		setGraphData(ds);

		if (graphtype == 0) {
			getWidthStatus(nowsqltime, disphourlen);
		} else {
			getWidthStatusCal(nowsqltime);
		}

		ds.setDataLabelX(data_labelX);
		ds.setDataLabelXSub(data_labelX_Sub);

		Canvas canvas = new GraphMaker4(ds);

		canvas.setSize(new Dimension(1400, 800));
		canvas.setBackground(Color.WHITE);

		pl.add(canvas);

		add(pl);
	}

	private void setGraphData(GraphMakerCommonDataSet ds) {

		ds.setGraphOffset_X(116);
		ds.setGraphOffset_Y(53);
		ds.setGraphHeight(500);
		ds.setMaxValue(10000);

		ds.setGraphWidth(720);
		ds.setGraphWidthDetail(-10);
		ds.setGraphBarDetail(-14);
		ds.setGraphBarThick(32);

		ds.setHeaderName("Equipment Group ID:"+ groupid);
		ds.setHeaderWidth(22);
		ds.setHeaderHeight(36);

		ds.setSubStatusName("");
		ds.setSubStatusWidth(730);
		ds.setSubStatusHeight(70);

		if (graphtype == 0) {
			ds.setXUnitName("Date( Interval:" + String.valueOf(disphourlen * 60 / 24) + "min)");
		}
		else {
			ds.setXUnitName("Ratio of Past " + disphourlen + "H (%)");
		}
		ds.setXUnitWidth(735);
		ds.setXUnitHeight(600);

		ds.setYUnitName("  Equipment ID");
		ds.setYUnitWidth(5);
		ds.setYUnitHeight(65);

		ds.setFontH(new Font("Courier", Font.PLAIN, 22));
		ds.setFontColorH(DevicePanelConfig.FONT_COLOR_H);
		ds.setFontS(new Font(DevicePanelConfig.logicalFontName[2], Font.ROMAN_BASELINE, 10));
		ds.setFontColorS(Color.RED);
		ds.setFontU_Y(new Font("Courier", Font.PLAIN, 10));
		ds.setFontColorU_Y(new Color(153, 50, 204));
		ds.setFontU_X(new Font("Courier", Font.PLAIN, 10));
		ds.setFontColorU_X(new Color(153, 50, 204));
		ds.setFontY(DevicePanelConfig.FONT_Y);
		ds.setFontColorY(DevicePanelConfig.FONT_COLOR_Y);
		ds.setFontX(new Font("Courier", Font.PLAIN, 9));
		ds.setFontColorX(DevicePanelConfig.FONT_COLOR_X);
		ds.setFontB(DevicePanelConfig.FONT_B);
		ds.setFontColorB(DevicePanelConfig.FONT_COLOR_B);
		ds.setFontB_S(new Font("Courier", Font.PLAIN, 12));
		ds.setFontColorB_S(Color.MAGENTA);

		ds.setIsScaleY(true);
		ds.setScaleDetailY(82);
		ds.setMaxScaleLengthY(8);
		ds.setIsHeadOrTail(true);
		ds.setIsGraphBarStatus(true);
		ds.setLineDetailXTop(0);
		ds.setXLabelSubDetail(10);

		ds.setDetailDecade(0);
		ds.setDetailState(0);
	}

	private void getWidthStatus(String nowsqltime, int length) {

		int labelnum = 25;
		data_labelX = new String[labelnum];
		data_labelX_Sub = new String[labelnum];

		Calendar now_cal = Calendar.getInstance();
		Timestamp nowtimestamp = Timestamp.valueOf(nowsqltime);
		now_cal.setTime(nowtimestamp);
		now_cal.add(Calendar.HOUR_OF_DAY, -(length));

		String lastmonthday = "";
		String month, day, hour, minute;
		for (int i = 0; i < labelnum; i++) {

			month = String.valueOf(now_cal.get(Calendar.MONTH) + 1);
			day = String.valueOf(now_cal.get(Calendar.DAY_OF_MONTH));
			if (now_cal.get(Calendar.HOUR_OF_DAY) < 10) {
				hour = "0" + String.valueOf(now_cal.get(Calendar.HOUR_OF_DAY));
			} else {
				hour = String.valueOf(now_cal.get(Calendar.HOUR_OF_DAY));
			}
			if (now_cal.get(Calendar.MINUTE) < 10) {
				minute = "0" + String.valueOf(now_cal.get(Calendar.MINUTE));
			} else {
				minute = String.valueOf(now_cal.get(Calendar.MINUTE));
			}

			String monthday = month + "/" + day;
			String hourminute = hour + ":" + minute;

			if (monthday.equals(lastmonthday)) {
				data_labelX[i] = "";
			} else {
				data_labelX[i] = monthday;
			}

			data_labelX_Sub[i] = hourminute;

			now_cal.add(Calendar.MINUTE, length * 60 / 24);

			lastmonthday = monthday;
		}
	}

	private void getWidthStatusCal(String nowsqltime) {

		int labelnum = 11;

		data_labelX = new String[labelnum];
		data_labelX_Sub = new String[labelnum];

		for (int i = 0; i < labelnum; i++) {
			data_labelX[i] = String.valueOf(i * 10);
			data_labelX_Sub[i] = "";
		}
	}
}
