package cn.fisher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author fisher
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * 序列化数据到String
     *
     * @param data 数据
     * @return 空的数据或者类型
     */
    public static String anyToString(Object data) {
        return toString(data).get();
    }

    /**
     * 序列化数据到String
     *
     * @param data 数据
     * @return 空的数据或者类型
     */
    public static Optional<String> toString(Object data) {
        if (data == null) {
            return Optional.empty();
        }

        try {
            return Optional.ofNullable(objectMapper.writeValueAsString(data));
        } catch (Exception e) {
            log.error("序列化数据错误，待序列化的数据:{},原因:{}", data, e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * 反序列化数据到实体
     *
     * @param json        待反序列化的字符串
     * @param targetClass 目标类型
     * @param <T>         类型
     * @return 空的数据或者类型
     */
    public static <T> T anyToData(String json, Class<T> targetClass) {
        return toData(json, targetClass).get();
    }

    /**
     * 反序列化数据到实体
     *
     * @param byteData    待反序列化的字节数据
     * @param targetClass 目标类型
     * @param <T>         类型
     * @return 空的数据或者类型
     */
    public static <T> T anyToData(byte[] byteData, Class<T> targetClass) {
        try {
            return objectMapper.readValue(byteData, targetClass);
        } catch (IOException e) {
            log.error("反序列化数据错误，反序列化的数据:{},目标类型:{},原因:{}",
                    new String(byteData), targetClass.getName(), e.getMessage()
            );
        }
        return null;
    }

    /**
     * 反序列化数据到实体
     *
     * @param data 待反序列化的字节数据
     * @param <T>  类型
     * @return 空的数据或者类型
     */
    public static <T> List<T> anyToData(String data) {
        if (StringUtils.isEmpty(data)) {
            return Collections.emptyList();
        }

        try {
            return objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("反序列化数据错误，反序列化的数据:{},原因:{}", data, e.getMessage());
        }
        return null;
    }

    /**
     * 反序列化数据到实体
     *
     * @param json        待反序列化的字符串
     * @param targetClass 目标类型
     * @param <T>         类型
     * @return 空的数据或者类型
     */
    public static <T> Optional<T> toData(String json, Class<T> targetClass) {
        if (StringUtils.isEmpty(json)) {
            return Optional.empty();
        }

        try {
            return Optional.ofNullable(objectMapper.readValue(json, targetClass));
        } catch (Exception e) {
            log.error("反序列化数据错误，反序列化的数据:{},目标类型:{},原因:{}", json, targetClass.getName(), e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * JsonNode
     *
     * @param json 数据
     * @return JsonNode
     */
    public static JsonNode toJsonNode(String json) {
        if (StringUtils.isEmpty(json)) {
            return NullNode.instance;
        }

        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("反序列化数据错误，反序列化的数据:{},原因:{}", json, e.getMessage());
            return NullNode.instance;
        }
    }

}
