package com.lc.clz.kafka.controller;

import com.lc.clz.entities.User;
import com.lc.clz.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/13 15:04
 */
@Controller
public class SendMessageControl {

    @Resource
    private KafkaTemplate kafkaTemplate;

    private String topic="oneMessage";

    @RequestMapping("/sendKafka")
    @ResponseBody
    public void sendKafka(User user){
        //测试
        user = new User();
        user.setUserAddress("南京");
        user.setUserAge((short)23);
        user.setUserEmail("123456789");
        user.setUserPwd("1111111111111111");
        kafkaTemplate.send(topic,SerializeUtil.serialize(user));
    }


}
