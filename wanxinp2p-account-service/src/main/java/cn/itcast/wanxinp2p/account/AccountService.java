package cn.itcast.wanxinp2p.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @date 2023/5/3 22:24
 * @desciption:
 */

@SpringBootApplication(scanBasePackages = {"org.dromara.hmily","cn.itcast.wanxinp2p.account"})
@EnableDiscoveryClient
@MapperScan("cn.itcast.wanxinp2p.account.mapper")
@EnableFeignClients(basePackages = {"cn.itcast.wanxinp2p.account.agent"})
public class AccountService {
    public static void main(String[] args) {
        SpringApplication.run(AccountService.class, args);
    }
}
