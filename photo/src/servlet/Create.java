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
import dto.Dto;

/**
 * Servlet implementation class ImageHome
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//��������
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//Dao����username���擾���ϐ�username�ɑ��
		String imagename = request.getParameter("imagename");//Dao����imagename���擾���A�ϐ�imagename�ɑ��
		String comment = request.getParameter("comment");
		System.out.println(imagename);
		try {
			dao = new Dao();
			dto = dao.getPost(username, imagename);//username��imagename��ϐ�success�ɑ��
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		//System.out.println(dto.getId());
		if(imagename != null) {//imagename�̒l��DB�ɓ����Ă����ꍇ
			request.setAttribute("username", username);//username��username�ɃZ�b�g
			request.setAttribute("imgname", imagename);//imagename��imagename�ɃZ�b�g
			request.setAttribute("comment", comment);
			Insert dbAccess = new Insert();
			try {
				dbAccess.execute(request);
			} catch (SQLException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			System.out.println("posted!");
			
		}else if(username == null || username.isEmpty() || imagename == null || imagename.isEmpty()) {
			request.setAttribute("message", "!���[�U���Aimagename����͂��Ă�������");//!���[�U���Aimagename����͂��Ă��������ƃ��b�Z�[�W��\��
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//create.jsp�Ƀy�[�W��߂�
			dis.forward(request, response);
		}else {//����ȊO
			request.setAttribute("message", "!�G���[���������܂���");
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//imagepost.jsp�ɖ߂�
			dis.forward(request, response);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/createfinish.jsp");//create.jsp�ɔ�΂�
		dis.forward(request, response);
	}

}