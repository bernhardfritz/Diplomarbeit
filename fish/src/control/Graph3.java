package control;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class Graph3 {
	
	public RenderedImage ri;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException{
		Graph3 gr3 = new Graph3();
		TimeSeries tsw = new TimeSeries("Wassertemperatur in °C", Second.class);
		TimeSeries tsl = new TimeSeries("Lufttemperatur in °C", Second.class);
		tsw.setMaximumItemCount(60);
		tsl.setMaximumItemCount(60);
		Date d=new Date();
		int current=d.getSeconds();
		int previous=current;
		System.out.println(d.toString());
		int i=-1;
		while(true) {
			while(current==previous) {
				d=new Date();
				current=d.getSeconds();
			}
			if(current!=previous)
			{
				System.out.println(d.toString());
				i++;
				tsw.addOrUpdate(new Second(), i);
				tsl.addOrUpdate(new Second(), i);
				TimeSeriesCollection dataset = new TimeSeriesCollection();
				dataset.addSeries(tsw);
				dataset.addSeries(tsl);
				JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"Wasser- und Lufttemperaturgraph",
				"Sekunden",
				"°C",
				dataset,
				true,
				true,
				false);
				gr3.ri=chart.createBufferedImage(800,400);
				ImageIO.write(gr3.ri, "png", new java.io.File(Data.fishprefix+Data.fishgraph));
				previous=current;
			}
		}
	}
}
