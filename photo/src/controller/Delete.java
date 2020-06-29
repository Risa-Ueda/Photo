package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button = request.getParameter("button");
		
		if(button.equals("delete")){//delete‚Ìƒ{ƒ^ƒ“‚ª‰Ÿ‚³‚ê‚½‚ç
			Dao dao = null;
			String id = request.getParameter("id");//id‚ğæ“¾
			try {
				dao = new Dao();//Dao‚ÉÚ‘±
				dao.deletePost(id);//ˆø”‚Ìid‚Ì“Še‚ğíœ
				System.out.println(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/delete.jsp");//delete.jsp‚É”ò‚Î‚·
			dis.forward(request, response);
		}
	}
}