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
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button = request.getParameter("button");		//buttonが押されたら
			if(button.equals("image")){	//imageのボタンが押されたら
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//image.jspに飛ばす
				dis.forward(request, response);
			} else if(button.equals("like")){
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/like.jsp");//imagepost以外だったらlike.jspに飛ばす
				dis.forward(request, response);
			} else if(button.equals("home")){
				Dao dao = null;
				ArrayList<Dto> postimage = null;
				try {
					dao = new Dao();
					postimage = dao.getListAll();//daoのgetListAllのメソッドをpostimageに代入(全部の投稿を抽出)			
					request.setAttribute("post", postimage);//postという文字列をpostimageという名前で保存					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/home.jsp");//imagepost以外だったらlike.jspに飛ばす
				dis.forward(request, response);
			}else {
				//request.setAttribute("message", "!ページを選択してください");
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//imagepost以外だったらlike.jspに飛ばす
				dis.forward(request, response);
			}
	}
}