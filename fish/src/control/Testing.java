package control;

import model.*;
public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SocketManager sman=new SocketManager();
		//System.out.println(sman.GETSTATUS(1));
		sman.SETPORT(1,1);
		Tool.wait(500);
		for(int i=0; i<10; i++)
		{
			System.out.println(i+1);
			System.out.println(sman.GETADC(1));
			Tool.wait(1000);
			System.out.println(sman.GETADC(1));
			Tool.wait(500);
			sman.SETPORT(1,0);
			Tool.wait(500);
			sman.SETPORT(1,1);
			Tool.wait(500);
			System.out.println(sman.GETADC(1));
			Tool.wait(500);
			sman.SETPORT(1,0);
			Tool.wait(500);
			sman.SETPORT(1,1);
			Tool.wait(1000);
		}
	}
}
