package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class ImageHome
 */
@WebServlet("/Create")
public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//文字化け
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//Daoからusernameを取得し変数usernameに代入
		String imagename = request.getParameter("imagename");//Daoからimagenameを取得し、変数imagenameに代入
		String comment = request.getParameter("comment");
		System.out.println(imagename);
		try {
			dao = new Dao();
			dto = dao.getPost(username, imagename);//usernameとimagenameを変数successに代入
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//System.out.println(dto.getId());
		if(imagename != null) {//imagenameの値がDBに入っていた場合
			request.setAttribute("username", username);//usernameをusernameにセット
			request.setAttribute("imgname", imagename);//imagenameをimagenameにセット
			request.setAttribute("comment", comment);
			Insert dbAccess = new Insert();
			try {
				dbAccess.execute(request);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			System.out.println("posted!");
			
		}else if(username == null || username.isEmpty() || imagename == null || imagename.isEmpty()) {
			request.setAttribute("message", "!ユーザ名、imagenameを入力してください");//!ユーザ名、imagenameを入力してくださいとメッセージを表示
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//create.jspにページを戻す
			dis.forward(request, response);
		}else {//それ以外
			request.setAttribute("message", "!エラーが発生しました");
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//imagepost.jspに戻す
			dis.forward(request, response);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/createfinish.jsp");//create.jspに飛ばす
		dis.forward(request, response);
	}

}