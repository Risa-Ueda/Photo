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
		
		if(button.equals("delete")){//delete�̃{�^���������ꂽ��
			Dao dao = null;
			String id = request.getParameter("id");//id���擾
			try {
				dao = new Dao();//Dao�ɐڑ�
				dao.deletePost(id);//������id�̓��e���폜
				System.out.println(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/delete.jsp");//delete.jsp�ɔ�΂�
			dis.forward(request, response);
		}
	}
}