package cn.fisher.common.web.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 返回值是long的时候转成string
 */
@Configuration
public class ReturnValueLongCustomizerConfigurer implements WebMvcConfigurer {

    /**
     * Extend or modify the list of converters after it has been, either
     * {@link #configureMessageConverters(List) configured} or initialized with
     * a default list.
     * <p>Note that the order of converter registration is important. Especially
     * in cases where clients accept {@link MediaType#ALL}
     * the converters configured earlier will be preferred.
     *
     * @param converters the list of configured converters to be extended
     * @since 4.1.3
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter){
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                        = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();

                SimpleModule module = new SimpleModule();
                module.addSerializer(Long.class, ToStringSerializer.instance);
                module.addSerializer(Long.TYPE, ToStringSerializer.instance);
                objectMapper.registerModule(module);
            }
        }
    }

}
