package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBManager;

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
		int anzahl=new Integer(request.getParameter("anzahl"));
		String str[]=new String[anzahl];
		for(int i=0; i<anzahl; i++)
		{
			str[i]=request.getParameter("stunden"+i);
			str[i]+=request.getParameter("minuten"+i);
			str[i]+=request.getParameter("einheiten"+i);
		}
		DBManager dbman=new DBManager(this);
		dbman.setConfig(str);
		dbman.close();
		response.sendRedirect("index.jsp");
	}

}
