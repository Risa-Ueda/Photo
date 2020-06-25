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

import dao.Dao;
import dto.Dto;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = null;
		int posts = 0;//posts�𐔒l�Œ�`
		String username = request.getParameter("username");//username���擾
		ArrayList<Dto> postimage = null;//ArrayList<Dto>��postimage�Ƃ������̕ϐ����`
			try {
				dao = new Dao();//Dao�Ɛڑ�
				posts = dao.getuserPosts(username);//getuserPosts��username�������n���ē��e��������
				System.out.println(username);//��������username��\��
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(posts != 0) {//���e����0�ȏ�ł����
				request.setAttribute("username", username);//username���Z�b�g
				request.setAttribute("posts", posts);//���e�����Z�b�g
				try {
					postimage = dao.getUserListAll(username);//username�Ŏ󂯎�������[�U�[�̓��e�̂ݕ\��
				} catch (SQLException e) {
					e.printStackTrace();
				}	
				request.setAttribute("post", postimage);//post�Ƃ����������postimage�Ƃ������O�ŕۑ����S���e��post�ɂ����Ă���
			}else {
				request.setAttribute("username", username);
				request.setAttribute("message", "�܂����e����Ă��܂���B");
			}
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/profileone.jsp");//profileone.jsp�ɔ�΂�
			dis.forward(request, response);
		}
	}