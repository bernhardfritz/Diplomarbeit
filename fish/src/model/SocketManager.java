package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import control.Data;

public class SocketManager {
	
	private String ip;
	private int port;
	public Socket socket;
	public PrintWriter printWriter;
	public BufferedReader bufferedReader;
	
	public SocketManager()
	{
		ip=Data.netioip;
		port=Data.netioport;
		init();
	}
	
	public void init()
	{
		try {
			socket = new Socket(ip,port);
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			Data.logger.error(e.getMessage());
		} catch (IOException e) {
			Data.logger.error(e.getMessage());
		}
	}
	
	public void close()
	{
		try {
			socket.close();
		} catch (IOException e) {
			Data.logger.error(e.getMessage());
		}
	}
	
    public String GETADC(int adc)
    {
    	String zuSendendeNachricht = "GETADC "+adc;
		String empfangeneNachricht = "";
		String formatierteNachricht = "";
    	try {
    		schreibeNachricht(zuSendendeNachricht);
    		empfangeneNachricht = leseNachricht();
    	} catch (IOException e) {
    		Data.logger.error(e.getMessage());
    	}	
		formatierteNachricht = empfangeneNachricht.trim();
        return formatierteNachricht;
    }
    
    public String GETSTATUS(int port)
    {
    	String zuSendendeNachricht = "GETSTATUS "+port;
		String empfangeneNachricht = "";
		String formatierteNachricht = "";
    	try {
    		schreibeNachricht(zuSendendeNachricht);
    		empfangeneNachricht = leseNachricht();
    	} catch (IOException e) {
    		Data.logger.error(e.getMessage());
    	}	
    	
    	formatierteNachricht = empfangeneNachricht.trim();
        return formatierteNachricht;
    }
    
    public void SETPORT(int port,int status)
    {
    	String zuSendendeNachricht = "SETPORT "+port+"."+status;
    	
    		try {
				schreibeNachricht(zuSendendeNachricht);
				leseNachricht();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Data.logger.error(e.getMessage());
			}
    }
    
    public void schreibeNachricht(String nachricht) throws IOException {
        printWriter.println(nachricht);
        printWriter.flush();
    }
     
    public String leseNachricht() throws IOException {
        char[] buffer = new char[200];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
        String nachricht = new String(buffer, 0, anzahlZeichen);
        return nachricht;
    }
}
