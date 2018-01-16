package com.sheefee.simple.sso.server.facade;

import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;

import com.sheefee.simple.sso.server.constant.AuthConst;
import com.sheefee.simple.sso.server.domain.User;
import com.sheefee.simple.sso.server.service.UserService;
import com.sheefee.simple.sso.server.service.UserServiceImpl;
import com.sheefee.simple.sso.server.storage.SessionStorage;
import com.sheefee.simple.sso.server.storage.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by daiwei on 2018/1/15.
 */
@Component
public class TokenUtil {

    public ResultData checkToken(HttpServletRequest request){
        UserService userService=new UserServiceImpl();
        String token = request.getParameter(AuthConst.TOKEN);
        if (StringUtils.isEmpty(token)){
            return ResultData.newResultData(ErrorCode.FAILOR,"token为空!");
        }

        User user = userService.findByToken(token);
        if (user!=null){
            return ResultData.newSuccessResultData();
        }
        return ResultData.newResultData(ErrorCode.FAILOR,"token认真失败");
    }
}
