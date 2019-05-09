package com.lc.clz.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableUtils {

    public  static  Map<String,Object> getTable(long total, List rows){
        Map<String,Object> map=new HashMap<>();
        map.put("count",total);
        map.put("rows",rows);
        return map;
    }

}
