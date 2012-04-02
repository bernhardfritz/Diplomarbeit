package control;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChartViewer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ChartViewer() {
        super();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
    	doPost(request,response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(Tool.createGraph().createBufferedImage(800, 400), "png", os);
        os.close();
	}

}
