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
		//���O�C������DB���烆�[�U�[���ƃp�X���[�h����v�����郁�\�b�h
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean success;
		sql = "SELECT * from user where username = ? and password = ?";//SQL��
		ps = con.prepareStatement(sql);//SQL����p��
		ps.setString(1, name);//name�̒l���Z�b�g
		ps.setString(2, pass);//pass�̒l���Z�b�g
		try {
			rs = ps.executeQuery();//���ʂ�rs�ɑ��
			success = rs.next();//���ʂ�login.java�Ɏ󂯓n��
		}finally {
			ps.close();
		}
		return success;
	}
	
	public boolean getImagename(String imagename) throws SQLException{
		//Image.java�ŉ摜���ƈ�v�����郁�\�b�h
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
		//create�y�[�W�Ń��[�U�[���Ɖ摜�t�@�C��������v�����郁�\�b�h
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
				dto.setImgname(rs.getString("imgname"));
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
				dto.setImgname(rs.getString("imgname"));
				dto.setComment(rs.getString("comment"));//content��̕����X�g�����O�^�Ƃ��Ď󂯎��
				list.add(dto);
			}
			rs.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
		}finally {//�ǂ�
			ps.close();//SQL���̕K�v���Ȃ��Ȃ������߃��\�[�X���J������
		}
		Comparator<Dto> comparator = Comparator.comparing(Dto::getId).reversed();//Id�̐����Ŕ�r���ĕ��בւ�������
		
		return (ArrayList<Dto>) list.stream().sorted(comparator).collect(Collectors.toList());	
	}
	
	public int insertData(String username, String imgname, String comment) throws SQLException{//�擾�����f�[�^�̓o�^���邽�߂̃��\�b�h/String input�̓��[�U�[���ł����񂾓��e/int�^�Ƃ��Ė߂��Ă���
		PreparedStatement ps = null;//psSQL���ǂ̃f�[�^�x�[�X�ɂǂ̂悤�ȃN�G���𑗂�̂���`
		int n = 0;//�g���C�u���b�N�̒��ɂ���Ɩ߂�l�Ƃ��ĔF������Ȃ�
		try {
			String sql = "INSERT INTO post (username, imgname)VALUES (?, ?, ?)";//?�̓��[�U�[���ł����񂾒l
			ps = con.prepareStatement(sql);
			ps.setString(1, username);//
			ps.setString(2, imgname);
			ps.setString(3, comment);
			n = ps.executeUpdate();//sql�̎��s��
	}finally {
		ps.close();
	}
	// �����ɏ������L�����Ă�������
	return n;//�R�[�h�F�؂�������������Ԃ��߂莮
	}
	
}