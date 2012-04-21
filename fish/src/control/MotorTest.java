package control;

import model.Data;
import model.SocketManager;

public class MotorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Data();
		SocketManager sman=new SocketManager();
		Tool.feed(sman,4);
		sman.close();
		/*sman.SETPORT(1, 1);
		Tool.wait(10000);
		sman.SETPORT(1, 0);*/
	}

}
