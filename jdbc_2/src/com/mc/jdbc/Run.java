package com.mc.jdbc;

import com.mc.jdbc.member.dao.MemberDao;
import com.mc.jdbc.member.dto.Member;

public class Run {
	//JDBC API(application programming Interface)
	//java database connectivity application programming Interface
	
	//모든 데이터베이스는 자신의 제품과 자바 어플리케이션의 연결을 위해
	// JDBC API를 구현한 구현체를 제공
	// 모든 데이터베이스가 JDBC API를 따르고 있기 때문에,
	//자바 개발자는 모든 데이터베이스를 공통된 형식으로 다룰 수 있다.
	
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
		
		// SQL injection 공격
		//데이터를 오염시키기 위한 목적으로 쿼리가 오작동하도록 SQL쿼리를 주입
//		int res = memberDao.changePassword("super", "' or 1=1 or user_id = '");
//		System.out.println(res);
		
		int res = memberDao.deleteUser("admin");
		System.out.println(res);
		
	}
	
	
	
	
	
	
	
	
	
	
}
