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
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class GraphTest extends JFrame{
	
	public BufferedImage bi;
	
	public GraphTest() {
		super("Temperaturgraph");
		setBounds(0,0,800,400);
		setResizable(false);
		setVisible(true);
		requestFocus();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException{
		GraphTest gt = new GraphTest();
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
				//Thread.sleep(250);
				gt.bi=ImageIO.read(new File("C:/fishfiles/graph.png"));
				previous=current;
				gt.repaint();
			}
		}
	}

	public void paint(Graphics g){
		g.drawImage(bi,0,0,this);
	}
}