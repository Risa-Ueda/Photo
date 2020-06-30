package dpost;

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
import dto.Dto;

/**
 * Servlet implementation class ImageHome
 */
@WebServlet("/Post")
public class Post extends HttpServlet {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//UTF-8�ŕ����𓝈�
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//create.jsp����username���擾���ϐ�username�ɑ��
		String imagename = request.getParameter("imagename");//create.jsp����imagename���擾���A�ϐ�imagename�ɑ��
		String comment = request.getParameter("comment");//create.jsp����comment���擾���A�ϐ�comment�ɑ��
		
		if(username == null || username.isEmpty() || imagename == null || imagename.isEmpty()) {//username��imagename�����͂���ĂȂ��ꍇ
			request.setAttribute("message", "!���[�U���Aimagename����͂��Ă�������");//!���[�U���Aimagename����͂��Ă��������ƃ��b�Z�[�W��\��
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/post.jsp");//create.jsp�Ƀy�[�W��߂�
			dis.forward(request, response);
			
		}else {
			request.setAttribute("username", username);//username��username�ɃZ�b�g
			request.setAttribute("imgname", imagename);//imagename��imagename�ɃZ�b�g
			request.setAttribute("comment", comment);//comment�ɃR�����g���Z�b�g
			Insert dbAccess = new Insert();//Insert���\�b�h�œ��e
			
			try {
				dbAccess.execute(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("posted!");
		}
		
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/postfinish.jsp");//���e���ꂽ��createfinish.jsp�ɔ�΂�
		dis.forward(request, response);
	}
}