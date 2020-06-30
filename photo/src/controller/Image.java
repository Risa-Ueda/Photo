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
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // �Z�b�V�����̎擾(�Ȃ����null���Ԃ��Ă���)
		HttpSession session = request.getSession(false); // �Z�b�V�����̔j��
		if(session != null) session.invalidate(); // ���O�C�����s���A���O�A�E�g���A�s�����쎞�ȊO�̏ꍇ
		if(request.getAttribute("message") == null) request.setAttribute("message", "���O�ƃp�X���[�h����͂��Ă�������"); //message��null�̏ꍇ
		response.setContentType("text/html; charset=UTF-8");
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jsp�ɔ�΂�
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success = false;
		Dao dao = null;
		String imagename = request.getParameter("imagename");
		
			try {
				dao = new Dao();//Dao�Ɛڑ�
				success = dao.getImagename(imagename);//DB�ɂ���imagename���擾
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(success);
			
			if(success) {
				request.setAttribute("imgname", imagename);//DB�ɂ���imagename���Z�b�g
				
			}else {
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//��肭�����Ȃ����image.jsp�ɖ߂�
				dis.forward(request, response);
			}
			
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/post.jsp");//imagename���Z�b�g�ł�����create.jsp�ɔ�΂�
			dis.forward(request, response);
		}
}