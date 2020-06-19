package servlet;

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
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success = false;
		Dao dao = null;
		String imagename = request.getParameter("imagename");
		//String button = request.getParameter("button");	
		try {
			dao = new Dao();//Dao�Ɛڑ�
			success = dao.getImagename(imagename);//DB�ɂ���imagename���擾
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		System.out.println(success);
		if(success) {
			request.setAttribute("imgname", imagename);//DB�ɂ���imagename���Z�b�g
			
		/*}else if(button.equals("upload")){
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/Upload.java");//��肭�����Ȃ����image.jsp�ɖ߂�
			dis.forward(request, response);*/
		
		
		}else {
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//��肭�����Ȃ����image.jsp�ɖ߂�
			dis.forward(request, response);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//imagename���Z�b�g�ł�����create.jsp�ɔ�΂�
		dis.forward(request, response);
	}

}
