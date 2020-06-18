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
@WebServlet("/CreateFinish")
public class CreateFinish extends HttpServlet {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//Dao����username���擾���ϐ�username�ɑ��
		String imagename = request.getParameter("imagename");//Dao����imagename���擾���A�ϐ�imagename�ɑ��
		try {
			dao = new Dao();
			dto = dao.getPost(username, imagename);//username��imagename��ϐ�success�ɑ��
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		//System.out.println(dto.getId());
		if(dto != null) {//��v������
			request.setAttribute("username", username);//username��username�ɃZ�b�g
			request.setAttribute("imgname", imagename);//imagename��imagename�ɃZ�b�g
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
			request.setAttribute("message", "!���[�U���A�摜�t�@�C������v���܂���");
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//imagepost.jsp�ɖ߂�
			dis.forward(request, response);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/createfinish.jsp");//create.jsp�ɔ�΂�
		dis.forward(request, response);
	}

}
