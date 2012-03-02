package control;

import model.SocketManager;

public class SocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Data();
		SocketManager sman=new SocketManager();
		System.out.println(sman.GETADC(1));
		
	}

}
