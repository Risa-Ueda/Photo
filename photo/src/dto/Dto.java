package dto;

/**
 * ���b�Z�[�W�����܂Ƃ߂ĕێ�����N���X<br>
 * �f�[�^�������Ɏg�p����A�ȉ��̃t�B�[���h������<br>
 * �E���eID<br>
 * �E���e���e<br>
 * �E���e����<br>
 * @author user
 *
 */
public class Dto { //MessageDto����s���̃f�[�^�������Ă���
	
	/**
	 * ���eID
	 */
	int id;
	
	String username;
	
	/**
	 * ���e���e
	 */
	String imgname;
	


	
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