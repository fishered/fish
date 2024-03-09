package cn.fisher.common.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * hash类型的redis服务
 * @author fisher
 */
@Service
public class RedisHashService extends RedisService {

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key   redis key
     * @param field hash filed
     * @return 序列化后的value
     */
    public <T> T get(String key, String field) {
        return (T) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key redis key
     * @return 全部的value
     */
    public Map get(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key    redis key
     * @param fields 多个字段
     * @return 字段的值
     */
    public List get(String key, String... fields) {
        return redisTemplate.opsForHash().multiGet(key, Arrays.asList(fields));
    }

    /**
     * 放置字段到hash
     *
     * @param key     redis key
     * @param hashKey hash key
     * @param value   待放置的value
     */
    public void put(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 直接放置map到hash
     *
     * @param key  redis key
     * @param maps 待放置的value
     */
    public void put(String key, Map<String, Object> maps) {
        redisTemplate.opsForHash().putAll(key, maps);
    }

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key     redis key
     * @param hashKey hash key
     * @param value   不存在时放置的value
     * @return true 成功 false 失败
     */
    public Boolean putIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key    redis key
     * @param fields 待删除的字段
     * @return 成功删除的数量
     */
    public Long remove(String key, String... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key   redis key
     * @param field 指定的字段
     * @return true 存在 false 不存在
     */
    public boolean exists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key       redis key
     * @param field     指定的字段
     * @param increment 自增的数量
     * @return 自增后的数字
     */
    public Long incr(String key, String field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key   redis key
     * @param field 指定的字段
     * @param delta 自增的数量(浮点数)
     * @return 自增后的数字
     */
    public Double incr(String key, String field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key redis key
     * @return key 中的字段
     */
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取哈希表中字段的数量
     *
     * @param key redis key
     * @return 哈希表的大小
     */
    public Long size(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 获取哈希表中所有值
     *
     * @param key redis key
     * @return 哈希表中的value
     */
    public List<Object> values(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 迭代哈希表中的键值对
     *
     * @param key     redis key
     * @param options scan 配置
     * @return 游标
     */
    public Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options) {
        return redisTemplate.opsForHash().scan(key, options);
    }
}