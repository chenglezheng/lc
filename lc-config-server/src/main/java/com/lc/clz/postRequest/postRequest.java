package com.lc.clz.postRequest;


import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/5/10 12:41
 */
@RestController
@RefreshScope
public class postRequest {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/sentPost")
    public String Post(){
        String sql = "http://localhost:8888/bus/refresh";
        sendPost(null,sql);
        return "哈哈";
    }

    public void sendPost(String param,String requestUrl){//发post请求

        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        restTemplate.postForObject(requestUrl,requestEntity,String.class);
    }

}
