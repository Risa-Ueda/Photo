package dao;//databaseとのアクセスを担当するジャバ
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import dto.Dto;

public class Dao {
	private Connection con;
	private String sql;
	/**
	 * DB接続コンストラクタ<br>
	 * インスタンス化時にDB接続が行われる
	 * @throws SQLException
	 */
	public Dao() throws SQLException{//Daoクラスのコンストラクタ/データーベースに接続するためのコンストラクタ
		// ここに処理を記入してください
		String url ="jdbc:mysql://localhost:3306/data?serverTimezone=UTC";//dataベースがある場所
		String user = "root";//ユーザー名
		String pass = "root";//パスワード
		con = DriverManager.getConnection(url, user, pass);//3つの仮引数の情報を使ってデーターベースへアクセスする
		System.out.println("Connection success!");//接続成功するとコンソールに現れる
	}
	/**
	 * 
	 */
	public void close() { 
		try {
			if(con != null) con.close();//DB接続を切るためのメソッド 
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public boolean getLoginInfo(String name, String pass) throws SQLException{
		//ログイン時にDBからユーザー名とパスワードを一致させるメソッド
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean success;
		sql = "SELECT * from user where username = ? and password = ?";//SQL文
		ps = con.prepareStatement(sql);//SQL文を用意
		ps.setString(1, name);//nameの値をセット
		ps.setString(2, pass);//passの値をセット
		try {
			rs = ps.executeQuery();//結果をrsに代入
			success = rs.next();//結果をlogin.javaに受け渡し
		}finally {
			ps.close();
		}
		return success;
	}
	
	public int createAccount(String username, String password) throws SQLException{
		//取得したデータの登録するためのメソッド
		PreparedStatement ps = null;//psSQLをどのデータベースにどのようなクエリを送るのか定義
		int n = 0;//トライブロックの中にいると戻り値として認識されない
		try {
			String sql = "INSERT INTO user (username, password)VALUES (?, ?)";//引数のusername,imgname,commentを受け取りsql文でインサート
			ps = con.prepareStatement(sql);//sql文を用意
			ps.setString(1, username);//usernameの値をセット
			ps.setString(2, password);//imgnameの値をセット
			n = ps.executeUpdate();//sqlの実行文
	}finally {
		ps.close();//閉じる
	}
	return n;//コード認証が成功した数を返す戻り式
	}
	
	public int setImageName(String imageName) throws SQLException {
		//Upload.javaでアップロードされたファイル名を登録
        int n = 0;
        sql = " insert into post(imgname) values(?)";//image.jspで取得したファイル名をsql文でインサート
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);//sql文の実行準備
            ps.setString(1, imageName);//imagenameにファイル名をセット
            n = ps.executeUpdate();//sql文を実行
        }finally {
            ps.close();//SQL自体必要がなくなったためリソースを開放する
        }
        return n;
    }
		
	public ArrayList<Dto> getListAll() throws SQLException{
		//DBに保存されているデータを全件取得するメソッド/メッセージdto.javaが一行分のデータを取得する
		sql = " SELECT * from post where username is not null;";//sql文を文字列として配置
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dto> list = null;
		try {
			ps = con.prepareStatement(sql);//sql文の実行準備
			rs = ps.executeQuery();//sql文実行
			list = new ArrayList<>();//ArrayListをインスタンス化
			Dto dto;
			while(rs.next()) {//rs.nextによってカーソルが移動する
				dto = new Dto();//dtoにインスタンス化したものを与え、メッセージｄｔoのインスタンス化をしている
				dto.setId(rs.getInt("id"));//id列の値を取得
				dto.setusername(rs.getString("username"));//username列の値を取得
				dto.setImgname(rs.getString("imgname"));//imgname列の値を取得
				dto.setComment(rs.getString("comment"));//content列の値を取得
				list.add(dto);//listに追加
			}
			rs.close();//SQL自体必要がなくなったためリソースを開放する
		}finally {
			ps.close();//SQL自体必要がなくなったためリソースを開放する
		}
		Comparator<Dto> comparator = Comparator.comparing(Dto::getId).reversed();//Idの数字で比較して並べ替えをする
		
		return (ArrayList<Dto>) list.stream().sorted(comparator).collect(Collectors.toList());	
	}
	
	public boolean getImagename(String imagename) throws SQLException{
		//Image.javaで画像名と一致させるメソッド
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean success;
		sql = "SELECT * from post where imgname = ?";//postテーブルからimgnameが同じものが存在するかどうか
		ps = con.prepareStatement(sql);
		ps.setString(1, imagename);//image.jspで受け取った文字列をセット
		try {
			rs = ps.executeQuery();
			success = rs.next();//結果を返す
		}finally {
			ps.close();//Daoを閉じる
		}
		return success;//結果を返す
	}
		
