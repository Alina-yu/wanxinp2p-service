package cn.itcast.wanxinp2p.consumer.config;

import cn.itcast.wanxinp2p.consumer.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @date 2023/5/16 23:40
 * @desciption: 添加前置控制器配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        //拦截通过网关后的所有请求
        interceptorRegistry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
    }
}
