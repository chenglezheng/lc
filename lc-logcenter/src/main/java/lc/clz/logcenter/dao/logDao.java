package lc.clz.logcenter.dao;

import com.lc.clz.entities.log.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shenjiang
 * @Description:
 * @Date: 2019/6/24 19:51
 */
@Repository
public interface logDao {

    public void saveLog(Log log);


    public List<Log> findLogs(String module);

}