	public int insertData(String username, String imgname, String comment) throws SQLException{
		//取得したデータの登録するためのメソッド
		PreparedStatement ps = null;//psSQLをどのデータベースにどのようなクエリを送るのか定義
		int n = 0;//トライブロックの中にいると戻り値として認識されない
		try {
			String sql = "INSERT INTO post (username, imgname, comment)VALUES (?, ?, ?)";//引数のusername,imgname,commentを受け取りsql文でインサート
			ps = con.prepareStatement(sql);//sql文を用意
			ps.setString(1, username);//usernameの値をセット
			ps.setString(2, imgname);//imgnameの値をセット
			ps.setString(3, comment);//commentの値をセット
			n = ps.executeUpdate();//sqlの実行文
	}finally {
		ps.close();//閉じる
	}
	return n;//コード認証が成功した数を返す戻り式
	}
	
	public int deletePost(String id) throws SQLException {
		//投稿を削除するメソッド
		PreparedStatement ps = null;//psSQLをどのデータベースにどのようなクエリを送るのか定義
		int n = 0;//トライブロックの中にいると戻り値として認識されない
		try {
			String sql = "delete from post where id = ?";//?はユーザーが打ち込んだ値、受け取ったidから投稿を検索
			ps = con.prepareStatement(sql);
			ps.setString(1, id);//idの値をを取得
			n = ps.executeUpdate();//sqlの実行文
		}finally {
			ps.close();//閉じる
		}
		return n;//コード認証が成功した数を返す戻り式
		}	
	
	public int countPost(String username) throws SQLException {
		//投稿を数えるメソッド
		PreparedStatement ps = null;//psSQLをどのデータベースにどのようなクエリを送るのか定義
		int n = 0;//トライブロックの中にいると戻り値として認識されない
		try {
			String sql = "select count(*) from post where username is not null and username = ? ";//?はユーザーが打ち込んだ値
			ps = con.prepareStatement(sql);
			ps.setString(1, username);//idの値をを取得
			n = ps.executeUpdate();//sqlの実行文
		}finally {
			ps.close();//閉じる
		}
		return n;//コード認証が成功した数を返す戻り式
	}
	
	public ArrayList<Dto> getUserAll() throws SQLException{
		//DBに保存されているユーザーを全件取得するメソッド、profile.jspで選択肢のボタンを表示
		sql = "SELECT username from user;";//sql文を文字列として配置、userテーブルから全ユーザーを取得
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dto> list = null;
		try {
			ps = con.prepareStatement(sql);//sql文の実行準備
			rs = ps.executeQuery();//sql文実行
			list = new ArrayList<>();//ArrayListをインスタンス化
			Dto dto;
			while(rs.next()) {//rs.nextによってカーソルが移動する
				dto = new Dto();//dtoにインスタンス化したものを与え、メッセージｄｔoのインスタンス化をしている
				dto.setusername(rs.getString("username"));//ユーザーの名前のみセット
				list.add(dto);//listに追加
			}
			rs.close();//SQL自体必要がなくなったためリソースを開放する
		}finally {
			ps.close();//SQL自体必要がなくなったためリソースを開放する
		}
		Comparator<Dto> comparator = Comparator.comparing(Dto::getId).reversed();//Idの数字で比較して並べ替えをする
		
		return (ArrayList<Dto>) list.stream().sorted(comparator).collect(Collectors.toList());	
	}
	
	public int getuserPosts(String username) throws SQLException{
		//投稿数を表示するメソッド
		PreparedStatement ps = null;
		ResultSet rs = null;
		int posts = 0;
		sql = "select count(id) from post where username = ?";//usernameを受け取ってそのユーザーの投稿数をカウント
		ps = con.prepareStatement(sql);
		ps.setString(1, username);//usernameをセット
		try {
			rs = ps.executeQuery();//結果を受け取る
			rs.next();//結果の部分にカーソルを乗せる
			posts = rs.getInt("count(id)");//successにcount(id)の部分の値を数値として受け取る
			System.out.println(posts);//投稿数を表示
		}finally {
			ps.close();
		}
		return posts;
	}
	
	public ArrayList<Dto> getUserListAll(String username) throws SQLException{
		//DBに保存されている一人のユーザーのデータを全件取得するメソッド
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dto> list = null;
		sql = "SELECT * from post where username = ?;";//usernameを?に引き渡し、その個人の投稿を取得
		try {
			ps = con.prepareStatement(sql);//sql文の実行準備
			ps.setString(1, username);//usernameをセット
			rs = ps.executeQuery();//sql文実行
			list = new ArrayList<>();//ArrayListをインスタンス化
			Dto dto;
			while(rs.next()) {//rs.nextによってカーソルが移動する
				dto = new Dto();//dtoにインスタンス化したものを与え、メッセージｄｔoのインスタンス化をしている
				dto.setId(rs.getInt("id"));//id列の値を取得
				dto.setusername(rs.getString("username"));//username列の値を取得
				dto.setImgname(rs.getString("imgname"));//imgname列の値を取得
				dto.setComment(rs.getString("comment"));//comment列の値を取得
				list.add(dto);//listに追加
			}
			rs.close();//SQL自体必要がなくなったためリソースを開放する
		}finally {
			ps.close();//SQL自体必要がなくなったためリソースを開放する
		}
		Comparator<Dto> comparator = Comparator.comparing(Dto::getId).reversed();//Idの数字で比較して並べ替えをする
		
		return (ArrayList<Dto>) list.stream().sorted(comparator).collect(Collectors.toList());	
	}
	
}