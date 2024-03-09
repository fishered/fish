package cn.fisher.common.redis;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis list服务
 * @author fisher
 */
@Service
public class RedisListService extends RedisService {

    /**
     * 通过索引获取列表中的元素
     *
     * @param key   redis key
     * @param index 指定的索引
     * @return 序列化之后的值
     */
    public <T> T get(String key, long index) {
        return (T) redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param key   redis key
     * @param start 开始位置, 0是开始位置
     * @param end   结束位置, -1返回所有
     * @return value 列表
     */
    public List get(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 存储在list头部
     *
     * @param key   redis key
     * @param value 待存储的值
     * @return 返回存储的值长度
     */
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 存储在list头部(多个)
     *
     * @param key    redis key
     * @param values 待存储的value
     * @return 返回存储的值长度
     */
    public Long lPush(String key, Collection values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 当list存在的时候才加入
     *
     * @param key   redis key
     * @param value 待存储的value
     * @return 返回存储的值长度
     */
    public Long lPushIfPresent(String key, Object value) {
        return redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 如果pivot存在,再pivot前面添加
     *
     * @param key   redis key
     * @param pivot 指定的 pivot
     * @param value 待存储的值
     * @return 返回存储的值长度
     */
    public Long lLeftPush(String key, Object pivot, Object value) {
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 存储在list尾部
     *
     * @param key   redis key
     * @param value 值
     * @return 返回存储的值长度
     */
    public Long rPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 存储在list尾部
     *
     * @param key    redis key
     * @param values 值
     * @return 返回存储的值长度
     */
    public Long rPushAll(String key, Collection values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 为已存在的列表添加值
     *
     * @param key   redis key
     * @param value 值
     * @return 返回存储的值长度
     */
    public Long rPushIfPresent(String key, Object value) {
        return redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 如果pivot存在,再pivot前面添加
     *
     * @param key   redis key
     * @param pivot 指定的 pivot
     * @param value 待存储的值
     * @return 返回存储的值长度
     */
    public Long rPush(String key, Object pivot, Object value) {
        return redisTemplate.opsForList().rightPush(key, pivot, value);
    }

    /**
     * 通过索引设置列表元素的值
     *
     * @param key   redis key
     * @param index 位置
     * @param value 带存储的value
     */
    public void set(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key redis key
     * @return 删除的元素
     */
    public <T> T lPop(String key) {
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key     redis key
     * @param timeout 等待时间
     * @param unit    时间单位
     * @return 删除的元素
     */
    public <T> T lbPop(String key, long timeout, TimeUnit unit) {
        return (T) redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key redis key
     * @return 删除的元素
     */
    public <T> T rPop(String key) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key     redis key
     * @param timeout 等待时间
     * @param unit    时间单位
     * @return 删除的元素
     */
    public <T> T rbPop(String key, long timeout, TimeUnit unit) {
        return (T) redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param sourceKey      原始的key
     * @param destinationKey 原始的key
     * @return 转移的元素
     */
    public <T> T rPopAndLPush(String sourceKey, String destinationKey) {
        return (T) redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey);
    }

    /**
     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param sourceKey      原始的key
     * @param destinationKey 原始的key
     * @param timeout        阻塞时间
     * @param unit           时间单位
     * @return 转移的元素
     */
    public <T> T rbPopAndLPush(String sourceKey, String destinationKey,
                               long timeout, TimeUnit unit) {
        return (T) redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
                destinationKey, timeout, unit);
    }

    /**
     * 删除集合中值等于value得元素
     *
     * @param key   redis key
     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
     *              index<0, 从尾部开始删除第一个值等于value的元素;
     * @param value 待删除的元素
     * @return 删除后的数组长度
     */
    public Long remove(String key, long index, Object value) {
        return redisTemplate.opsForList().remove(key, index, value);
    }

    /**
     * 裁剪list
     *
     * @param key   redis key
     * @param start 开始索引
     * @param end   结束索引
     */
    public void trim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 获取列表长度
     *
     * @param key redis key
     * @return 元素的长度
     */
    public Long len(String key) {
        return redisTemplate.opsForList().size(key);
    }
}
