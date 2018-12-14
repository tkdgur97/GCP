package admin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import tcpserver.MemberDTO;
import tcpserver.TCPClient1;

public class MemberAge_admin {
	ChartPanel chartPanel;
	public MemberAge_admin() throws Exception {
		ArrayList<MemberDTO> member = new TCPClient1().allMemberInfo();
		int[] count = new int[6];
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

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(count[0], "사람 수", "20대");
		dataset.addValue(count[1], "사람 수", "30대");
		dataset.addValue(count[2], "사람 수", "40대");
		dataset.addValue(count[3], "사람 수", "50대");
		dataset.addValue(count[4], "사람 수", "60대");
		dataset.addValue(count[5], "사람 수", "70대");

		JFreeChart chart = ChartFactory.createBarChart("", "연령대", "명 수", dataset, PlotOrientation.VERTICAL, true, true,
				false);
		chart.setBackgroundPaint(Color.WHITE);

		// 2. 그래프 전체의 경계선 설정
		chart.setBorderVisible(true); // 차트전체의 경계선이 나타난다.
		chart.setBorderPaint(Color.BLUE); // 차트전체의 경계선의 색을 파란색으로 정한다.
		chart.setBorderStroke(new BasicStroke(5)); // 차트전체의 경계선의 두께를 정한다.
		
		// 제목
		chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15));
		// 범례
		chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10));

		CategoryPlot plot = chart.getCategoryPlot();

		Font font = plot.getDomainAxis().getLabelFont();
		// X축 라벨
		plot.getDomainAxis().setLabelFont(new Font("돋움", font.getStyle(), font.getSize()));
		// X축 도메인
		plot.getDomainAxis().setTickLabelFont(new Font("굴림", font.getStyle(), 15));

		font = plot.getRangeAxis().getLabelFont();
		// Y축 라벨
		plot.getRangeAxis().setLabelFont(new Font("돋움", font.getStyle(), font.getSize()));
		// Y축 범위
		plot.getRangeAxis().setTickLabelFont(new Font("돋움", font.getStyle(), 10));
		chartPanel = new ChartPanel(chart);
		chartPanel.setLocation(2, 5);
		chartPanel.setSize(443, 252);
	
	}
	
	public ChartPanel panel() {
		return chartPanel;
	}
	

	public static void main(String[] args) throws Exception {
		
		JFrame jf = new JFrame();
		jf.getContentPane().setLayout(null);
		jf.getContentPane().add(new MemberAge_admin().panel());
		jf.setBounds(700, 300, 700, 700);
		jf.setVisible(true);

	}

}