package com.mc.jdbc.common.uitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	
private static final JDBCTemplate INSTANCE = new JDBCTemplate();
	
	//singleton pattern ������ ���α׷������� �ѹ��� ȣ��Ǵ� ������
	private JDBCTemplate() {
		
	
	try {
		//JVM�� cm=om.mysql.jdbc.Driver Ŭ������ ������ �ø��� �ڵ�
		//Ŭ���� ���� �����ʹ� Static������ �ö󰡱� ������ �ѹ��� ����̹��� ����ϸ� ���α׷� ���ᶧ ���� �޸𸮿� �������� �ʴ´�.
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
	}

 public static JDBCTemplate getInstance() {
	 return INSTANCE;
	 
	 
	 
 }
 public Connection getConnection() {
	  String url = "jdbc:mysql://localhost:3306/bookmanager?useUnicode=true&characterEncoding=utf8";
	  Connection conn = null;
	  
		try {
			conn= DriverManager.getConnection(url, "bm", "123qwe!@#QWE");
			
			//�����ڰ� Ʈ������ ������ ������ �� �ֵ��ΰ� ���� Ŀ���� ����
			conn.setAutoCommit(false);	 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
 }
 
public void commit(Connection conn) {
	
	try {
		conn.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void rollback(Connection conn) {
	try {
		conn.rollback();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}
	

public void close(ResultSet rset) {
		try {
			if(rset!= null&&rset.isClosed())rset.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
}

public void close(Statement stmt) {
	
		try {
			
			if(stmt != null&& !stmt.isClosed())stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

public void close(Connection conn) {
	
	try {
		if(conn != null&& !conn.isClosed()) conn.close();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}

	public void close(ResultSet rset, Statement stmt) {
		close(rset);
		close(stmt);
	}











	
}
