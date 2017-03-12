package org.dw.action.demo1;
import org.dw.action.constant;
import org.dw.action.util.SSOCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "demo1")
public class Demo1Action {

	/**
	 * 先校验cookie的有效性  在校验登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "main.action")
	public String main(HttpServletRequest request){
		boolean ok = SSOCheck.checkcookie(request);
		if(ok){
			return "success1";
		}
		constant.GO_TO_URL = "/demo1/main.action";
		request.setAttribute("gotourl",constant.GO_TO_URL);
		return "login";
	}

}
