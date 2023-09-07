package com.care.root;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.care.root.member.controller.MemberController;
import com.care.root.member.dao.MemberDAO;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberServiceImpl;

/*
 * junit : test진행하기 위한 프레임 워크(기능들의 집합)
 * test 주도 개발
 * 단위 테스트, 통합 테스트
 */
// @RunWith 테스트 환경으로 구동하겠다
// @ContextConfiguration 특정파일을 현재위치로 불러옴
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:testMember.xml","file:src/main/webapp/WEB-INF/spring/root-Context.xml"})
public class TestMember {
	@Autowired 
	MemberController mc;
	
	@Test
	public void testMc() {
		System.out.println(mc);
		
		
		//단위별 테스트
		//빈 등록의 유무
		//해당 빈이 null이 아니면 성공
		//만약 해당 빈이 null이면 실패
		assertNotNull(mc);
		
	}
	
	@Autowired
	MemberServiceImpl ms;
	
	@Test
	public void testMs() {
		assertNotNull(ms);
		
		MemberDTO dto = new MemberDTO();
		dto.setId(222);
		dto.setName("김개똥");
		int result = ms.insertMember(dto);
		System.out.println("result : " + result);
		assertEquals(result, 1);

	}
	
	
	@Autowired MemberDAO dao;
	
	@Test
	public void testDao() {
		assertNotNull(dao);
		MemberDTO dto = new MemberDTO();
		dto.setId(111);
		dto.setName("홍길동");
		int result = dao.insertMember(dto);
		System.out.println("result : " + result);
		assertEquals(result, 1);
		
	}
	
	
	
}
