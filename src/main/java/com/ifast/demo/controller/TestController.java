package com.ifast.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 测试专用
 * </pre>
 * 
 * <small> 2018年1月16日 | Aron</small>
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/session")
    public void session(HttpServletRequest request) {
        System.out.println("request class : " + request.getClass());
        System.out.println("request session : " + request.getSession());

    }

    @RequestMapping("/redis")
    public void redis() {
        Set<String> keys = stringRedisTemplate.keys("*");
        System.out.println(keys);
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("Aron", "hello Aron.");
        String aron = stringStringValueOperations.get("Aron");
        System.out.println(aron);

        Long expire = stringRedisTemplate.getExpire("Aron");
        Long expire2 = stringRedisTemplate.getExpire("Aron", TimeUnit.MICROSECONDS);
        Long expire3 = stringRedisTemplate.getExpire("Aron", TimeUnit.SECONDS);
        System.out.println(expire);
        System.out.println(expire2);
        System.out.println(expire3);

    }



}
