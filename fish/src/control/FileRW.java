package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRW {

    public void write(String[] str) throws IOException
    {
        File file = new File("C:/fishconfig.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
 
        for (int i = 0; i < str.length; i++) {
            if(str[i]!=null)
            {	
            	bw.write(str[i]);
            	bw.newLine();
            }
        }
 
        bw.flush();
        bw.close();
    }
    
    public String[] read() throws IOException
    {
    	File file = new File("C:/fishconfig.txt");
    	BufferedReader br = new BufferedReader(new FileReader(file));
 
        String str[]=new String[99];
    	String line;
    	int i=0;
 
        while ((line = br.readLine()) != null) {
            str[i]=line;
            i++;
        }
 
        br.close();
        return str;
 
    }
}