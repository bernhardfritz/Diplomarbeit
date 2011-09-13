package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRW {

    public void write(String path, String[] str) throws IOException
    {
        File file = new File(path);
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
    
    public String[] read(String path) throws IOException
    {
    	File file = new File(path);
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
    
    public Double[] Fishdata2Array()
	{
		Double fishdata[]=new Double[51];
		String s[]=new String[51];
		try {
			s=read("C:/fishdata.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<=50; i++)
		{
			fishdata[i]=new Double(s[i]);
		}
		return fishdata;
	}
    
    public int getTemperature(double voltage)
    {
		int temp=0;
		double diff;
    	Double fishdata[]=Fishdata2Array();
    	for(int i=0; i<=50; i++)
		{
			diff=voltage-fishdata[i];
			if(diff<=0)
			{
				temp=i;
				break;
			}
		}
    	if(voltage>fishdata[50]) temp=50;
    	return temp;
    }
}