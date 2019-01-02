package com.lc.clz.redis;

import com.lc.clz.util.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

@Component
public class RedisCache<K,V> implements Cache<K,V> {

    private final String CACHE_PREFIX="lc-clz-cache";

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 获取redis中的key
     * @param k
     * @return
     */
    private byte[] getKey(K k){
        if(k instanceof  String){
            return (CACHE_PREFIX+k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }



    public V get(K k) throws CacheException {
        byte[] value=jedisUtil.get(getKey(k));
        if(value!=null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    public V put(K k, V v) throws CacheException {
        byte[] key=getKey(k);
        byte[] value= SerializationUtils.serialize(v);
        jedisUtil.set(key,value);
        jedisUtil.expire(key,600);
        return v;
    }

    public V remove(K k) throws CacheException {
        byte[] key=getKey(k);
        byte[] value=jedisUtil.get(key);
        jedisUtil.del(key);
        if(value!=null){
            SerializationUtils.deserialize(value);
        }
        return null;
    }

    public void clear() throws CacheException {

    }

    public int size() {
        return 0;
    }

    public Set<K> keys() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }
}
