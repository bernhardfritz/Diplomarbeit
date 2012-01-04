package control;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.tomcat.jni.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class Graph3 {
	
	public RenderedImage ri;
	
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
				ImageIO.write(gr3.ri, "png", new java.io.File("C:/fishfiles/graph.png"));
				previous=current;
			}
		}
	}
}
