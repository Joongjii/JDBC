
package com.mc.jdbc.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mc.jdbc.member.dto.Member;

// MVC2 ����
// Model
// DAO (Data Access Object)

// Persistence Layer 
// ���Ӽ� ���� : �����͸� ���������� �����ϱ� ���� DB�� ��ȣ�ۿ��ϴ� Layer

// �ʿ��� �����͸� DBMS�� ��û
// DBMS�� ���� �о�� �����͸� ���ø����̼� ������ ����ϱ� ������ ���·� �Ľ�
public class MemberDao {

   // ����� ����
   public Member userAuthenticate(String userId, String password) {

      Member member = null;

      // DBMS���� ����(session)�� ����
      // transaction ������ ���� �޼��带 ���� ��ü
      // transaction ���� �� Connection ��ü ����
      // transaction ���� �� Connection�� ���� (close())
      Connection conn = null;

      // ���� ���ۿ� ��ü
      // select, insert, update, delete ���� ������ �����ϱ� ���� ��ü
      // �����ͺ��̽��� ������ sql���� �ۼ�
      Statement stmt = null;

      // Select ������ ����� ��ȯ�� �����͵��� ����
      // DBMS�� ���� ��ȸ�� �����͸� �о���� ��ü
      ResultSet rset = null;

      try {
         // JDBC �ڵ� ����
         // 1. jdbc driver ��ü�� jvm�� ���
         // Reflection�� ����� ���ϴ� DBMS�� ����̹� ��ü�� JVM�� ����� �� �ִ�.
         // �ν��Ͻ� ������ ���ڿ��� �ϰ�, �޼��峪 �ʵ忡 ������ �� �ִ� ���
         // private ���� ��ü�������α׷����� ��Ģ�� ���� ���� �ڹ��� ��Ģ�� �����ϰ� �޼��峪 �Ӽ� ���� ����� ��
         // �־ �����ϸ� ������� �ʴ� ���� ����.

         // �츮�� ����� DBMS�� Driver Ŭ������ JVM�� ���
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("����̹� �ε� ����");
         // 2. �����ͺ��̽��� ����
         // jdbc:mysql//<ip>:<port>/<database�̸�>?queryString
         String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
         conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");

         // 3. ���� ���ۿ� ��ü ����
         stmt = conn.createStatement();

         // 3-1. ���� �ۼ�
         String query = "select * from member where user_id = '" + userId + "' and password = '" + password + "'";

         rset = stmt.executeQuery(query);

         // resultSet���� �� row�� �� �پ� �о���� �ڵ�
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

      } catch (ClassNotFoundException e) {
         e.printStackTrace();

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            rset.close();
            stmt.close();
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }

      return member;
   }

   
   public int insertMember(Member member) {
      
      Connection conn = null;
      Statement stmt = null;
      int res = 0;
      
      try {
         
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
         conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
         
         // 3. ���� ���ۿ� ��ü ����
         stmt = conn.createStatement();
         
         //`USER_ID`, `PASSWORD`, `EMAIL`, `GRADE`, `TELL`, `IS_LEAVE`, `REG_DATE`, `RENTABLE_DATE`
         String sql = "insert into member(user_id, password, email, grade, tell, rentable_date)"
               + " values('"+ member.getUserId() +"',"
                  + "'" + member.getPassword() + "',"
                  + "'" + member.getEmail() + "',"
                  + "'" + member.getGrade() + "',"
                  + "'" + member.getTell() + "',"
                  + " now() "   
                  + ")";
         
        res = stmt.executeUpdate(sql);
         
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            stmt.close();
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      return res;
      
      
   }

   public int changePassword(String userId, String password) {
	
	   
	   Connection conn = null;
	   //SQL Injection ������ ����� �� �ִ� ���� ���ۿ� ��ü
	   PreparedStatement pstm = null;
	      int res = 0;
	      
	      try {
	         
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
	         conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
	         
	         // 3. ���� ���ۿ� ��ü ����
	    
	         
	         // `USER_ID`, `PASSWORD`, `EMAIL`, `GRADE`, `TELL`, `IS_LEAVE`, `REG_DATE`, `RENTABLE_DATE`
	         // ������� ���� ���ø�
	         // ? �� ��� ��Ƽ� ���ڿ��� ��� �̸� �̽������� ó�� ���ش�
	         // ���ڿ��� Ư���� ���·� ��ȯ���ش�
	         // a -> 'a'
	         
	         String sql = "update member set password = ? where user_id = ?" ;
	         System.out.println(sql);
	        
	        pstm = conn.prepareStatement(sql);
	        pstm.setString(1, password); //ù��° ����ǥ�� �н����� ���� �̽�������ó���Ͽ� �־���
	        pstm.setString(2, userId);
	        
	        
	        
	        System.out.println(pstm);
	        res = pstm.executeUpdate();
	         
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
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
   
   
   //���ϴ� ����� ���̵� �����ϴ� �޼���
   
   public int deleteUser(String userId) {

	
	   Connection conn = null;
	   //SQL Injection ������ ����� �� �ִ� ���� ���ۿ� ��ü
	   PreparedStatement pstm = null;
	      int res = 0;
	      
	      try {
	         
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
	         conn = DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
	         
	         // 3. ���� ���ۿ� ��ü ����
	    
	         
	         // `USER_ID`, `PASSWORD`, `EMAIL`, `GRADE`, `TELL`, `IS_LEAVE`, `REG_DATE`, `RENTABLE_DATE`
	         // ������� ���� ���ø�
	         // ? �� ��� ��Ƽ� ���ڿ��� ��� �̸� �̽������� ó�� ���ش�
	         // ���ڿ��� Ư���� ���·� ��ȯ���ش�
	         // a -> 'a'
	         
	         String sql = "delete from member where user_id = ?" ;
	        
	        pstm = conn.prepareStatement(sql);
	        pstm.setString(1, userId);
	        res = pstm.executeUpdate();
	         
	      } catch (ClassNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
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