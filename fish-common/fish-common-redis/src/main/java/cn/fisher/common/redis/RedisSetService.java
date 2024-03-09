package cn.fisher.common.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * redis set 服务
 * @author fisher
 */
@Service
public class RedisSetService extends RedisService {

    /**
     * set添加元素
     *
     * @param key    redis Key
     * @param values 待添加的值
     * @return 数组长度
     */
    public Long add(String key, Object ...values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * set移除元素
     *
     * @param key    redis Key
     * @param values 待删除的元素
     * @return 删除后的数组长度
     */
    public Long remove(String key, Object ...values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 移除并返回集合的一个随机元素
     *
     * @param key redis Key
     * @return 弹出的元素
     */
    public <T> T pop(String key) {
        return (T) redisTemplate.opsForSet().pop(key);
    }

    /**
     * 将元素value从一个集合移到另一个集合
     *
     * @param key     redis Key
     * @param value   待转移的元素
     * @param destKey 目标key
     * @return true 成功 false 失败
     */
    public Boolean move(String key, Object value, String destKey) {
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 获取集合的大小
     *
     * @param key redis Key
     * @return 集合大小
     */
    public Long size(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 判断集合是否包含value
     *
     * @param key   redis Key
     * @param value 待检测的值
     * @return true 是 false 不是
     */
    public Boolean isMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 获取两个集合的交集
     *
     * @param key      redis Key
     * @param otherKey 另外一个集合的key
     * @return 交集
     */
    public Set intersect(String key, String otherKey) {
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的交集
     *
     * @param key       redis Key
     * @param otherKeys 多个集合的key
     * @return 交集
     */
    public Set intersect(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     *
     * @param key      redis Key
     * @param otherKey 集合的key
     * @param destKey  待存储的集合key
     * @return 数组的长度
     */
    public Long intersect(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的交集存储到destKey集合中
     *
     * @param key       redis Key
     * @param otherKeys 多个集合的key
     * @param destKey   待存储的集合key
     * @return 数组的长度
     */
    public Long intersect(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取两个集合的并集
     *
     * @param key       redis Key
     * @param otherKeys 集合的key
     * @return 并集
     */
    public Set<Object> union(String key, String otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 获取key集合与多个集合的并集
     *
     * @param key       redis Key
     * @param otherKeys 集合的key
     * @return 并集
     */
    public Set<Object> union(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的并集存储到destKey中
     *
     * @param key      redis Key
     * @param otherKey 集合的key
     * @param destKey  待存储的集合key
     * @return 数组的长度
     */
    public Long union(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的并集存储到destKey中
     *
     * @param key       redis Key
     * @param otherKeys 集合的key
     * @param destKey   待存储的集合key
     * @return 数组的长度
     */
    public Long union(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取两个集合的差集
     *
     * @param key      redis Key
     * @param otherKey 集合的key
     * @return 差集
     */
    public Set difference(String key, String otherKey) {
        return redisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的差集
     *
     * @param key       redis Key
     * @param otherKeys 多个集合的key
     * @return 差集
     */
    public Set<Object> sDifference(String key, Collection<String> otherKeys) {
        return redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的差集存储到destKey中
     *
     * @param key      redis Key
     * @param otherKey 集合的key
     * @param destKey  待存储的集合key
     * @return 数组的长度
     */
    public Long sDifference(String key, String otherKey, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的差集存储到destKey中
     *
     * @param key       redis Key
     * @param otherKeys 集合的key
     * @param destKey   待存储的集合key
     * @return 数组的长度
     */
    public Long sDifference(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取集合所有元素
     *
     * @param key redis Key
     * @return 集合中的元素
     */
    public Set members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取集合中的一个元素
     *
     * @param key redis Key
     * @return 集合中的元素
     */
    public <T> T randomMember(String key) {
        return (T) redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取集合中count个元素
     *
     * @param key   redis Key
     * @param count 待获取的元素数量
     * @return 获取到的列表
     */
    public List randomMembers(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 随机获取集合中count个元素并且去除重复的
     *
     * @param key   redis Key
     * @param count 待获取的元素数量
     * @return 获取到的列表
     */
    public Set distinctRandomMembers(String key, long count) {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * set 的scan
     *
     * @param key     redis ey
     * @param options 扫描配置
     * @return 游标
     */
    public Cursor<Object> scan(String key, ScanOptions options) {
        return redisTemplate.opsForSet().scan(key, options);
    }
}
