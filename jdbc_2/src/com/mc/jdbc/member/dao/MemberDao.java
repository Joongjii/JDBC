
package com.mc.jdbc.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		public Member userAuthenticate(String userId, String password) {

			Member member = null;
			String query = "select * from member where user_id = '" + userId + "' and password = '" + password + "'";
			
			try(Connection conn = jdt.getConnection();
				Statement stmt = conn.createStatement()) {
				try(ResultSet rset = stmt.executeQuery(query)){
					
					while (rset.next()) {
						member = new Member();
						member.setUserId(rset.getString("user_id"));
						member.setPassword(rset.getString("password"));
						member.setGrade(rset.getString("grade"));
						member.setTell(rset.getString("tell"));
						member.setEmail(rset.getString("email"));
						member.setLeave(rset.getBoolean("is_leave"));
						member.setRegDate(rset.getTimestamp("reg_date").toLocalDateTime());
						member.setRentableDate(rset.getTimestamp("rentable_date").toLocalDateTime());
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
			return member;
		}

		public int insertMember(Member member) {
			
			String sql = "insert into member(user_id, password, email, grade, tell, rentable_date)"
					+ " values('"+ member.getUserId() +"',"
						+ "'" + member.getPassword() + "',"
						+ "'" + member.getEmail() + "',"
						+ "'" + member.getGrade() + "',"
						+ "'" + member.getTell() + "',"
						+ " now() "	
						+ ")";
			
			int res = 0;
			
			Connection conn = null;
			Statement stmt = null;
			
			try {
				conn = jdt.getConnection();
				stmt = conn.createStatement();
				res = stmt.executeUpdate(sql);
				
				jdt.commit(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				jdt.rollback(conn);
			} finally {
				jdt.close(stmt);
				jdt.close(conn);
			}
			
			return res;
		}
		
		//userId�� ��й�ȣ��
		//�Ű������� �޾ƿ� password�� �����ϴ� �ڵ带 �ۼ��Ͻÿ�. 
		public int changePassword(String userId, String password) {
			
			Connection conn = null;
			
			// SQL Injecton ������ ����� �� �ִ� ���� ���ۿ� ��ü
			PreparedStatement pstm = null;
			int res = 0;
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
				conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
				
				//`USER_ID`, `PASSWORD`, `EMAIL`, `GRADE`, `TELL`, `IS_LEAVE`, `REG_DATE`, `RENTABLE_DATE`
				
				// PreparedStatement�� ���� ���ø�
				// ? �� ��� �����Ͱ� ���ڿ��� ��� �̸� �̽�������ó�� ���ش�.
				// ���ڿ��� Ư���� ���·� ��ȯ. �Էµ� ���ڿ� �������� �� �ڿ� ' �� �ٿ��ش�.
				// a -> 'a'
				
				String sql = "update member set password = ? where user_id = ?" ;
				System.out.println(sql);

				// 3. ���� ���ۿ� ��ü ����
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, password);  // ù�� ° ����ǥ�� password ���� �̽�������ó���Ͽ� �־���
				pstm.setString(2, userId);
				res = pstm.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return res;
		}
		
		
		// ���ϴ� ������� ���̵� �����ϴ� �޼���
		public int deleteUser(String userId) {
			
			Connection conn = null;
			
			// SQL Injecton ������ ����� �� �ִ� ���� ���ۿ� ��ü
			PreparedStatement pstm = null;
			int res = 0;
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
				conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
				
				//`USER_ID`, `PASSWORD`, `EMAIL`, `GRADE`, `TELL`, `IS_LEAVE`, `REG_DATE`, `RENTABLE_DATE`
				
				// PreparedStatement�� ���� ���ø�
				// ? �� ��� �����Ͱ� ���ڿ��� ��� �̸� �̽�������ó�� ���ش�.
				// ���ڿ��� Ư���� ���·� ��ȯ. �Էµ� ���ڿ� �������� �� �ڿ� ' �� �ٿ��ش�.
				// a -> 'a'
				
				String sql = "delete from member where user_id = ?" ;

				// 3. ���� ���ۿ� ��ü ����
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, userId);
				res = pstm.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstm.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return res;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}