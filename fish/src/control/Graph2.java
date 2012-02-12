package control;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.SocketManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class Graph2 extends JFrame{
	
	private static final long serialVersionUID = -170906011521620204L;
	public BufferedImage bi;
	
	public Graph2() {
		super("Temperaturgraph");
		setBounds(0,0,800,400);
		setResizable(false);
		setVisible(true);
		requestFocus();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws IOException{
		Graph2 gr2 = new Graph2();
		TimeSeries tsw = new TimeSeries("Wassertemperatur in °C", Second.class);
		TimeSeries tsl = new TimeSeries("Lufttemperatur in °C", Second.class);
		tsw.setMaximumItemCount(60);
		tsl.setMaximumItemCount(60);
		Date d=new Date();
		int current=d.getSeconds();
		int previous=current;
		System.out.println(d.toString());
		while(true) {
			while(current==previous) {
				d=new Date();
				current=d.getSeconds();
			}
			if(current!=previous)
			{
				System.out.println(d.toString());
				new Data();
				SocketManager sman=new SocketManager();
				String s=sman.GETADC(1);
	        	int i=Integer.parseInt(s.trim());
	        	double v=Tool.getVoltage(i);
	    		double t=Tool.getTemperature(v);
				tsw.addOrUpdate(new Second(), t);
				tsl.addOrUpdate(new Second(), t);
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
				gr2.bi=chart.createBufferedImage(800,400);
				previous=current;
				ImageIO.write(gr2.bi,"png",new File(Data.fishgraph));
				gr2.repaint();
			}
		}
	}
		
	public void paint(Graphics g){
		g.drawImage(bi,0,0,this);
	}
}
