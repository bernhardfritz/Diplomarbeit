package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;

public class DatenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatenServlet() {
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
		String wtemp=request.getParameter("wtemp");
		String ltemp=request.getParameter("ltemp");
		String breite=request.getParameter("breite");
		String laenge=request.getParameter("laenge");
		String wasserstand=request.getParameter("wasserstand");
		String volumen=request.getParameter("volumen");
		String naehrstoffgehalt=request.getParameter("naehrstoffgehalt");
		String lichtintensitaet=request.getParameter("lichtintensitaet");
		Daten d=new Daten(wtemp,ltemp,breite,laenge,wasserstand,volumen,naehrstoffgehalt,lichtintensitaet);
		
		DBManager dbman;
		try {
			dbman = new DBManager();
			dbman.speichern(d);
			dbman.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
