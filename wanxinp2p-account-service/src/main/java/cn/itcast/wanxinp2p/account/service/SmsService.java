package cn.itcast.wanxinp2p.account.service;


import cn.itcast.wanxinp2p.common.domain.RestResponse;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SmsService {

//    @Value("${sms.url}")
//    private String smsURL;

//    @Value("${sms.enable}")
//    private Boolean smsEnable;


    public RestResponse getSMSCode(String mobile) {
        Properties properties  = new Properties();
        Boolean smsEnable = Boolean.valueOf(properties.getProperty("smsEnable"));
//        if (smsEnable) {
//            return OkHttpUtil.post(smsURL + "/generate?effectiveTime=300&name=sms", "{\"mobile\":" + mobile + "}");
//
//        }
        return RestResponse.success();
    }

    public void verifySmsCode(String key,String code){

//        if (smsEnable) {
//            StringBuilder params = new StringBuilder("/verify?name=sms");
//            params.append("&verificationKey=").append(key).append("&verificationCode=").append(code);
//            RestResponse smsResponse = OkHttpUtil.post(smsURL + params, "");
//            if (smsResponse.getCode() != CommonErrorCode.SUCCESS.getCode() || smsResponse.getResult().toString().equalsIgnoreCase("false")) {
//                throw new BusinessException(AccountErrorCode.E_140152);
//            }
//        }

    }
}
