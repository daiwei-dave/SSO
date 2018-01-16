package com.sheefee.simple.sso.client.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.gitee.common.entity.ResultData;
import com.sheefee.simple.sso.client.constant.AuthConst;
import com.sheefee.simple.sso.server.facade.TokenUtil;
import com.sheefee.simple.sso.server.storage.SessionStorage;


/**
 * 客户端登录filter
 * 
 * @author daiwei
 * @date 2017年9月11日 下午4:08:25
 *
 */
public class LoginFilter implements Filter {
	private FilterConfig config;



	public void destroy() {}


	/**
	 * 拦截子系统未登录用户请求，跳转至sso认证中心
	 * @param req
	 * @param res
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();

//		//访问首页，放行
//		if ("/".equals(uri)){
//			chain.doFilter(req, res);
//			return;
//		}
		
		// 已经登录，放行
		if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
			chain.doFilter(req, res);
			return;
		}
		// 从认证中心回跳的带有token的请求，有效则放行
		String token = request.getParameter(AuthConst.TOKEN);
		if (token != null) {


			//todo 存储，用于注销



//			HttpSession httpSession = SessionStorage.INSTANCE.get(token);
//			if (httpSession==null){
//				return;
//			}
//			SessionStorage.INSTANCE.set(token, session);
			ResultData resultData = new TokenUtil().checkToken(request);
			//todo 认证失败返回给客户端
			if (!ResultData.isSuccess(resultData)){
				return ;
			}
			session.setAttribute(AuthConst.IS_LOGIN, true);
			session.setAttribute(AuthConst.TOKEN, token);
			chain.doFilter(req, res);
			return;
		}

		// 重定向至登录页面，并附带当前请求地址
		response.sendRedirect(config.getInitParameter(AuthConst.LOGIN_URL));
	}


	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}
}