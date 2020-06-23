package dimg;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
		//アップロードされたファイルを保存するメソッド
		request.setCharacterEncoding("utf-8");
		String name = null;//画像名を取得する変数
		Part part = request.getPart("image");//Part型で画像のファイルを取得
        name = this.getFileName(part);//画像名を取得
        System.out.println(name);//画像名を表示
        Path filePath = Paths.get("C:\\Users\\Risa\\eclipse-workspace\\photo\\WebContent" + File.separator + name);//WebContentにファイルを保存することを決める
        InputStream in = part.getInputStream();//画像を取得
        Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);//ファイルをコピーし画像を保存
        request.setAttribute("imgname", name);//create.jspにファイル名を送る        
        ServletContext context = getServletContext();
		RequestDispatcher dis = context.getRequestDispatcher("/post.jsp");//create.jspに飛ばす
		dis.forward(request, response);
	}	
	
	private String getFileName(Part part) {
		//画像名を取得するメソッド
        String name = null;//nameに画像名を取得する変数
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