package control;

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
    	float v;
    	float t;
    	int counter=0;
    	System.out.println("Counter\tInteger\tVoltage\t\tTemperature");
    	while(true) {
    		s=sman.GETADC(1);
        	i=Integer.parseInt(s.trim());
        	v=Tool.getVoltage(i);
    		t=Tool.getTemperature(v);
        	System.out.println(counter+++"\t"+i+"\t"+v+"\t"+t);
        	Tool.wait(500);
    	}
    	
	}

}
