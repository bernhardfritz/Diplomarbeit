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
		PrintWriter out=response.getWriter();
		if(!str.equals(""))
		{
			anzahl=new Integer(str);
		}
		if(anzahl!=0)
		{
			res="<form action=\"\">\n";
			for(int i=0; i<anzahl; i++)
			{
				res+="<select name=\"stunden\">\n";
				for(int j=0; j<=23; j++)
				{
					res+="<option value=\""+j+"\">"+j+"</option>\n";
				}
				res+="</select>\n";
				res+="<select name=\"minuten\">\n";
				for(int k=0; k<=59; k++)
				{
					res+="<option value=\""+k+"\">"+k+"</option>\n";
				}
				res+="</select><br />\n";
			}
			res+="</form>\n";
		}
		out.println(res);		
	}

}
