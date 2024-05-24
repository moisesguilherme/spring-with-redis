package com.example.redis.config;

import com.example.redis.dto.StudentDTO;
import com.example.redis.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;

@Configuration
@Slf4j
public class RedisConfig {


    @Value("${spring.redis.time-to-live}")
    private Long timeToLive;

    @Value("${spring.redis.cluster.nodes}")
    private String envRedisClusterNodes;

    @Value("${spring.redis.host}")
    private String envRedisHost;

    @Value("${spring.redis.timeout}")
    private Long timeout;

    @Autowired
    ClusterConfigurationProperties clusterProperties;

    public @Bean RedisConnectionFactory connectionFactory() {
        if (StringUtils.isNotEmpty(envRedisClusterNodes)) {
            final List<String> nodes = clusterProperties.getNodes();

            log.info("Aplicando Redis CLUSTER configuration {}", nodes);

            final LettuceClientConfiguration lettuceConfig = LettuceClientConfiguration.builder()
                    .commandTimeout(Duration.ofMillis(timeout))
                    .shutdownTimeout(Duration.ZERO)
                    .build();

            final RedisClusterConfiguration redisConfig = new RedisClusterConfiguration(nodes);

            return new LettuceConnectionFactory(redisConfig, lettuceConfig);
        } else {
            log.info("Aplicando Redis STANDALONE configuration {}", envRedisHost);

            final LettuceClientConfiguration lettuceConfig = LettuceClientConfiguration.builder()
                    .commandTimeout(Duration.ofMillis(timeout))
                    .shutdownTimeout(Duration.ZERO)
                    .build();

            final RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(envRedisHost);

            return new LettuceConnectionFactory(redisConfig, lettuceConfig);
        }
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(timeToLive))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(Student.CACHE_STUDENT_LIST,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(timeToLive)))
                .withCacheConfiguration(Student.CACHE_ID,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMillis(timeToLive)));
    }
}