package com.includesys.sm.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("test.TestController")
public class TestController {

	@RequestMapping(value="/test01.do")
	public ModelAndView call01(){
		System.out.println("[CALL Controller] test01");
		ModelAndView mv = new ModelAndView("test/test01");
		return mv;
	}
	
}
