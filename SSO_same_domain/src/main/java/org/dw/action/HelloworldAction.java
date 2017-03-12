package org.dw.action;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HelloworldAction {

	@RequestMapping(value = "/test")
	public void test(){
		System.out.println("HelloworldAction");

	}
}
