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
		request.setCharacterEncoding("utf-8"); // セッションの取得(なければnullが返ってくる)
		HttpSession session = request.getSession(false); // セッションの破棄
		if(session != null) session.invalidate(); // ログイン失敗時、ログアウト時、不正操作時以外の場合
		if(request.getAttribute("message") == null) request.setAttribute("message", "名前とパスワードを入力してください"); //messageがnullの場合
		response.setContentType("text/html; charset=UTF-8");
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jspに飛ばす
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String button = request.getParameter("button");//buttonが押されたら
		
			if(button.equals("image")){	//imageのボタンが押されたら
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//image.jspに飛ばす
				dis.forward(request, response);
				
			} else if(button.equals("profile")){//likeのボタンが押されたら
				Dao dao = null;
				ArrayList<Dto> alluser = null;//ArrayListをalluserで定義
			
				try {
					dao = new Dao();//Daoに接続
					alluser = dao.getUserAll();//daoのgetListAllのメソッドをalluserに代入(全部の投稿を抽出)			
					request.setAttribute("post", alluser);//postという文字列をalluserという名前で保存					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/profile.jsp");//profile.jspに飛ばす
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
				
			}else {
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jspに飛ばす
				dis.forward(request, response);
			}
	}
}