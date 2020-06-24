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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = null;
		String username = request.getParameter("username");
			try {
				dao = new Dao();//DaoÇ∆ê⁄ë±
				dao.getuserPosts(username);
				System.out.println(username);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("username", username);
			
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/profileone.jsp");//image.jspÇ…îÚÇŒÇ∑
			dis.forward(request, response);
		}
	}