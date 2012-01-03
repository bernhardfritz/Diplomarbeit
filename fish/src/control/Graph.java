package control;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class Graph extends Canvas implements WindowListener{
	
	private static final long serialVersionUID = -170906011521620204L;
	BufferedImage bi;
	Image img;
	BufferStrategy strategy;
	JFrame container;
	JPanel panel;
	
	public void init(){
		container = new JFrame("Temperaturgraph");
		panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800,400));
		panel.setLayout(null);
		setBounds(0,0,800,400);
		panel.add(this);
		setIgnoreRepaint(true);
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		container.addWindowListener(this);
		requestFocus();
		createBufferStrategy(2);
	}
	
	public static void main(String[] args) throws IOException{
		Graph gt = new Graph();
		gt.init();
		TimeSeries tsw = new TimeSeries("Wassertemperatur in °C", Second.class);
		TimeSeries tsl = new TimeSeries("Lufttemperatur in °C", Second.class);
		tsw.setMaximumItemCount(10);
		tsl.setMaximumItemCount(10);
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
				gt.bi=chart.createBufferedImage(800,400);
				gt.img=gt.bi;
				gt.draw();
				previous=current;
			}
		}
	}
		
	public void draw() {
		strategy=getBufferStrategy();
		Graphics g=strategy.getDrawGraphics();
		g.drawImage(img,0,0,null);
		g.dispose();
		strategy.show();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

