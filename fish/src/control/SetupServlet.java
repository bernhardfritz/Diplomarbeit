package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBManager;

/**
 * Servlet implementation class SetupServlet
 */
public class SetupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String auth = request.getParameter("auth");
		
		HttpSession session=request.getSession();
		boolean correct = false;
		boolean key = false;

		if(username.length()>=3&&username.length()<=8&&password.length()>=3&&password.length()<=32) correct=true;
		if(auth.equals("mpLiBGsDwwZS8ntAsiWg6Zmm3WF6TNDGCXyMmrYjn8Cuu55nmaUsAGeCmsIFGUV")) key=true;
		
		session.setAttribute("correct", correct);
		session.setAttribute("key", key);
		
		if(correct&&key) {
			DBManager dbman;
			try {
				dbman = new DBManager(this);
				dbman.setup(username, password);
				dbman.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("index.jsp");
		}
		else response.sendRedirect("setup.jsp");
	}

}
