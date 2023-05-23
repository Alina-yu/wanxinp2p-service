package cn.itcast.wanxinp2p.account.service.impl;


import cn.itcast.wanxinp2p.account.common.AccountErrorCode;
import cn.itcast.wanxinp2p.account.entity.Account;
import cn.itcast.wanxinp2p.account.mapper.AccountMapper;
import cn.itcast.wanxinp2p.account.service.AccountService;
import cn.itcast.wanxinp2p.account.service.SmsService;
import cn.itcast.wanxinp2p.api.account.model.AccountDTO;
import cn.itcast.wanxinp2p.api.account.model.AccountLoginDTO;
import cn.itcast.wanxinp2p.api.account.model.AccountRegisterDTO;
import cn.itcast.wanxinp2p.common.domain.BusinessException;
import cn.itcast.wanxinp2p.common.domain.RestResponse;
import cn.itcast.wanxinp2p.common.util.PasswordUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private SmsService smsService;


//    @Value("${sms.enable}")
//
//    private Boolean smsEnable;

    /**
     * 获取验证码
     * @param mobile
     * @return
     */
    @Override
    public RestResponse getSMSCode(String mobile) {
      return smsService.getSMSCode(mobile);
    }

    @Override
    public Integer checkMobile(String mobile, String key, String code) {
//        smsService.verifySmsCode(key,code);
//        QueryWrapper<Account> wrapper=new QueryWrapper<>();
//        //wrapper.eq("mobile",mobile);
//        wrapper.lambda().eq(Account::getMobile,mobile);
//        int count=count(wrapper);
        int count = 1;
        return count>0?1:0;
    }

    /**
     * 用户注册
     * @param accountRegisterDTO
     * @return
     */
    @Override
    @Hmily(confirmMethod = "confirmRegister",cancelMethod = "cancelRegister")
    public AccountDTO register(AccountRegisterDTO accountRegisterDTO) {
        Properties properties  = new Properties();
        Boolean smsEnable = Boolean.valueOf(properties.getProperty("smsEnable"));
        Account account=new Account();
        account.setUsername(accountRegisterDTO.getUsername());
        account.setMobile(accountRegisterDTO.getMobile());
        account.setPassword(PasswordUtil.generate(accountRegisterDTO.getPassword()));
        if(smsEnable){
            account.setPassword(PasswordUtil.generate(accountRegisterDTO.getMobile()));
        }
        account.setDomain("c");
        save(account);
        return convertAccountEntityToDTO(account);
    }
    public void confirmRegister(AccountRegisterDTO accountRegisterDTO){
        log.info("execute confirmRegister");
    }
    public void cancelRegister(AccountRegisterDTO accountRegisterDTO){
        log.info("execute cancelRegister");
        //删除账号
        remove(Wrappers.<Account>lambdaQuery().eq(Account::getUsername,accountRegisterDTO.getUsername()));
    }






    @Override
    public AccountDTO login(AccountLoginDTO accountLoginDTO) {
        //1.现根据用户名查询，再比对密码
        //c 端用户用户名是手机号
        //b 端用户用户名是账号
        Account account = null;
        if(accountLoginDTO.getDomain().equalsIgnoreCase("c")){
            account = getAccountByMobile(accountLoginDTO.getMobile());
        }
        else {
            account = getAccountByUsername(accountLoginDTO.getUsername());
        }
        if (account == null){
            throw new BusinessException(AccountErrorCode.E_130104);
        }

        //根据手机验证码进行登录
        Properties properties = new Properties();
        //String smsEnable = properties.getProperty("smsEnable");

        String smsEnable = String.valueOf(false);
        AccountDTO accountDTO = convertAccountEntityToDTO(account);
        if(smsEnable.equalsIgnoreCase("true")){
            return accountDTO;
        }
        String generate = PasswordUtil.generate(accountLoginDTO.getPassword());
        System.out.println(generate);


        //验证密码进行登录
        if(PasswordUtil.verify(accountLoginDTO.getPassword(),account.getPassword())){
            return accountDTO;
        }

         throw   new BusinessException(AccountErrorCode.E_130105);


    }

    /**
     * 根据手机号查询账号
     * @param mobile
     * @return
     */

    private Account getAccountByMobile(String mobile){

        return getOne(new QueryWrapper<Account>().lambda().eq(Account::getMobile,mobile));

    }


    /**
     * 根据用户名查询账号
     * @param username
     * @return
     */
    private Account getAccountByUsername(String username){
        return getOne(new QueryWrapper<Account>().lambda().eq(Account::getUsername,username));
    }



    /**
     * entity转为dto
        * @param entity
        * @return
     */
    private AccountDTO convertAccountEntityToDTO(Account entity) {
        if (entity == null) {
            return null;
        }
        AccountDTO dto = new AccountDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
