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
public class Dto { //MessageDtoが一行分のデータを持っている
	
	/**
	 * 投稿ID
	 */
	int id;
	
	String username;
	
	/**
	 * 投稿内容
	 */
	String imagename;
	


	
	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
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