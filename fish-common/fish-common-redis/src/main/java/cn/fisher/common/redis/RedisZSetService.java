package cn.fisher.common.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * redis zset 服务
 * @author fisher
 */
@Service
public class RedisZSetService extends RedisService {

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key   redis Key
     * @param value 值
     * @param score 分数
     * @return true 成功 false 失败
     */
    public Boolean add(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }


    /**
     * 删除指定的元素
     *
     * @param key    redis Key
     * @param values 元素的值
     * @return 数组长度
     */
    public Long remove(String key, Collection values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key   redis Key
     * @param value 值
     * @param delta 分数
     * @return true 成功 false 失败
     */
    public Double incrementScore(String key, Object value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     *
     * @param key   redis Key
     * @param value 值
     * @return 0表示第一位
     */
    public Long rank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 返回元素在集合的排名,按元素的score值由大到小排列
     *
     * @param key   redis Key
     * @param value 值
     * @return 倒序的值
     */
    public Long reverseRank(String key, Object value) {
        return redisTemplate.opsForZSet().reverseRank(key, value);
    }

    /**
     * 获取集合的元素, 从小到大排序
     *
     * @param key   redis Key
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return 元素列表
     */
    public Set range(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取集合元素, 并且把score值也获取
     *
     * @param key   redis Key
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return 元素列表
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 根据Score值查询集合元素
     *
     * @param key redis Key
     * @param min 最小值
     * @param max 最大值
     * @return 元素列表
     */
    public Set rangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从小到大排序
     *
     * @param key redis Key
     * @param min 最小值
     * @param max 最大值
     * @return 元素列表
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    /**
     * 根据Score值和指定的开始和结束区间查询集合元素, 从小到大排序
     *
     * @param key   redis Key
     * @param min   分数最小值
     * @param max   分数最大值
     * @param start 索引开始位置
     * @param end   索引结束位置
     * @return 元素列表
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序
     *
     * @param key   redis Key
     * @param start 索引开始位置
     * @param end   索引结束位置
     * @return 元素列表
     */
    public Set reverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序, 并返回score值
     *
     * @param key   redis Key
     * @param start 索引开始位置
     * @param end   索引结束位置
     * @return 元素列表
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key redis Key
     * @param min 最低分
     * @param max 最高分
     * @return 元素列表
     */
    public Set reverseRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key redis Key
     * @param min 最低分
     * @param max 最高分
     * @return 元素列表带分数
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeByScoreWithScores(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * 根据Score值和指定的开始和结束区间查询集合元素, 从小到大排序
     *
     * @param key   redis Key
     * @param min   分数最小值
     * @param max   分数最大值
     * @param start 索引开始位置
     * @param end   索引结束位置
     * @return 元素列表
     */
    public Set reverseRangeByScore(String key, double min, double max, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end);
    }

    /**
     * 根据score值获取集合元素数量
     *
     * @param key redis Key
     * @param min 最低分
     * @param max 最高分
     * @return 元素数量
     */
    public Long count(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 获取集合大小
     *
     * @param key redis Key
     * @return 元素数量
     */
    public Long size(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获取集合大小
     *
     * @param key redis Key
     * @return 元素数量
     */
    public Long card(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 获取集合中value元素的score值
     *
     * @param key   redis Key
     * @param value 值
     * @return 值的分数
     */
    public Double score(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 移除指定索引位置的成员
     *
     * @param key   redis Key
     * @param start 开始位置
     * @param end   结束位置
     * @return 移除元素的数量
     */
    public Long remove(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 根据指定的score值的范围来移除成员
     *
     * @param key redis Key
     * @param min 最低分
     * @param max 最高分
     * @return 元素数量
     */
    public Long union(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 获取key和otherKey的并集并存储在destKey中
     *
     * @param key      redis Key
     * @param otherKey 待比较的key
     * @param destKey  存储的key
     * @return 并集的长度
     */
    public Long union(String key, String otherKey, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * 获取key和多个otherKey的并集并存储在destKey中
     *
     * @param key       redis Key
     * @param otherKeys 待比较的key
     * @param destKey   存储的key
     * @return 并集的长度
     */
    public Long union(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 交集
     *
     * @param key      redis Key
     * @param otherKey 待比较的key
     * @param destKey  存储的key
     * @return 交集的长度
     */
    public Long intersect(String key, String otherKey, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
    }

    /**
     * 交集
     *
     * @param key       redis Key
     * @param otherKeys 待比较的key
     * @param destKey   存储的key
     * @return 交集的长度
     */
    public Long intersect(String key, Collection<String> otherKeys, String destKey) {
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * 集合扫描
     *
     * @param key     redis Key
     * @param options 扫描配置
     * @return 游标
     */
    public Cursor<ZSetOperations.TypedTuple<Object>> scan(String key, ScanOptions options) {
        return redisTemplate.opsForZSet().scan(key, options);
    }
}
