package com.lc.clz.upload.control;

import com.lc.clz.entities.User;
import com.lc.clz.utils.SerializeUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Iterator;
import java.util.List;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/13 15:30
 */
@Controller
@Component
public class ReceiveControl{

    /*@Value("${kafka.topic.name}")*/
    private final String topic="oneMessage";

    @KafkaListener(topics = topic ,containerFactory = "listenerContainerFactory")
    public String getKafkaMessage(List<ConsumerRecord<String,byte[]>> consumerRecords, Acknowledgment acknowledgment){
        User user = null;
        Iterator<ConsumerRecord<String, byte[]>> consumerRecords1 = consumerRecords.iterator();
        while (consumerRecords1.hasNext()){
            ConsumerRecord<String, byte[]> consumerRecord = consumerRecords1.next();
            System.out.println("key:"+consumerRecord.key()+",offset:"+consumerRecord.offset()+",value:"+consumerRecord.offset());
            if(consumerRecord.value()!=null){
                user = (User) SerializeUtil.unserialize(consumerRecord.value());
            }
        }
        acknowledgment.acknowledge();
        return user.getUserEmail();

    }

}
