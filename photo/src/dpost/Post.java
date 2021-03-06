package dpost;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class ImageHome
 */
@WebServlet("/Post")
public class Post extends HttpServlet {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//UTF-8で文字を統一
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//create.jspからusernameを取得し変数usernameに代入
		String imagename = request.getParameter("imagename");//create.jspからimagenameを取得し、変数imagenameに代入
		String comment = request.getParameter("comment");//create.jspからcommentを取得ｓ、変数commentに代入
		
		if(username == null || username.isEmpty() || imagename == null || imagename.isEmpty()) {//usernameとimagenameが入力されてない場合
			request.setAttribute("message", "!ユーザ名、imagenameを入力してください");//!ユーザ名、imagenameを入力してくださいとメッセージを表示
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/post.jsp");//create.jspにページを戻す
			dis.forward(request, response);
			
		}else {
			request.setAttribute("username", username);//usernameをusernameにセット
			request.setAttribute("imgname", imagename);//imagenameをimagenameにセット
			request.setAttribute("comment", comment);//commentにコメントをセット
			Insert dbAccess = new Insert();//Insertメソッドで投稿
			
			try {
				dbAccess.execute(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("posted!");
		}
		
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/postfinish.jsp");//投稿されたらcreatefinish.jspに飛ばす
		dis.forward(request, response);
	}
}