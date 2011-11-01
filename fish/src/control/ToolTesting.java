package control;

import java.io.IOException;
import java.util.Calendar;

public class ToolTesting {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//Tool.createImage2("Woche");
		/*Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -5);
		System.out.println(cal.getTime().toString());*/
		Tool.feed(1);
		Feeding.status="start";
		Feeding.main(null);
	}

}
