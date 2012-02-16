package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBManager;
import model.Daten;

/**
 * Servlet implementation class DBServlet
 */
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new Data();
		HttpSession session=request.getSession();
		DBManager dbman;
		List<Daten> erg=null;
		dbman = new DBManager();
		erg=dbman.getAll();
		dbman.close();
		String tabelle="<table border=1><th>ID</th><th>Wassertemperatur</th><th>Lufttemperatur</th><th>Zeitpunkt</th><th>Wassertemperatur/Lufttemperatur</th>";
		if(erg!=null)
		{
			for(Daten d:erg)
			{
				tabelle+="<tr><td>"+d.getId()+
						"</td><td>"+d.getWtemp()+
						"</td><td>"+d.getLtemp()+
						"</td><td>"+d.getZeitpunkt()+
						"</td><td>"+Tool.getGauge(d.getWtemp())+"<br />"+Tool.getGauge(d.getLtemp())+
						"</td></tr>";	
			}
		}
		tabelle+="</table>";
		session.setAttribute("tabelle",tabelle);
		response.sendRedirect("tabelle.jsp");
	}

}
