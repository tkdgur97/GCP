package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

import tcpserver.TCPClient1;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.LegendTitle;

public class BookChart_admin extends ApplicationFrame {
	ChartPanel chartPanel;

	public BookChart_admin(String title) throws Exception {

		super(title); // 입력받은 타이틀 가져오기
		PieDataset dataset = createTestSample(); // 데이터셋 객체 정의
		JFreeChart chart = createChart(dataset); // 데이터셋을 참조하는 차트객체만들기
		chartPanel = new ChartPanel(chart); // 패널
		chartPanel.setPreferredSize(new Dimension(500, 400)); // 크기
		setContentPane(chartPanel); // 패널 세팅
		chartPanel.setLayout(null);
		chartPanel.validate();
		chartPanel.setBackground(Color.LIGHT_GRAY);

	}

	private PieDataset createTestSample() throws Exception {
		TreeMap<String, Integer> b = new TCPClient1().bookKind2();
		String[] kind = { "사회과학","기술과학", "역사", "예술", "철학", "총류", "자연과학", "언어", "기타", "종교", "문학"};
		DefaultPieDataset testData = new DefaultPieDataset();
		for (int i = 0; i < kind.length; i++) {
			testData.setValue(kind[i], b.get(kind[i]));
		}

		return testData; // 데이터셋 반환
	}

	private JFreeChart createChart(PieDataset dataset) { // 위의 파이 데이터셋 메서드에서 가져오기

		JFreeChart chart = ChartFactory.createPieChart3D( // 3D차트 만들기
				"",// 제목
				dataset, // 데이터셋 연동으로 처리
				true, // 모름
				true, // 모름
				false // 모름
		);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: ({2})", new DecimalFormat("0"),
				new DecimalFormat("0%"));
		plot.setLabelGenerator(gen);
		chart.getTitle().setFont(new Font("굴림", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10));
		plot.setLabelFont(new Font("굴림", Font.BOLD, 15));
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);

		return chart;
	}

	public JPanel chart() throws Exception {

		return chartPanel;

	}

	public static void main(String[] args) throws Exception {
		TreeMap<String, Integer> b = new TCPClient1().bookKind2();

		BookChart_admin testgo = new BookChart_admin("test");
		testgo.pack();
		RefineryUtilities.centerFrameOnScreen(testgo);
		testgo.setVisible(true);

	}

}
