package dao;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public interface DBAccess { //���[�U�[(����̓T�[�u���b�g)����f�[�^�x�[�X�ւ̃A�N�Z�X���ȒP�ɂł���悤��interface���
	public void execute(HttpServletRequest request) throws SQLException;
}