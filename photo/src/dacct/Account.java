package dacct;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DBAccess;
import dao.Dao;
import dto.Dto;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String button = request.getParameter("button");
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//create.jspからusernameを取得し変数usernameに代入
		String password = request.getParameter("password");//create.jspからimagenameを取得し、変数imagenameに代入
		
		if(button.equals("login")){//loginのボタンが押されたら
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jspに飛ばす
			dis.forward(request, response);
		}else if(button.equals("create")){//createのボタンが押されたら
			
			if(username == null || username.isEmpty() || password == null || password.isEmpty()) {//usernameとpasswordの中に値が入っていない場合
				request.setAttribute("message", "!ユーザ名、パスワードを入力してください");//!ユーザ名、パスワードを入力してくださいとメッセージを表示
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/createaccount.jsp");//createaccount.jspにページを戻す
				dis.forward(request, response);
			}else if(username != null || password != null) {//usernameとpasswordが入力されている場合
				request.setAttribute("username", username);//usernameをusernameにセット
				request.setAttribute("password", password);//passwordをpasswordにセット
				AcCreate dbAccess = new AcCreate();//AcCreateメソッドでアカウント作成
				
				try {
					((DBAccess) dbAccess).execute(request);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Account created!");
				
		}else {//それ以外
			request.setAttribute("message", "!エラーが発生しました");
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/login.jsp");//login.jspに戻す
			dis.forward(request, response);
		}
			
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/createaccount.jsp");//投稿されたらcreateaccount.jspに飛ばす
		dis.forward(request, response);
		}
		
	}
}