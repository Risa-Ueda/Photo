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
			if(posts != 0) {//投稿数が0以上であれば
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