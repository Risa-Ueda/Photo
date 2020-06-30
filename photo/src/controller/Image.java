package controller;

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

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
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
		boolean success = false;
		Dao dao = null;
		String imagename = request.getParameter("imagename");
		
			try {
				dao = new Dao();//Daoと接続
				success = dao.getImagename(imagename);//DBにあるimagenameを取得
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(success);
			
			if(success) {
				request.setAttribute("imgname", imagename);//DBにあるimagenameをセット
				
			}else {
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//上手くいかなければimage.jspに戻す
				dis.forward(request, response);
			}
			
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/post.jsp");//imagenameをセットできたらcreate.jspに飛ばす
			dis.forward(request, response);
		}
}