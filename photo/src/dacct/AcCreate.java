package dacct;

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
public class AcCreate implements DBAccess { //DBAccess

	public void execute(HttpServletRequest request) throws SQLException {
		Dao dao = null;
		String username = request.getParameter("username");//jspで入力されたusernameを取得
		String password = (String) request.getAttribute("password");//jspで入力されたpasswordを取得

		try {
			dao = new Dao();//Daoクラスのコンストラクタでdbとつなげる
		
			if(dao.createAccount(username, password) > 0) {//成功した数が0以上であれば
				request.setAttribute("message", "アカウント作成完了!");//messageにアカウント作成完了！をセット
				System.out.println("Insert success!");
			}else {//なんらかの理由によりポストが出来なかった場合
				request.setAttribute("message", "アカウント作成失敗...");//messageにアカウント作成失敗...をセット
				System.out.println("Insert failed...");
			}
		}finally {
			if(dao != null) dao.close();
		}
	}
}