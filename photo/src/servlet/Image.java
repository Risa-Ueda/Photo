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

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean success = false;
		Dao dao = null;
		String imagename = request.getParameter("imagename");
		//String button = request.getParameter("button");	
		try {
			dao = new Dao();//Daoと接続
			success = dao.getImagename(imagename);//DBにあるimagenameを取得
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println(success);
		if(success) {
			request.setAttribute("imgname", imagename);//DBにあるimagenameをセット
			
		/*}else if(button.equals("upload")){
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/Upload.java");//上手くいかなければimage.jspに戻す
			dis.forward(request, response);*/
		
		
		}else {
			ServletContext context = getServletContext();
			RequestDispatcher dis = context.getRequestDispatcher("/image.jsp");//上手くいかなければimage.jspに戻す
			dis.forward(request, response);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//imagenameをセットできたらcreate.jspに飛ばす
		dis.forward(request, response);
	}

}
