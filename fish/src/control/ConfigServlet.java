package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBManager;
import model.Data;

/**
 * Servlet implementation class ConfigServlet
 */
public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new Data();
		String str=request.getParameter("str");
		int anzahl=0;
		String res="";
		String stunden="";
		String minuten="";
		String einheiten="";
		PrintWriter out=response.getWriter();
		DBManager dbman=new DBManager(this);
		int currentlength=dbman.getConfig().length;
		if(!str.equals(""))
		{
			anzahl=new Integer(str);
		}
		if((anzahl>0&&anzahl<=Data.futtermax&&anzahl!=currentlength)||!dbman.konfigurationExists())
		{
			res+="<br />\n";
			for(int i=0; i<anzahl; i++)
			{
				res+=(i+1)+". F&uuml;tterung um <select name=\"stunden"+i+"\">\n";
				for(int j=0; j<=23; j++)
				{
					stunden+=j;
					if(stunden.length()==1)
					{
						stunden="0"+j;
					}
					res+="<option value=\""+stunden+"\">"+stunden+"</option>\n";
					stunden="";
				}
				res+="</select>:\n";
				res+="<select name=\"minuten"+i+"\">\n";
				for(int k=0; k<=59; k++)
				{
					minuten+=k;
					if(minuten.length()==1)
					{
						minuten="0"+k;
					}
					res+="<option value=\""+minuten+"\">"+minuten+"</option>\n";
					minuten="";
				}
				res+="</select>Uhr mit \n";
				res+="<select name=\"einheiten"+i+"\">\n";
				for(int l=1; l<=10; l++) {
					einheiten+=l;
					if(einheiten.length()==1) {
						einheiten="0"+l;
					}
					res+="<option value=\""+einheiten+"\">"+einheiten+"</option>\n";
					einheiten="";
				}
				res+="</select> Futtereinheit(en).<br />\n";
			}
			res+="<br />\n";
			res+="<input type=\"submit\" value=\"Speichern\" />";
		}
		if(anzahl==currentlength&&dbman.konfigurationExists())
		{
			String s[]=dbman.getConfig();
			String fstunden[]=new String[s.length];
			String fminuten[]=new String[s.length];
			String feinheiten[]=new String[s.length];
			int counter=0;
			for(String temp:s)
			{
				fstunden[counter]=temp.substring(0,2);
				fminuten[counter]=temp.substring(2,4);
				feinheiten[counter]=temp.substring(4,6);
				
				counter++;
			}
			res+="<br />\n";
			for(int i=0; i<s.length; i++)
			{				
				res+=(i+1)+". F&uuml;tterung um <select name=\"stunden"+i+"\">\n";
				for(int j=0; j<=23; j++)
				{
					
					stunden+=j;
					if(stunden.length()==1)
					{
						stunden="0"+j;
					}
					if(fstunden[i].equals(stunden)) res+="<option value=\""+stunden+"\" selected=\"selected\">"+stunden+"</option>\n";
					else res+="<option value=\""+stunden+"\">"+stunden+"</option>\n";
					stunden="";
				}
				res+="</select>:\n";
				res+="<select name=\"minuten"+i+"\">\n";
				for(int k=0; k<=59; k++)
				{
					minuten+=k;
					if(minuten.length()==1)
					{
						minuten="0"+k;
					}
					if(fminuten[i].equals(minuten)) res+="<option value=\""+minuten+"\" selected=\"selected\">"+minuten+"</option>\n";
					else res+="<option value=\""+minuten+"\">"+minuten+"</option>\n";
					minuten="";
				}
				res+="</select>Uhr mit \n";
				res+="<select name=\"einheiten"+i+"\">\n";
				for(int l=1; l<=10; l++) {
					einheiten+=l;
					if(einheiten.length()==1) {
						einheiten="0"+l;
					}
					if(feinheiten[i].equals(einheiten)) res+="<option value=\""+einheiten+"\" selected=\"selected\">"+einheiten+"</option>\n";
					res+="<option value=\""+einheiten+"\">"+einheiten+"</option>\n";
					einheiten="";
				}
				res+="</select> Futtereinheit(en).<br />\n";
			}
			res+="<br />\n";
			res+="<input type=\"submit\" value=\"Speichern\" />";
		}
		dbman.close();
		out.println(res);		
	}
}
