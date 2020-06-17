package dao;//database�Ƃ̃A�N�Z�X��S������W���o
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
	 * DB�ڑ��R���X�g���N�^<br>
	 * �C���X�^���X������DB�ڑ����s����
	 * @throws SQLException
	 */
	public Dao() throws SQLException{//Dao�N���X�̃R���X�g���N�^/�f�[�^�[�x�[�X�ɐڑ����邽�߂̃R���X�g���N�^
		// �����ɏ������L�����Ă�������
		String url ="jdbc:mysql://localhost:3306/data?serverTimezone=UTC";//data�x�[�X������ꏊ
		String user = "root";//���[�U�[��
		String pass = "root";//�p�X���[�h
		con = DriverManager.getConnection(url, user, pass);//3�̉������̏����g���ăf�[�^�[�x�[�X�փA�N�Z�X����
		System.out.println("Connection success!");//�ڑ���������ƃR���\�[���Ɍ����
	}
	/**
	 * 
	 */
	public void close() { 
		try {
			if(con != null) con.close();//DB�ڑ���؂邽�߂̃��\�b�h 
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
				dto = new Dto();//dto�ɃC���X�^���X���������̂�^���A���b�Z�[�W����o�̃C���X�^���X�������Ă���
				dto.setId(rs.getInt("id"));//id��̒l���擾
				dto.setusername(rs.getString("username"));
				dto.setImagename(rs.getString("imgname"));
			}
		}finally {
			ps.close();
		}
		return dto;
	}
	
	public ArrayList<Dto> getListAll() throws SQLException{//DB�ɕۑ�����Ă���f�[�^��S���擾���郁�\�b�h/���b�Z�[�Wdto.java����s���̃f�[�^���擾����
		// �����ɏ������L�����Ă�������
		sql = " select * from post;";//sql���𕶎���Ƃ��Ĕz�u
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Dto> list = null;
		try {
			ps = con.prepareStatement(sql);//sql���̎��s����
			rs = ps.executeQuery();//
			list = new ArrayList<>();//ArrayList���C���X�^���X��
			Dto dto;
			while(rs.next()) {//rs.next�ɂ���ăJ�[�\�����ړ�����
				dto = new Dto();//dto�ɃC���X�^���X���������̂�^���A���b�Z�[�W����o�̃C���X�^���X�������Ă���
				dto.setId(rs.getInt("id"));//id��̒l���擾
				dto.setusername(rs.getString("username"));
				dto.setImagename(rs.getString("imgname"));//content��̕����X�g�����O�^�Ƃ��Ď󂯎��
				list.add(dto);
			}
			rs.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
		}finally {//�ǂ�
			ps.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
		}
		Comparator<Dto> comparator = Comparator.comparing(Dto::getId).reversed();
		
		return (ArrayList<Dto>) list.stream().sorted(comparator).collect(Collectors.toList());	
	}
	
}