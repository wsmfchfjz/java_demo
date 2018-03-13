package anddd7.springboot.utils;

import anddd7.springboot.dao.IdSequenceMapper;
import anddd7.springboot.domain.IdSequence;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * Created by edliao on 2017/5/5.
 */
@Service
@CacheConfig(cacheNames = "idCache")
public class IDGenerator {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IdSequenceMapper mapper;
    @Autowired
    CacheManager cacheManager;

    /**
     * 计算缓存的当前ID
     *
     * @param tableName
     * @return
     */
    @CachePut
    public ID calId(String tableName) {
        log.debug("计算当前ID {} - Id", tableName);
        //获取当前缓存
        Cache idCache = this.cacheManager.getCache("idCache");
        Cache.ValueWrapper cacheValue = idCache.get(tableName);
        //第一次 加载缓存
        if (cacheValue == null) {
            return getId(tableName);
        }

        ID current = (ID) cacheValue.get();

        //更新下标
        current.current++;

        //查看是否超界
        if (current.current.compareTo(current.end) == 0) {
            //重新加载
            return getId(tableName);
        }

        //返回并重新缓存
        return current;
    }


    private ID getId(String tableName) {
        log.debug("{} - ID 缓存中不存在 ,从数据库加载", tableName);
        IdSequence sequence = mapper.selectByPrimaryKey(tableName);

        ID current = new ID(sequence.getCurrent(), sequence.getCurrent() + 1, sequence.getCurrent() + sequence.getStep());

        //更新ID范围
        sequence.setCurrent(sequence.getCurrent() + sequence.getStep());

        mapper.updateByPrimaryKey(sequence);

        log.debug("获取到{} - ID数据:{}\t更新ID段到{}",
                tableName,
                JSON.toJSONString(current),
                sequence.getCurrent());

        return current;
    }


}
