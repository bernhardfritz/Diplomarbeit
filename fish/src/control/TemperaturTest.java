package control;

import model.Data;
import model.SocketManager;

public class TemperaturTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Data();
		SocketManager sman=new SocketManager();
    	int i;
    	float v;
    	float t;
    	int counter=0;
    	System.out.println("Counter\tInteger\tVoltage\t\tTemperature");
    	while(true) {
        	i=sman.GETADC(Data.adcluft);
        	v=Tool.getVoltageFromDigital(i);
    		t=Tool.getTemperatureFromVoltage(v);
        	System.out.println(counter+++"\t"+i+"\t"+v+"\t"+t);
        	Tool.wait(500);
    	}
    	
	}

}
