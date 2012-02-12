package control;

import model.DBManager;
import model.Daten;
import model.SocketManager;

public class TemperaturTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Data();
		SocketManager sman=new SocketManager();
		String s="";
    	int i;
    	double v;
    	double t;
    	System.out.println("Integer\tVoltage\t\tTemperature");
    	while(true) {
    		s=sman.GETADC(1);
        	i=Integer.parseInt(s.trim());
        	v=Tool.getVoltage(i);
    		t=Tool.getTemperature(v);
        	System.out.println(i+"\t"+v+"\t"+t);
        	Tool.wait(1000);
    	}
    	
	}

}
