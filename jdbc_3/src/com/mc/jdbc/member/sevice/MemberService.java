package com.mc.jdbc.member.sevice;

import java.sql.Connection;
import java.util.List;

import com.mc.jdbc.common.uitl.JDBCTemplate;
import com.mc.jdbc.member.dao.MemberDao;
import com.mc.jdbc.member.dto.Member;

// MVC2����
// Model
// Service

// ����
// �����Ͻ� ����(��� ����)�� ����
// DB transaction ����
//		transaction : ���� �ּ� �۾� ����.
//	    commit/rollback�� Service���� ����

public class MemberService {
	
	private JDBCTemplate jdt = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	
	// ����������� ���� �����Ͻ� ����
	// ����������� ���� Ʈ������� �����ϰ� ����
	// Connection ��ü�� ������ ���Ḧ ���, commit/rollback
	public Member userAuthenticate(String userId, String password) {
		
		Connection conn = jdt.getConnection();
		
		try {
			// DataAccessObject���� ������� ���̵�� password�� �����͸� ��ȸ�� ���� ��û
			Member member = memberDao.userAuthenticate(conn, userId, password);
			return member;
			
		}finally {
			jdt.close(conn);
		}
		
	}

	public List<Member> selectAllMember() {
		
		Connection conn = jdt.getConnection();
		
		List<Member> members = null;
		
		try {
			members = memberDao.selectAllMember(conn);
		}finally {
			jdt.close(conn);
		}
		
		return members;
	}

	public void insertMember(Member member) {
		
		Connection conn = jdt.getConnection();
		
		try {
			
			if(memberDao.insertMember(member)) {
				//���� �� �۾�
			}else{
				//���� �� �۾�...
			};
			
			jdt.commit(conn);
		} catch(Exception e){
			jdt.rollback(conn);
			System.out.println("������ catch ���");
		}finally {
			jdt.close(conn);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}