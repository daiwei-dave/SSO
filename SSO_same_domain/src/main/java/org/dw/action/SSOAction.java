package org.dw.action;

import org.dw.action.util.SSOCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;



/**
 * 登录校验 
 * 1、校验用户名密码
 * 2、校验cookie
 * @author xuzj
 *
 */
@Controller
public class SSOAction {
	@RequestMapping(value = "/dologin")
	public String dologin(String username,String password,String gotourl,HttpServletResponse response) {
		//同域下的sso
		boolean ok = SSOCheck.checklogin(username, password);
		if (ok) {
			Cookie cookie = new Cookie("ssocookie", "sso");
			cookie.setPath("/");//设置到顶层
			response.addCookie(cookie);
			constant.GO_TO_URL=gotourl;
			return "redirect:"+constant.GO_TO_URL+"";
		}
		return "login";
	}
}

	
	
	
