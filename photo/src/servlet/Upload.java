package servlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import dao.Dao;
/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
@MultipartConfig
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String button = request.getParameter("button");
		
		// 画像名を受け取る変数
		String name = null;
			// 画像パラメータ受け取り
			Part part = request.getPart("image");
			
			// 画像名取得
	        name = this.getFileName(part);
	        System.out.println(name);
	        
	        // 画像保管場所指定(ワークスペースのwebContentを指定)
	        Path filePath = Paths.get("C:\\Users\\Risa\\eclipse-workspace\\photo\\WebContent" + File.separator + name);
	        // 上記ディレクトリに画像を保存
	        InputStream in = part.getInputStream();
	        Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
		
		// 画像名を受け取るリスト
		ArrayList<String> list = null;
		
        try {
			Dao dao = new Dao();
			// 画像がアップロードされた場合
			if(name != null) {
				// 画像名をDBに保存
				dao.setImageName(name);
			} else if(button.equals("view")) {
			// 全画像名をDBから取得
			list = dao.getNameAll();
			dao.close();
			request.setAttribute("images", list);
			System.out.println(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        request.setAttribute("imgname", name);
        
        ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/create.jsp");//home.jspに飛ばす
		dis.forward(request, response);
	}
	
	// 画像名取得メソッド
	private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}