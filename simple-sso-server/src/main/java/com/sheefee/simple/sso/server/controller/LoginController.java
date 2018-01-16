package com.sheefee.simple.sso.server.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;

import com.sheefee.simple.sso.server.constant.AuthConst;
import com.sheefee.simple.sso.server.domain.User;
import com.sheefee.simple.sso.server.storage.SessionStorage;

import com.sheefee.simple.sso.server.storage.TokenStorage;
import com.sheefee.simple.sso.server.util.HTTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




import com.sheefee.simple.sso.server.service.UserService;
import com.sheefee.simple.sso.server.storage.ClientStorage;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录和注销控制器
 * 
 * @author sheefee
 * @date 2017年9月12日 下午2:09:47
 *
 */
@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	/**
	 * 登录
	 * 
	 * @author sheefee
	 * @date 2017年9月12日 下午2:09:24
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResultData login(HttpServletRequest request, User input, HttpServletResponse response) {
		// 验证用户
		User user = userService.find(input);
		if (user == null) {
			return ResultData.newResultData(ErrorCode.FAILOR,"用户登录失败");
		}

		// 授权
		String token = UUID.randomUUID().toString();
		user.setToken(token);
		request.getSession().setAttribute(AuthConst.IS_LOGIN, true);
		request.getSession().setAttribute(AuthConst.TOKEN, token);
		
		// 存储，用于注销
		SessionStorage.INSTANCE.set(token, request.getSession());
		TokenStorage.getInstance().set(token, request.getSession());

		// 子系统跳转过来的登录请求，授权、存储后，跳转回去
//		String clientUrl = request.getParameter(AuthConst.CLIENT_URL);
		//todo clientUrl从客户端获取


		String clientUrl = "http://localhost:8084/index.jsp";
		Map<String, String> params = new HashMap<String, String>();
		params.put("token",token);
		final boolean post = HTTPUtil.post(clientUrl, params);
		if (!post){
			return ResultData.newResultData(ErrorCode.FAILOR,"向客户端发送token失败！");
		}
		if (clientUrl != null && !"".equals(clientUrl)) {
			// 存储，用于注销
			ClientStorage.INSTANCE.set(token, clientUrl);
			return ResultData.newResultData(ErrorCode.SUCCESS,ErrorCode.SUCCESS_MSG,user);
		}
		return ResultData.newResultData(ErrorCode.SUCCESS,ErrorCode.SUCCESS_MSG,user);
	}

	/**
	 * 注销
	 * 
	 * @author sheefee
	 * @date 2017年9月12日 下午2:08:51
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String token = request.getParameter(AuthConst.LOGOUT_REQUEST);
		
		// token存在于请求中，表示从客户端发起的注销；token存在于会话中，表示从认证中心发起的注销
		if (token != null && !"".equals(token)) {
			session = SessionStorage.INSTANCE.get(token);
		} else {
			token = (String) session.getAttribute(AuthConst.TOKEN);
		}
		
		if (session != null) {
			session.invalidate();
		}
		
		// 注销子系统
		List<String> list = ClientStorage.INSTANCE.get(token);
		if (list != null && list.size() > 0) {
			Map<String, String> params = new HashMap<String, String>();
			params.put(AuthConst.LOGOUT_REQUEST, token);
			for (String url : list) {
//				HTTPUtil.post(url, params);
			}
		}
		return "redirect:/";
	}
}