package com.example.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 롬복으로 로그 설정 가능
@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        /*
        * 이 두 개 차이
        * + 연산자를 사용하면 해당 메서드가 호출되지 않아도 문자열 연산이 일어난다.
        * 즉 내가 로그레벨을 debug로 설정했어도 문자열 연산이 일어나기 때문에 리소스 낭비
        * 그래서 로그를 찍을 때는 첫번째 형태로 찍어야한다.*/
        log.trace("trace log={}", name);
        log.trace("trace log={}" + name);

        /*
         * trace와 debug는 설정정보에 로그레벨을 추가해줘야함*/
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);

        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
