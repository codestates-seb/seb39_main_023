package com.team23.mainPr;

import com.team23.mainPr.Member.Controller.MemberController;
import com.team23.mainPr.Member.Dto.CreateMemberDto;
import com.team23.mainPr.Member.Entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest
class MainPrApplicationTests {
	@Autowired
	MemberController memberController;

	CreateMemberDto createMemberDto = new CreateMemberDto("ska40806","jangwonyong@@@");



	@Test
	void contextLoads() {
	}
	/*
	* refactor : 유스케이스 늘리기
	*
	* */
	@Test
	void validateCreateUserData(){
		boolean result = memberController.validation(createMemberDto);
		Assert.isTrue(result,"user ID, password check function test");
	}


	//ResponseEntity 내부에 제네릭으로 body가 선언되어 있다. 가져와서 내부 데이터를 검증할수 있다.
	@Test
	void createUserTest(){
		ResponseEntity result = memberController.createMember(createMemberDto);
		Member created = (Member)result.getBody();//명시적으로 캐스팅해주어야 한다.

		System.out.println(created.getLoginId());
		Assert.isTrue(created.getLoginId().equals("ska40806"),"user ID, password check function test");

	}
	@Test
	void getUserDataTest(){

	}
	@Test
	void delUserDataTest(){

	}

	@Test
	void autoCreateProfileTest(){

	}

	@Test
	void checkMemberRole(){

	}


}
