package control;

import java.io.IOException;
import java.sql.SQLException;

import model.DBManager;
import model.Data;
import model.Daten;
import model.SocketManager;

public class ToolTesting {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
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
		//System.out.println(System.getProperty("user.dir"));
		new Data();
		//SocketManager sman=new SocketManager();
		//FeedThread ft=new FeedThread(sman);
		//ft.run();
		/*for(String s: Tool.read(Data.fishconfig)) {
			System.out.println(s);
		}*/
		//DBManager dbman=new DBManager(null);
		//SocketManager sman=new SocketManager();
		//dbman.createDB();
		/*for(int i=0; i<=10; i++) {
			Tool.fetch(sman);
			Tool.wait(250);
		}*/
		/*for(Daten d:dbman.getAll()) {
			System.out.println(Tool.DateToDouble(d.getZeitpunkt())+"\t"+d.getLtemp());
		}*/
		//Tool.fetch(new SocketManager());
		//new Data();
		//System.out.println(Data.mailrecipientadress);
		//new Data();
		//Tool.sendMail("asdf", "bla");
		//System.out.println(Data.mailrecipientadress);
		//System.out.println(Data.mailsenderadress);
		//new JavaMailThread("asdf","asdf").run();
		Tool.feed(new SocketManager());
	}

}
