package com.mc.jdbc.member.sevice;

import java.sql.Connection;
import java.util.List;

import com.mc.jdbc.common.uitl.JDBCTemplate;
import com.mc.jdbc.member.dao.MemberDao;
import com.mc.jdbc.member.dto.Member;

// MVC2패턴
// Model
// Service

// 역할
// 비지니스 로직(기능 구현)을 구현
// DB transaction 관리
//		transaction : 논리적 최소 작업 단위.
//	    commit/rollback을 Service에서 결정

public class MemberService {
	
	private JDBCTemplate jdt = JDBCTemplate.getInstance();
	private MemberDao memberDao = new MemberDao();
	
	// 사용자인증에 대한 비지니스 로직
	// 사용자인증에 대한 트랜잭션을 시작하고 종료
	// Connection 객체의 생성과 종료를 담당, commit/rollback
	public Member userAuthenticate(String userId, String password) {
		
		Connection conn = jdt.getConnection();
		
		try {
			// DataAccessObject에게 사용자의 아이디와 password로 데이터를 조회할 것을 요청
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
				//성공 시 작업
			}else{
				//실패 시 작업...
			};
			
			jdt.commit(conn);
		} catch(Exception e){
			jdt.rollback(conn);
			System.out.println("서비스의 catch 블록");
		}finally {
			jdt.close(conn);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}