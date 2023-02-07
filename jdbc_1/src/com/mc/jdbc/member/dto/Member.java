package com.mc.jdbc.member.dto;

import java.time.LocalDateTime;

public class Member {
	// DTO(Data Transfer Object)
	// ������ ������ ����ϴ� ��ü
	// ���̺� �����ϴ� �÷��� �Ӽ����� ����, getter/ setter ����
	
	
	
	// DTO : �ڹٺ� �Ծ�
	// 1. ��� �ʵ庯���� private
	// 2. �ݵ�� �⺻ �����ڰ� �����Ѵ�
	// 3. ��� �ʵ庯���� getter/setter�� ������.
	
	// `USER_ID`, `PASSWORD`, `EMAIL`, `GRADE`, `IS_LEAVE`, `TELL`, `RENTABLE_DATE`, `REG_DATE`
	
	// ���ڿ� Ÿ�� : String
	// timestamp 
	// ���� int double
	// boolean
	
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	
	private boolean isLeave;
	private LocalDateTime regDate;
	private LocalDateTime rentableDate;
	
	
	
	

	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public boolean isLeave() {
		return isLeave;
	}
	public void setLeave(boolean isLeave) {
		this.isLeave = isLeave;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public LocalDateTime getRentableDate() {
		return rentableDate;
	}
	public void setRentableDate(LocalDateTime rentableDate) {
		this.rentableDate = rentableDate;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", email=" + email + ", grade=" + grade
				+ ", isLeave=" + isLeave + "]";
	}
	
	
	
	
	
	
	
	
}
