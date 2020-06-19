package servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.DBAccess;
import dao.Dao;

/**
 * DBAccessインターフェースを実装する登録クラス<br>
 * パラメータに受取った入力値をDBに登録する
 * @author user
 *
 */
public class Insert implements DBAccess { //DBAccess

	public void execute(HttpServletRequest request) throws SQLException {//executeメソッド
		
		// ここに処理を記入してください
		Dao dao = null;
		String username = request.getParameter("username");
		String imgname = (String) request.getAttribute("imgname");
		System.out.println(imgname);
		String comment = request.getParameter("comment");//ユーザーからの入力を受け取っている
		
		try {
			dao = new Dao();//Daoクラスのコンストラクタでdbとつなげる
			if(dao.insertData(username, imgname, comment) > 0) {//inputとはユーザーがテキストをポストした回数
				request.setAttribute("message", "投稿完了!");
				System.out.println("Insert success!");
			}else {//なんらかの理由によりポストが出来なかった場合
				request.setAttribute("message", "投稿失敗...");
				System.out.println("Insert failed...");
			}
		}finally {
			if(dao != null) dao.close();
		}
	}
}