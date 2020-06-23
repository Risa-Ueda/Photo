package dpost;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import dao.DBAccess;
import dao.Dao;

/**
 * DBAccess�C���^�[�t�F�[�X����������o�^�N���X<br>
 * �p�����[�^�Ɏ��������͒l��DB�ɓo�^����
 * @author user
 *
 */
public class Insert implements DBAccess { //DBAccess

	public void execute(HttpServletRequest request) throws SQLException {
		Dao dao = null;
		String username = request.getParameter("username");//jsp�œ��͂��ꂽusername���擾
		String imgname = (String) request.getAttribute("imgname");//jsp�œ��͂��ꂽimgname���擾
		String comment = request.getParameter("comment");//���[�U�[����̓��͂��󂯎���Ă���
		try {
			dao = new Dao();//Dao�N���X�̃R���X�g���N�^��db�ƂȂ���
			if(dao.insertData(username, imgname, comment) > 0) {//input�Ƃ̓��[�U�[���e�L�X�g���|�X�g������
				request.setAttribute("message", "���e����!");
				System.out.println("Insert success!");
			}else {//�Ȃ�炩�̗��R�ɂ��|�X�g���o���Ȃ������ꍇ
				request.setAttribute("message", "���e���s...");
				System.out.println("Insert failed...");
			}
		}finally {
			if(dao != null) dao.close();
		}
	}
}