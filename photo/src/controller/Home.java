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
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
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
		request.setCharacterEncoding("UTF-8");
		String button = request.getParameter("button");//button�������ꂽ��
		
			if(button.equals("image")){	//image�̃{�^���������ꂽ��
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//image.jsp�ɔ�΂�
				dis.forward(request, response);
				
			} else if(button.equals("profile")){//like�̃{�^���������ꂽ��
				Dao dao = null;
				ArrayList<Dto> alluser = null;//ArrayList��alluser�Œ�`
			
				try {
					dao = new Dao();//Dao�ɐڑ�
					alluser = dao.getUserAll();//dao��getListAll�̃��\�b�h��alluser�ɑ��(�S���̓��e�𒊏o)			
					request.setAttribute("post", alluser);//post�Ƃ����������alluser�Ƃ������O�ŕۑ�					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/profile.jsp");//profile.jsp�ɔ�΂�
				dis.forward(request, response);
				
			} else if(button.equals("home")){//home�̃{�^���������ꂽ��
				Dao dao = null;
				ArrayList<Dto> postimage = null;//ArrayList��postimage�Œ�`
				
				try {
					dao = new Dao();//Dao�ɐڑ�
					postimage = dao.getListAll();//dao��getListAll�̃��\�b�h��postimage�ɑ��(�S���̓��e�𒊏o)			
					request.setAttribute("post", postimage);//post�Ƃ����������postimage�Ƃ������O�ŕۑ�					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/home.jsp");//home.jsp�ɔ�΂�
				dis.forward(request, response);
				
			}else {
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jsp�ɔ�΂�
				dis.forward(request, response);
			}
	}
}