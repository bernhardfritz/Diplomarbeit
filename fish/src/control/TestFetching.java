package control;

import java.io.IOException;

public class TestFetching {
	public static void main(String args[])
	{
		String s="";
		int i=0;
		double d=0.0;
		int temperature=0;
		try {
			s=Tool.connect("192.168.0.90", 50290, "GETADC 1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
		i=new Integer(s);
		d=Tool.getVoltage(i);
		System.out.println(d);
		temperature=Tool.getTemperature(d);
		System.out.println(temperature);
		
	}
}
