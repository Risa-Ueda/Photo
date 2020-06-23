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

import dacct.AcCreate;
import dao.DBAccess;
import dao.Dao;
import dto.Dto;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//create.jsp����username���擾���ϐ�username�ɑ��
		String password = request.getParameter("password");//create.jsp����imagename���擾���A�ϐ�imagename�ɑ��
		if(username == null || username.isEmpty() || password == null || password.isEmpty()) {//imagename�̒l��DB�ɓ����Ă����ꍇ
			request.setAttribute("message", "!���[�U���A�p�X���[�h����͂��Ă�������");//!���[�U���Aimagename����͂��Ă��������ƃ��b�Z�[�W��\��
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/createaccount.jsp");//createaccount.jsp�Ƀy�[�W��߂�
			dis.forward(request, response);
		}else if(username != null || password != null) {//username��password�����͂���Ă���ꍇ
			request.setAttribute("username", username);//username��username�ɃZ�b�g
			request.setAttribute("password", password);//password��password�ɃZ�b�g
			AcCreate dbAccess = new AcCreate();//AcCreate���\�b�h�ŃA�J�E���g�쐬
			try {
				((DBAccess) dbAccess).execute(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Account created!");
		}else {//����ȊO
			request.setAttribute("message", "!�G���[���������܂���");
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//create.jsp�ɖ߂�
			dis.forward(request, response);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/createaccount.jsp");//���e���ꂽ��createfinish.jsp�ɔ�΂�
		dis.forward(request, response);
	}
}