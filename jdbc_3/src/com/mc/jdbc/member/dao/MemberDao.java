package com.mc.jdbc.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mc.jdbc.common.uitl.JDBCTemplate;
import com.mc.jdbc.member.dto.Member;

// MVC2 ����
// Model
// DAO (Data Access Object)

// Persistence Layer 
// ���Ӽ� ���� : �����͸� ���������� �����ϱ� ���� DB�� ��ȣ�ۿ��ϴ� Layer

// �ʿ��� �����͸� DBMS�� ��û
// DBMS�� ���� �о�� �����͸� ���ø����̼� ������ ����ϱ� ������ ���·� �Ľ�
public class MemberDao {
	
	private JDBCTemplate jdt = JDBCTemplate.getInstance();

	// ����� ����
	public Member userAuthenticate(Connection conn, String userId, String password) {

		Member member = null;
		String query = "select * from member where user_id = '" + userId + "' and password = '" + password + "'";
		
		try(Statement stmt = conn.createStatement()) {
			try(ResultSet rset = stmt.executeQuery(query)){
				
				while (rset.next()) {
					member = generateMember(rset);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return member;
	}

	private Member generateMember(ResultSet rset) throws SQLException {
		Member member;
		member = new Member();
		member.setUserId(rset.getString("user_id"));
		member.setPassword(rset.getString("password"));
		member.setGrade(rset.getString("grade"));
		member.setTell(rset.getString("tell"));
		member.setEmail(rset.getString("email"));
		member.setLeave(rset.getBoolean("is_leave"));
		member.setRegDate(rset.getTimestamp("reg_date").toLocalDateTime());
		member.setRentableDate(rset.getTimestamp("rentable_date").toLocalDateTime());
		return member;
	}

	public boolean insertMember(Member member) {
		
		boolean res = false;
		String sql = "insert into member(user_id, password, email, grade, tell, rentable_date)"
				+ " values('"+ member.getUserId() +"',"
					+ "'" + member.getPassword() + "',"
					+ "'" + member.getEmail() + "',"
					+ "'" + member.getGrade() + "',"
					+ "'" + member.getTell() + "',"
					+ " now() "	
					+ ")";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = jdt.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			res = true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("dao���� ����");
			
		} finally {
			jdt.close(stmt);
		}
		
		return res;
	}
	
	//userId�� ��й�ȣ��
	//�Ű������� �޾ƿ� password�� �����ϴ� �ڵ带 �ۼ��Ͻÿ�. 
	public int changePassword(String userId, String password) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		int res = 0;

		String sql = "update member set password = ? where user_id = ?" ;
		
		try {
			
			conn = jdt.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, password); 
			pstm.setString(2, userId);
			res = pstm.executeUpdate();
			
			jdt.commit(conn);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			jdt.rollback(conn);
			
		} finally {
			jdt.close(pstm);
			jdt.close(conn);
		}
		
		return res;
	}
	
	
	// ���ϴ� ������� ���̵� �����ϴ� �޼���
	public int deleteUser(String userId) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "delete from member where user_id = ?" ;
		
		try {
			
			conn = jdt.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			res = pstm.executeUpdate();
			
			jdt.commit(conn);
			
		}  catch (SQLException e) {
			
			e.printStackTrace();
			jdt.rollback(conn);
			
		} finally {
			jdt.close(pstm);
			jdt.close(conn);
		}
		
		return res;
		
	}

	public List<Member> selectAllMember(Connection conn) {
		
		List<Member> members = new ArrayList<Member>();
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement("select * from member");
			ResultSet rset = pstm.executeQuery();
			
			while(rset.next()) {
				members.add(generateMember(rset));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return members;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}