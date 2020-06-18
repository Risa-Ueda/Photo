package dto;

/**
 * メッセージ情報をまとめて保持するクラス<br>
 * データ検索時に使用され、以下のフィールドを持つ<br>
 * ・投稿ID<br>
 * ・投稿内容<br>
 * ・投稿日時<br>
 * @author user
 *
 */
public class Dto { //Dtoが一行分のデータを持っている
	
	/**
	 * 投稿ID
	 */
	int id;//idのゲッターとセッター
	
	String username;//usernameのゲッターとセッター
	
	
	String imgname;//imgnameのゲッターとセッター
	
	String comment;

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	
	
	
	
}