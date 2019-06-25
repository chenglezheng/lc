package lc.clz.logcenter.control;

import com.lc.clz.entities.Log;
import lc.clz.logcenter.service.logService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/24 19:28
 */
@Controller
@RequestMapping("/logController")
public class logController {

    @Autowired
    private logService logService;

    @RequestMapping(value = "/saveLog",consumes = "application/json")
    public void saveLog(@RequestBody Log log){
        logService.saveLog(log);
    }

    @RequestMapping(value = "/findLog",consumes = "application/json")
    public List<Log> getLogMessage(String module){
        return logService.findLog(module);
    }

}
