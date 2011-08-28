package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String str=request.getParameter("str");
		int anzahl=0;
		String res="";
		String stunden="";
		String minuten="";
		PrintWriter out=response.getWriter();
		if(!str.equals(""))
		{
			anzahl=new Integer(str);
		}
		if(anzahl!=0)
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
				res+="</select>Uhr.<br />\n";
			}
			res+="<br />\n";
			res+="<input type=\"submit\" value=\"Speichern\" />";
		}
		out.println(res);		
	}

}
