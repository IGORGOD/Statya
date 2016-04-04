package ua.iih.graphics;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Graphic {

	public static void paint(double [] Y, String [] X){

		JFrame frame = new JFrame();
		frame.setTitle("График");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		XYSeries series = new XYSeries("");
		if (X.length != Y.length){
			System.out.println("Error: X[] and Y[] must be with same length");
			return;
		}
		for (int i = 0; i < Y.length; i++) {
			series.add(i, Y[i]);
		}

		XYSeriesCollection data = new XYSeriesCollection(series);
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Пассажиропоток",
				"X",
				"Y",
				data,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);
		XYPlot plot = (XYPlot) chart.getPlot();
		SymbolAxis rangeAxis = new SymbolAxis("Dates", X);
		rangeAxis.setTickUnit(new NumberTickUnit(1)); // tra-ta-ta 4astota
	    rangeAxis.setRange(0, X.length);
	    plot.setDomainAxis(rangeAxis);
		
		final ChartPanel chartPanel = new ChartPanel(chart);
		
		chartPanel.setPreferredSize(new java.awt.Dimension(1300, 600));
		frame.setContentPane(chartPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
}
