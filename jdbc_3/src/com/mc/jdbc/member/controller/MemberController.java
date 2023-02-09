package com.mc.jdbc.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mc.jdbc.member.dto.Member;
import com.mc.jdbc.member.sevice.MemberService;

// MVC2 ����
// Model, View, Controller �� ����Ʈ���� ������ �����ϴ� ����Ʈ�����Ű��ó ����

// Controller

// Presentation Layer : Application���� Client�� ���� ��ȣ�ۿ��ϴ� Layer
//						�ٽ� ������ Service Layer�� �ܺ��� ��ȭ�� ���ӵ��� �ʵ��� �и��ϱ� ����
//					    Client�� ���� ��ȣ�ۿ��ϴ� Presentation Layer�� �и�.
//						Client ����� Controller�� �ڵ常 ������ �߻�

// 1. �Էµ����͸� ���ø����̼� ������ ����ϱ� ������ ���·� �Ľ�
// 2. �������� ��û�� ���� �㰡/�Ұ��� ó���ϴ� �ܺ� ����(validator, ���Ѱ���)
// 3. Client���� ����Ͻ������� ������� ��� ������ �� ���� ����
//	  HTMl�� ���� ������, JSON, XML ���� Ư�� �������������� ���� ������ ���...

public class MemberController {
	
	private MemberService memberService = new MemberService();

	public Map<String, Object> login(String userId, String password) {
		
		Member member = memberService.userAuthenticate(userId, password);
		
		Map<String, Object> res = new HashMap<>();
		
		res.put("isSuccess", true);
		res.put("userInfo", member);
		
		if(member == null) res.put("isSuccess", false);
		return res;
	}

	public List<Member> searchAllMember() {
		
		List<Member> members = memberService.selectAllMember();
		return members;
	}

	public void join(Member member) {
		
		memberService.insertMember(member);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}