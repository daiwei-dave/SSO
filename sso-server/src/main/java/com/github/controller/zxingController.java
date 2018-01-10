package com.github.controller;

import com.github.util.QrCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;



/**
 * @Description pdf导出测试
 * @Author daiwei
 * @Date 2017/11/14
 */
@Controller
@RequestMapping("/zxing")
public class zxingController {

    /**
     * 生成二维码
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/createQRCode")
    @ResponseBody
    public void createQRCode(HttpServletResponse response) throws Exception {
        QrCodeUtils.writeToStream("daiwei", 300, 300, "png", response.getOutputStream());
    }



}
