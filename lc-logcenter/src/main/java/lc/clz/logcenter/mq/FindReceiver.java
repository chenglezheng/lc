package lc.clz.logcenter.mq;

import com.lc.clz.entities.log.Log;
import lc.clz.logcenter.service.logService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/25 16:39
 */
@Component
@RabbitListener(queues="findLog")
public class FindReceiver {

    @Autowired
    private logService logService;

    public List<Log> findLogs(String module){
        return logService.findLog(module);
    }

}
