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
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
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
		Dao dao = null;
		int posts = 0;//postsを数値で定義
		String username = request.getParameter("username");//usernameを取得
		ArrayList<Dto> postimage = null;//ArrayList<Dto>をpostimageという名の変数を定義
			
			try {
				dao = new Dao();//Daoと接続
				posts = dao.getuserPosts(username);//getuserPostsにusernameを引き渡して投稿数を検索
				System.out.println(username);//検索したusernameを表示
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(posts != 0) {//投稿数が0でなければ
				request.setAttribute("username", username);//usernameをセット
				request.setAttribute("posts", posts);//投稿数をセット
				
				try {
					postimage = dao.getUserListAll(username);//usernameで受け取ったユーザーの投稿のみ表示
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("post", postimage);//postという文字列をpostimageという名前で保存し全投稿をpostにもっていく
			}else {
				request.setAttribute("username", username);
				request.setAttribute("message", "まだ投稿されていません。");
			}
			
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/profileone.jsp");//profileone.jspに飛ばす
			dis.forward(request, response);
			
		}
	}