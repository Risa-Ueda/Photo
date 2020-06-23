package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
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
@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = null;
		boolean success = false;
		String name = request.getParameter("name");//Dao����name���擾
		String pass = request.getParameter("pass");//Dao����pass���擾
		ArrayList<Dto> postimage = null;//ArrayList<Dto>��postimage�Ƃ������̕ϐ����`
		String button = request.getParameter("button");//button�������ꂽ��
		
		if(button.equals("login")){//login�{�^���������ꂽ��
			try {
				dao = new Dao();
				success = dao.getLoginInfo(name, pass);//successs�̕ϐ���name��pass�̒l�����AgetLogininfo�Ŏ擾
				postimage = dao.getListAll();//dao��getListAll�̃��\�b�h��postimage�ɑ��(�S���̓��e�𒊏o)			
				request.setAttribute("post", postimage);//post�Ƃ����������postimage�Ƃ������O�ŕۑ�
				HttpSession session = request.getSession();
				session.setAttribute("name", name);//username���Z�b�V�����ŕێ�
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(success) {//Dao��name��pass�A���͂��ꂽname��pass����v�����ꍇ
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/home.jsp");//home.jsp�ɔ�΂�
				dis.forward(request, response);
			} else if(name == null || name.isEmpty() || pass == null || pass.isEmpty()) {//�������͂���Ȃ������ꍇ�A��v���Ȃ������ꍇ
				request.setAttribute("message", "!���[�U���A�p�X���[�h����͂��Ă�������");//!���[�U���A�p�X���[�h����͂��Ă��������ƃ��b�Z�[�W��\��
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jsp�Ƀy�[�W��߂�
				dis.forward(request, response);	
			}else {//����ȊO
				request.setAttribute("message", "���O�C���Ɏ��s���܂���"); //���O�C���Ɏ��s���܂����ƃ��b�Z�[�W�\��
				doGet(request, response);
			}
		} else if(button.equals("createaccount")){//createaccount�������ꂽ��	
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/createaccount.jsp");//createaccount.jsp�Ƀy�[�W��߂�
			dis.forward(request, response);
		}
	}
}