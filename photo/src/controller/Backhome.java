package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Backhome
 */
@WebServlet("/Backhome")
public class Backhome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button = request.getParameter("button");		//button‚ª‰Ÿ‚³‚ê‚½‚ç
		if(button.equals("home")){	//image‚Ìƒ{ƒ^ƒ“‚ª‰Ÿ‚³‚ê‚½‚ç
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/home.jsp");//image.jsp‚É”ò‚Î‚·
			dis.forward(request, response);
		}
	}

}
