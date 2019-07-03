package lc.clz.logcenter.service.impl;

import com.lc.clz.entities.log.Log;
import lc.clz.logcenter.service.logService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/24 19:29
 */
@Service("logservice")
public class logServiceImpl implements logService {
    @Override
    public void saveLog(Log log) {

    }

    @Override
    public List<Log> findLog(String module) {
        return null;
    }
}
