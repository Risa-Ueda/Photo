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
public class Dto { //Dto����s���̃f�[�^�������Ă���
	
	int id;//id�̃Q�b�^�[�ƃZ�b�^�[
	
	String username;//username�̃Q�b�^�[�ƃZ�b�^�[
		
	String imgname;//imgname�̃Q�b�^�[�ƃZ�b�^�[
	
	String comment;//comment�̃Q�b�^�[�ƃZ�b�^�[
	
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
	
	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}