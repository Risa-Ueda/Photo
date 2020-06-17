package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

//import dao.Dao;
import dto.Dto;

/**
 * DBAccess�C���^�[�t�F�[�X���������錟���N���X<br>
 * �S�Ẵf�[�^��ArrayList�Ƃ��Ď擾����
 * @author user
 *
 */
public class Select implements DBAccess {
	
	public void execute(HttpServletRequest request) throws SQLException { //�T�[�u���b�g�ł��g���Ă��郊�N�G�X�g���������Ŏ󂯂�
		
		Dao dao=null; //Dao�^��dao�ϐ���錾
		try {
			dao=new Dao(); //��ao�N���X�̃C���X�^���X��
			ArrayList<Dto>list=dao.getListAll(); //��ao�N���X��getListAll�Ɠ����C�f�[�^�x�[�X�̊e�s�̔z�񂪂����ŕԂ��Ă���
			
			if(list !=null) { //list(�c�C�[�g�ꗗ)��null����Ȃ�������
				request.setAttribute("list",list); //request�X�R�[�v��"list"�Ƃ������O��list�ϐ���ݒ�
			}else { //list�������Ȃ�������
				request.setAttribute("message","�܂��f�[�^������܂���");
			}
		}finally {
			if(dao !=null)dao.close(); //dao��null����Ȃ�������f�[�^�x�[�X�Ƃ̒ʐM��ؒf
		}
		
	}
}