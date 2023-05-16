package cn.itcast.wanxinp2p.consumer.interceptor;


import cn.itcast.wanxinp2p.api.account.model.LoginUser;
import cn.itcast.wanxinp2p.common.util.EncryptUtil;
import cn.itcast.wanxinp2p.common.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @date 2023/5/16 23:21
 * @desciption: 前置拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,Object o){
        String jsonToken = httpServletRequest.getParameter("json");
        if (StringUtil.isNotBlank(jsonToken)){

            LoginUser loginUser =
            JSON.parseObject(EncryptUtil.decodeUTF8StringBase64(jsonToken),new TypeReference<LoginUser>(){});
            httpServletRequest.setAttribute("jsonToken",loginUser);
        }
        return true;

    }
}
