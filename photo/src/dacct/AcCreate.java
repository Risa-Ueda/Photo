package dacct;

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
public class AcCreate implements DBAccess { //DBAccess

	public void execute(HttpServletRequest request) throws SQLException {
		Dao dao = null;
		String username = request.getParameter("username");//jsp�œ��͂��ꂽusername���擾
		String password = (String) request.getAttribute("password");//jsp�œ��͂��ꂽpassword���擾

		try {
			dao = new Dao();//Dao�N���X�̃R���X�g���N�^��db�ƂȂ���
		
			if(dao.createAccount(username, password) > 0) {//������������0�ȏ�ł����
				request.setAttribute("message", "�A�J�E���g�쐬����!");//message�ɃA�J�E���g�쐬�����I���Z�b�g
				System.out.println("Insert success!");
			}else {//�Ȃ�炩�̗��R�ɂ��|�X�g���o���Ȃ������ꍇ
				request.setAttribute("message", "�A�J�E���g�쐬���s...");//message�ɃA�J�E���g�쐬���s...���Z�b�g
				System.out.println("Insert failed...");
			}
		}finally {
			if(dao != null) dao.close();
		}
	}
}