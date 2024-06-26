package com.example.redis.config;

import com.example.redis.dto.StudentDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        //return new JedisConnectionFactory();
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(6379);
        return jedisConFactory;
    }

    public RedisTemplate<String, StudentDTO> redisTemplate() {
        RedisTemplate<String, StudentDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        //template.setKeySerializer(new StringRedisSerializer());
        //template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

}
