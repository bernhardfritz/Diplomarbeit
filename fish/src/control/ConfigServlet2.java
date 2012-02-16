package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfigServlet2
 */
public class ConfigServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigServlet2() {
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
		String str[]=new String[99];
		int anzahl=new Integer(request.getParameter("anzahl"));
		for(int i=0; i<anzahl; i++)
		{
			str[i]=request.getParameter("stunden"+i)+":";
			str[i]+=request.getParameter("minuten"+i);
		}
		try{
			Tool.writeFishConfig(str);
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		response.sendRedirect("index.jsp");
	}

}
