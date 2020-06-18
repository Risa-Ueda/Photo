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
@WebServlet("/CreateFinish")
public class CreateFinish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dto dto = null;
		Dao dao = null;
		String username = request.getParameter("username");//Daoからusernameを取得し変数usernameに代入
		String imagename = request.getParameter("imagename");//Daoからimagenameを取得し、変数imagenameに代入
		try {
			dao = new Dao();
			dto = dao.getPost(username, imagename);//usernameとimagenameを変数successに代入
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//System.out.println(dto.getId());
		if(dto != null) {//一致したら
			request.setAttribute("username", username);//usernameをusernameにセット
			request.setAttribute("imgname", imagename);//imagenameをimagenameにセット
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
			request.setAttribute("message", "!ユーザ名、画像ファイルが一致しません");
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//imagepost.jspに戻す
			dis.forward(request, response);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/createfinish.jsp");//create.jspに飛ばす
		dis.forward(request, response);
	}

}
