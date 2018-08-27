package com.test.app.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.app.dto.Quote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RedisConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .build();
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Quote> quoteRedisTemplate() {
        final RedisTemplate<String, Quote> template = new RedisTemplate<String, Quote>();
        final Jackson2JsonRedisSerializer<Quote> serializer = new Jackson2JsonRedisSerializer<Quote>(Quote.class);
        serializer.setObjectMapper(objectMapper());

        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(serializer);

        return template;
    }

}
