package com.sheefee.simple.sso.server.facade;

import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;

import com.sheefee.simple.sso.server.constant.AuthConst;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by daiwei on 2018/1/15.
 */
public class TokenUtil {
    public static ResultData checkToken(HttpServletRequest request){
        String token = request.getParameter(AuthConst.TOKEN);
        if (StringUtils.isEmpty(token)){
            return ResultData.newResultData(ErrorCode.FAILOR,"token为空!");
        }
        if (request.getSession().getAttribute("token").equals(token)){
            return ResultData.newSuccessResultData();
        }
        return ResultData.newResultData(ErrorCode.FAILOR,"token认真失败");
    }
}
