package control;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import model.Data;


public class GraphTest extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929820752596263114L;
	public BufferedImage bi;
	
	public GraphTest() {
		super("Temperaturgraph");
		setBounds(0,0,800,400);
		setResizable(false);
		setVisible(true);
		requestFocus();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException{
		GraphTest gt = new GraphTest();
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
				//Thread.sleep(250);
				gt.bi=ImageIO.read(new File(Data.fishgraph));
				previous=current;
				gt.repaint();
			}
		}
	}

	public void paint(Graphics g){
		g.drawImage(bi,0,0,this);
	}
}
