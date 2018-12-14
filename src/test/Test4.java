package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import tcpserver.MemberDTO;
import tcpserver.TCPClient1;

public class Test4 extends JFrame {

	private Browser browser = new Browser();
	private BrowserView browserView = new BrowserView(browser);
	int[] count;
	JPanel jp;

	public Test4() throws Exception {
		ArrayList<MemberDTO> member = new TCPClient1().allMemberInfo();
		count = new int[6];
		for (int i = 0; i < member.size(); i++) {
			int birthYear = Integer.parseInt("19" + member.get(i).getRrn().substring(0, 2));// 주민번호 앞자리 2개 인트변환
			String year = String.valueOf((Calendar.getInstance().get(Calendar.YEAR) - birthYear + 1));
			if (year.charAt(0) == '2') {
				count[0]++;
			} else if (year.charAt(0) == '3') {
				count[1]++;
			} else if (year.charAt(0) == '4') {
				count[2]++;
			} else if (year.charAt(0) == '5') {
				count[3]++;
			} else if (year.charAt(0) == '6') {
				count[4]++;
			} else if (year.charAt(0) == '7') {
				count[5]++;
			}
		}
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setVisible(true);
//		setSize(800, 600);
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, 447, 258);
		browser.addLoadListener(new LoadAdapter() {
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent event) {
			}
		});
		browser.loadHTML(getChart());
		browserView.setBounds(0, 0, 447, 258);
		browserView.setBackground(Color.LIGHT_GRAY);
		jp.add(browserView);
		jp.setBackground(Color.LIGHT_GRAY);
		add(jp, BorderLayout.CENTER);
	}

	public JPanel jpanel() {
		return jp;
	}

	public String getChart() throws Exception {

		String htmlString = "<html>\r\n" + "  <head>\r\n"
				+ "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n"
				+ "    <script type=\"text/javascript\">\r\n"
				+ "    google.charts.load(\"current\", {packages:[\"corechart\"]});\r\n"
				+ "    google.charts.setOnLoadCallback(drawChart);\r\n" + "    function drawChart() {\r\n"
				+ "     var data = google.visualization.arrayToDataTable([\r\n"
				+ "        ['Year', '인원', { role: 'style' } ],\r\n" + "        ['20대', " + count[0]
				+ ", 'color: gray'],\r\n" + "        ['30대', " + count[1] + ", 'color: #76A7FA'],\r\n"
				+ "        ['40대', " + count[2] + ", 'opacity: 0.2'],\r\n" + "        ['50대', " + count[3]
				+ ", 'stroke-color: #703593; stroke-width: 4; fill-color: #C5A5CF'],\r\n" + "        ['60대', "
				+ (count[4] + count[5])
				+ ", 'stroke-color: #871B47; stroke-opacity: 0.6; stroke-width: 8; fill-color: #BC5679; fill-opacity: 0.2']\r\n"
				+ "      ]);\r\n" + "\r\n" + "      var view = new google.visualization.DataView(data);\r\n"
				+ "      view.setColumns([0, 1,\r\n" + "                       { calc: \"stringify\",\r\n"
				+ "                         sourceColumn: 1,\r\n" + "                         type: \"string\",\r\n"
				+ "                         role: \"annotation\" },\r\n" + "                       2]);\r\n" + "\r\n"
				+ "      var options = {\r\n" + "        title: \"도서관 회원나이\",\r\n" + "        width: 420,\r\n"
				+ "        height: 230,\r\n" + "        bar: {groupWidth: \"95%\"},\r\n"
				+ "        legend: { position: \"none\" },\r\n" + "      };\r\n"
				+ "      var chart = new google.visualization.BarChart(document.getElementById(\"barchart_values\"));\r\n"
				+ "      chart.draw(view, options);\r\n" + "  };\r\n" + "    </script>\r\n" + "  </head>\r\n"
				+ "  <body>\r\n" + "    <div id=\"barchart_values\" style=\"width: 150px; height: 150px;\"></div>\r\n"
				+ "  </body>\r\n" + "</html>";
		return htmlString;
	}

}