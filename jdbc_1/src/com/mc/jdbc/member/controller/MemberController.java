package com.mc.jdbc.member.controller;


//MVC2 패턴
//Model View Controller로 소프트웨어아키텍처를 구성하는 소프트웨어아키텍처 패턴

//Controller
//Presentation Layer : Application에서 Client와 직접 상호작용하는 Layer
// 						핵심 로직인 Service Layer가 외부의 변화에 종속되지 않도록 분리하기 위해
//						Client와 직접 상호작용하는 Layer 분리 client 변경시 controller의 코드만 변경이 발생


// 1. 입력데이터를 어플리케이션 내에서 사용하기 적합한 형태로 파싱OO
// 2. 부적합한 요청에 대해 허카 불가를 처리하는 외벽 역할(validator, 권한관리)
// 3. Client에게 비즈니스로직의 결과물을 어떻게 보여줄 것인지 선택
// HTML로 보낼 것인지, JSON이나 XML 같은 특정 데이터형식으로 보낼 것인지 등등...

public class MemberController {
			
}
