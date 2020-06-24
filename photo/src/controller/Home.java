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
		String button = request.getParameter("button");//buttonが押されたら
			if(button.equals("image")){	//imageのボタンが押されたら
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//image.jspに飛ばす
				dis.forward(request, response);
			} else if(button.equals("profile")){//likeのボタンが押されたら
				Dao dao = null;
				ArrayList<Dto> postimage = null;//ArrayListをpostimageで定義
				try {
					dao = new Dao();//Daoに接続
					postimage = dao.getUserAll();//daoのgetListAllのメソッドをpostimageに代入(全部の投稿を抽出)			
					request.setAttribute("post", postimage);//postという文字列をpostimageという名前で保存					
				} catch (SQLException e) {
					e.printStackTrace();
				}				
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/profile.jsp");//like.jspに飛ばす
				dis.forward(request, response);
			} else if(button.equals("home")){//homeのボタンが押されたら
				Dao dao = null;
				ArrayList<Dto> postimage = null;//ArrayListをpostimageで定義
				try {
					dao = new Dao();//Daoに接続
					postimage = dao.getListAll();//daoのgetListAllのメソッドをpostimageに代入(全部の投稿を抽出)			
					request.setAttribute("post", postimage);//postという文字列をpostimageという名前で保存					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/home.jsp");//home.jspに飛ばす
				dis.forward(request, response);
				
			} else if(button.equals("delete")){//deleteのボタンが押されたら
				Dao dao = null;
				String id = request.getParameter("id");//idを取得
				try {
					dao = new Dao();//Daoに接続
					dao.deleteData(id);//引数のidの投稿を削除
					System.out.println(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/delete.jsp");//delete.jspに飛ばす
				dis.forward(request, response);
				
			} else {
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jspに飛ばす
				dis.forward(request, response);
			}
	}
}