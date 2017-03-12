package org.dw.action.demo2;



import org.dw.action.constant;
import org.dw.action.util.SSOCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping(value = "demo2")
public class Demo2Action {
	@RequestMapping(value = "main.action")
	public String main(HttpServletRequest request){
		boolean ok = SSOCheck.checkcookie(request);
		if(ok){
			return "success2";
		}
		constant.GO_TO_URL = "/demo2/main.action";
		request.setAttribute("gotourl",constant.GO_TO_URL);
		return "login";
	}

}
