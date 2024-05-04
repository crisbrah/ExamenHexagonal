package com.codigo.msperezhuatuco.infraestructure.redis;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor

public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public void saveInRedis(String key, String value, int exp){
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, exp, TimeUnit.MINUTES);
    }
    public String getFromRedis(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    public void deleteKey(String key){
        stringRedisTemplate.delete(key);
    }
}
