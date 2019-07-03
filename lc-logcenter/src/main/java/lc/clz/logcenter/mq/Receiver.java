package lc.clz.logcenter.mq;

import com.lc.clz.entities.log.Log;
import lc.clz.logcenter.service.logService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/25 16:31
 */
@Component
@RabbitListener(queues = "logMessageSave")
public class Receiver {

    @Autowired
    private logService logService;

    @RabbitHandler
    public void send(Log log){
        logService.saveLog(log);
    }

}
