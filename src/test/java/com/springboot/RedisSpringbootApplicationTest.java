package com.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
public class RedisSpringbootApplicationTest {

//    @Autowired
//    @Qualifier("redisTemplate")
//    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

//    @Test
//    void contextLoads(){
//        redisTemplate.opsForValue().set("key","cjb");
//        System.out.println(redisTemplate.opsForValue().get("key"));
//    }

    @Test
    public void test()throws JsonProcessingException {
        redisUtil.set("password","123456");
        System.out.println(redisUtil.get("password"));
    }
}
