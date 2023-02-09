package com.mc.jdbc.view;

import java.util.Scanner;

import com.mc.jdbc.member.controller.MemberController;
import com.mc.jdbc.member.dto.Member;

// MVC2
// View
// UI



public class MemberMenu {
   
   private Scanner sc = new Scanner(System.in);
   private MemberController memberController = new MemberController();
   
   public void memberMenu() {
      
      do {
         System.out.println("\n***  ȸ�� ���� ���α׷� ***");
         System.out.println("1. ȸ�� ��ü ��ȸ");
         System.out.println("2. �� ȸ�� ���");
         System.out.println("3. ȸ�� ��ȣ ����");
         System.out.println("4. ȸ�� Ż��");
         System.out.println("5. ����");
         System.out.print("��ȣ �Է� : ");
         
         switch(sc.nextInt()) {
            case 1: 
            
               break;
            case 2: 
            
               break;
            case 3: 
               sc.nextLine();
               Member member = new Member();
               System.out.print("��й�ȣ�� ������ ���̵� : ");
               member.setUserId(sc.nextLine());
               
               System.out.print("������ ��й�ȣ : ");
               member.setPassword(sc.next());
               
               break;
               
            case 4: 
               System.out.print("Ż�� ��ų ȸ���� ���̵� �Է� : ");
               String userId = sc.next();
               
               
               break;
                           
            case 5: return;
            default : System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��ϼ���.");
         }
      }while(true);
   }
   
      
   //����ڷκ��� ȸ������ ������ �޾Ƽ� member��ü�� ��ȯ
   public Member join() {
      
      Member member = new Member();
      
      System.out.println("ȸ�� ������ �Է��ϼ���.-------------");
      
      System.out.print("���̵� : ");
      member.setUserId(sc.next());
      
      System.out.print("��ȣ : ");
      member.setPassword(sc.next());
      
      System.out.print("�̸��� : ");
      member.setEmail(sc.next());
      
      System.out.print("��ȭ ��ȣ : ");
      member.setTell(sc.next());
      return member;
   }
   

}