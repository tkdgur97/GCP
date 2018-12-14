package test;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Test2 {
	public static void main(String[] args) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		String KEY1 = "KEY1";
		String KEY2 = "KEY2";
		dataset.setValue("KEY1", 99);
        dataset.setValue("KEY2", 77);

        JFreeChart someChart = ChartFactory.createPieChart(
            "Header", 
            dataset, 
            true, 
            true, 
            false);
        PiePlot plot = (PiePlot) someChart.getPlot();
        plot.setSectionPaint(KEY1, Color.green);
        plot.setSectionPaint(KEY2, Color.red);
        plot.setExplodePercent(KEY1, 0.10);
        plot.setSimpleLabels(true);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);

        JFrame f = new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new ChartPanel(someChart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }
        });
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

	}
}