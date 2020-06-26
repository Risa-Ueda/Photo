package dacct;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		request.setCharacterEncoding("UTF-8");
		String button = request.getParameter("button");
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//create.jsp����username���擾���ϐ�username�ɑ��
		String password = request.getParameter("password");//create.jsp����imagename���擾���A�ϐ�imagename�ɑ��
		
		if(button.equals("login")){//login�̃{�^���������ꂽ��
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jsp�ɔ�΂�
			dis.forward(request, response);
		}else if(button.equals("create")){//create�̃{�^���������ꂽ��
			
			if(username == null || username.isEmpty() || password == null || password.isEmpty()) {//username��password�̒��ɒl�������Ă��Ȃ��ꍇ
				request.setAttribute("message", "!���[�U���A�p�X���[�h����͂��Ă�������");//!���[�U���A�p�X���[�h����͂��Ă��������ƃ��b�Z�[�W��\��
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
			RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jsp�ɖ߂�
			dis.forward(request, response);
		}
			
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/createaccount.jsp");//���e���ꂽ��createaccount.jsp�ɔ�΂�
		dis.forward(request, response);
		}
		
	}
}