package lc.clz.logcenter.service;

import com.lc.clz.entities.log.Log;

import java.util.List;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/24 19:45
 */
public interface logService {

    public void saveLog(Log log);


    public List<Log> findLog(String module);

}
