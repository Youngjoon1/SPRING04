package com.care.root;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.root.member.controller.MemberController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:testMember.xml","file:src/main/webapp/WEB-INF/spring/root-Context.xml"})
public class TestMock {
	@Autowired MemberController mc;
	MockMvc mock;
	
	@Before
	public void setUp() {
		System.out.println("test실행 전 실행");
		mock = MockMvcBuilders.standaloneSetup(mc).build();
		// mock이 컨트롤러로 접근
	}
	
	//@Test
	public void testIndex() throws Exception {
		System.out.println("---test 실행");
		
		mock.perform(get("/list")).andDo(print()).andExpect(status().isOk()).andExpect(forwardedUrl("member/list"));
		//   정보를 요청              출력             성공 코드 200번과 같다면          list의 경로   
		// 통합 테스트
		//Assert.assertNull(null);
	}
	
	@Test
	@Transactional(transactionManager = "txMgr") // root-context bean호출
	public void testInsert() throws Exception {
		mock.perform( post("/insert").param("id","555").param("name","도도도")).andDo(print()).andExpect(status().is3xxRedirection());
		
	}
	
}
