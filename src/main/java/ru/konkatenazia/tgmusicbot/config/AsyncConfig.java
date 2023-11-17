package ru.konkatenazia.tgmusicbot.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Value("${async.pool.corePoolSize}")
    private int corePoolSize;

    @Value("${async.pool.maxPoolSize}")
    private int maxPoolSize;

    @Value("${async.pool.aliveTimeout}")
    private int aliveTimeout;

    @Value("${async.pool.queueSize}")
    private int queueSize;

    @Bean(name = "saveMusicAsync")
    public ExecutorService executeLegalLimitIssueAsync(BeanFactory beanFactory) {
        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                aliveTimeout,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize)
        );
    }
}
