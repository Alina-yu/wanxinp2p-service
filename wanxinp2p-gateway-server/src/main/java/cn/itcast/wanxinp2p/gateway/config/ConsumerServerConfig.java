//package cn.itcast.wanxinp2p.gateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
///**
// * @date 2023/5/14 22:31
// * @desciption:* 用户中心 资源服务器定义
// */
//@Configuration
//@EnableResourceServer
//public class ConsumerServerConfig extends ResourceServerConfigurerAdapter {
//    @Autowired
//    private TokenStore tokenStore;
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(tokenStore).resourceId(RESOURCE_ID) .stateless(true);
//        resources.authenticationEntryPoint(point).accessDeniedHandler(handler);
//    }
//    public void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/consumer/l/**")
//                .denyAll()
//                .antMatchers("/consumer/my/**")
//                .access("#oauth2.hasScope('read')" +
//                        "and #oauth2.clientHasRole('ROLE_CONSUMER')")
//                .antMatchers("/consumer/m/**")
//                .access("#oauth2.hasScope('read')" +
//                        "and #oauth2.clientHasRole('ROLE_ADMIN')")
//                .antMatchers("/consumer/**").permitAll(); }
//}
