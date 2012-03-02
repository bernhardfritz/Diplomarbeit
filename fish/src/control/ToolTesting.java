package control;

import java.io.IOException;

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
		//Tool.feed(1);
		//Main.main(null);
		/*new Data();
		String text[]=Tool.readFishConfig();
		int length=text.length;
		System.out.println(length);
		System.out.println(Tool.fishConfigExists());
		for(String s:text) {
			System.out.println(s);
		}
		String text2[]={"14:03","14:04","14:05"};
		Tool.writeFishConfig(text2);*/
		//System.out.println(Tool.md5("bernhard"));
		//Tool.createGraph();
		System.out.println(System.getProperty("user.dir"));
	}

}
