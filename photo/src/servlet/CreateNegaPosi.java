package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static img.ImageUtility.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Servlet implementation class CreateImage
 */
@WebServlet("/CreateNegaPosi")
public class CreateNegaPosi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//public class Test {
		    //public static void main(String[] args) throws IOException {
		        //File f = new File("sky.jpg");
		        BufferedImage read=ImageIO.read(new File(getServletContext().getRealPath("/") + "time.jpg"));
		        int w = read.getWidth(),h=read.getHeight();
		        System.out.println("width:" + w);
		        System.out.println("height:" + h);
		        BufferedImage write =
		                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		        
		        for(int y=0;y<h;y++){
		            for(int x=0;x<w;x++){
		                int c = read.getRGB(x, y);
		                int r = 255-r(c);
		                int g = 255-g(c);
		                int b = 255-b(c);
		                int rgb = rgb(r,g,b);
		                write.setRGB(x,y,rgb);
		            }
		        }
		        
		        File f2 = new File(getServletContext().getRealPath("/") + "time.jpg");
		        ImageIO.write(write, "jpg", f2);
		    }
		//}
	}
	
