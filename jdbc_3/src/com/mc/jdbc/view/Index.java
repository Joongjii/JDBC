package com.mc.jdbc.view;

import java.util.Map;
import java.util.Scanner;

import com.mc.jdbc.member.controller.MemberController;
import com.mc.jdbc.member.dto.Member;

public class Index {
   
   private Scanner sc = new Scanner(System.in);
   private MemberController memberController = new MemberController();
   
   private MemberMenu memberMenu = new MemberMenu();
   
   public void startMenu() {
	   
      System.out.println("�α��� �ϼ���.");
      
      System.out.print("���̵� : ");
      String userId = sc.next();
      
      System.out.print("��ȣ : ");
      String password = sc.next();

      Map<String, Object> auth = memberController.login(userId, password);

		Boolean isSuccess = (Boolean) auth.get("isSuccess");
      
      
      if(isSuccess) {
         while(true) {
            System.out.println("������ �޴��� �����ϼ���.");
            System.out.println("1. ȸ������");
            System.out.println("2. ��������");
            System.out.println("3. �������");
            System.out.println("4. ����");
            System.out.print("�Է� : ");
            
            switch(sc.nextInt()) {
            case 1 : memberMenu.memberMenu(); break;
            //case 2 : bookMenu.bookMenu(); break;
            //case 3 : rentMenu.rentMenu(); break;
            case 4 : return;
            default :  System.out.println("�߸��� ���ڸ� �Է��ϼ̽��ϴ�.");
            
            }
         }
      }else {
         System.out.println("���̵� ��ȣ�� Ȯ���ϼ���.");
      }
   }

}