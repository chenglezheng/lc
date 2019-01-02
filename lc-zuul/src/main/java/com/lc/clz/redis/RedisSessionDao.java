package com.lc.clz.redis;

import com.lc.clz.util.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisSessionDao extends AbstractSessionDAO {

    @Autowired
    private JedisUtil jedisUtil;

    private final String SHIRO_SESSIO_PREFIX="lc-clz-session";

    /**
     * 获取redis中的sessionId
     * @param key
     * @return
     */
    private byte[] getKey(String key){
        return (SHIRO_SESSIO_PREFIX+key).getBytes();
    }

    /**
     * 将session保存到redis中
     * @param session
     */
    private void saveSession(Session session){
        if(session!=null && session.getId()!=null){
            byte[] key=getKey(session.getId().toString());
            byte[] value= SerializationUtils.serialize(session);
            jedisUtil.set(key,value);
            jedisUtil.expire(key,600);
        }
    }

    protected Serializable doCreate(Session session) {
        Serializable sessionId=generateSessionId(session);
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    protected Session doReadSession(Serializable sessionId) {
        if (sessionId==null){
            return null;
        }
        byte[] key=getKey(sessionId.toString());
        byte[] value= jedisUtil.get(key);
        if(value!=null){
            return (Session) SerializationUtils.deserialize(value);
        }
        return null;
    }

    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    public void delete(Session session) {
        if (session ==null && session.getId()==null){
            return;
        }
        byte[] key=getKey(session.toString());
        if(key!=null){
            jedisUtil.del(key);
        }
    }

    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys=jedisUtil.keys(SHIRO_SESSIO_PREFIX);
        Set<Session> sessions=new HashSet<Session>();
        if(CollectionUtils.isEmpty(keys)){
            return sessions;
        }
        for (byte[] key:keys) {
            Session session=(Session) SerializationUtils.deserialize(jedisUtil.get(key));
            sessions.add(session);
        }
        return sessions;
    }
}
