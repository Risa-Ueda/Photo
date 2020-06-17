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
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean success;
		sql = "SELECT * from user where username = ? and password = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, pass);
		try {
			rs = ps.executeQuery();
			success = rs.next();
		}finally {
			ps.close();
		}
		return success;
	}
	
	public boolean getImagename(String imagename) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean success;
		sql = "SELECT * from imagename where imgname = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, imagename);
		try {
			rs = ps.executeQuery();
			success = rs.next();
		}finally {
			ps.close();
		}
		return success;
	}
	
	public Dto getPost(String username, String imgname) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Dto dto = null;
		sql = "SELECT * from post where username = ? and imgname = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, imgname);
		try {
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new Dto();//dtoにインスタンス化したものを与え、メッセージｄｔoのインスタンス化をしている
				dto.setId(rs.getInt("id"));//id列の値を取得
				dto.setusername(rs.getString("username"));
				dto.setImagename(rs.getString("imgname"));
			}
		}finally {
			ps.close();
		}
		return dto;
	}
	
	public ArrayList<Dto> getListAll() throws SQLException{//DBに保存されているデータを全件取得するメソッド/メッセージdto.javaが一行分のデータを取得する
		// ここに処理を記入してください
		sql = " select * from post;";//sql文を文字列として配置
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dto> list = null;
		try {
			ps = con.prepareStatement(sql);//sql文の実行準備
			rs = ps.executeQuery();//
			list = new ArrayList<>();//ArrayListをインスタンス化
			Dto dto;
			while(rs.next()) {//rs.nextによってカーソルが移動する
				dto = new Dto();//dtoにインスタンス化したものを与え、メッセージｄｔoのインスタンス化をしている
				dto.setId(rs.getInt("id"));//id列の値を取得
				dto.setusername(rs.getString("username"));
				dto.setImagename(rs.getString("imgname"));//content列の文をストリング型として受け取る
				list.add(dto);
			}
			rs.close();//SQL自体必要がなくなったためリソースを開放する
		}finally {//どの
			ps.close();//SQL自体必要がなくなったためリソースを開放する
		}
		Comparator<Dto> comparator = Comparator.comparing(Dto::getId).reversed();
		
		return (ArrayList<Dto>) list.stream().sorted(comparator).collect(Collectors.toList());	
	}
	
}