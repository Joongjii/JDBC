package com.mc.jdbc;

import com.mc.jdbc.member.dao.MemberDao;
import com.mc.jdbc.member.dto.Member;

public class Run {
	//JDBC API(application programming Interface)
	//java database connectivity application programming Interface
	
	//��� �����ͺ��̽��� �ڽ��� ��ǰ�� �ڹ� ���ø����̼��� ������ ����
	// JDBC API�� ������ ����ü�� ����
	// ��� �����ͺ��̽��� JDBC API�� ������ �ֱ� ������,
	//�ڹ� �����ڴ� ��� �����ͺ��̽��� ����� �������� �ٷ� �� �ִ�.
	
	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.userAuthenticate("admin","1234");
		System.out.println(member);
		
//		Member member2 = new Member();
//		member2.setUserId("super");
//		member2.setPassword("1234");
//		member2.setEmail("super@mc.com");
//		member2.setGrade("ROLE_ADMIN");
//		member2.setTell("010-0000-1133");
//		
//		int res = memberDao.insertMember(member2);
//		System.out.println(res);
		
		//int res = memberDao.changePassword("super", "abcd");
		
		// SQL injection ����
		//�����͸� ������Ű�� ���� �������� ������ ���۵��ϵ��� SQL������ ����
//		int res = memberDao.changePassword("super", "' or 1=1 or user_id = '");
//		System.out.println(res);
		
		int res = memberDao.deleteUser("admin");
		System.out.println(res);
		
	}
	
	
	
	
	
	
	
	
	
	
}
