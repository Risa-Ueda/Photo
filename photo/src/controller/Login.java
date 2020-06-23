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
@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dao dao = null;
		boolean success = false;
		String name = request.getParameter("name");//Daoからnameを取得
		String pass = request.getParameter("pass");//Daoからpassを取得
		ArrayList<Dto> postimage = null;//ArrayList<Dto>をpostimageという名の変数を定義
		String button = request.getParameter("button");//buttonが押されたら
		
		if(button.equals("login")){//loginボタンが押されたら
			try {
				dao = new Dao();
				success = dao.getLoginInfo(name, pass);//successsの変数にnameとpassの値を代入、getLogininfoで取得
				postimage = dao.getListAll();//daoのgetListAllのメソッドをpostimageに代入(全部の投稿を抽出)			
				request.setAttribute("post", postimage);//postという文字列をpostimageという名前で保存
				HttpSession session = request.getSession();
				session.setAttribute("name", name);//usernameをセッションで保持
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(success) {//Daoのnameとpass、入力されたnameとpassが一致した場合
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/home.jsp");//home.jspに飛ばす
				dis.forward(request, response);
			} else if(name == null || name.isEmpty() || pass == null || pass.isEmpty()) {//何も入力されなかった場合、一致しなかった場合
				request.setAttribute("message", "!ユーザ名、パスワードを入力してください");//!ユーザ名、パスワードを入力してくださいとメッセージを表示
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jspにページを戻す
				dis.forward(request, response);	
			}else {//それ以外
				request.setAttribute("message", "ログインに失敗しました"); //ログインに失敗しましたとメッセージ表示
				doGet(request, response);
			}
		} else if(button.equals("createaccount")){//createaccountが押されたら	
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/createaccount.jsp");//createaccount.jspにページを戻す
			dis.forward(request, response);
		}
	}
}