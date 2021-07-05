package com.example.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Controller는 String이 반환되면 뷰를 찾지만
 * @RestController는 HTTP 메시지 바디에 String을 입력한다.*/
@Slf4j
@RestController
public class MappingController {

    /*
     * 배열로 제공되어서 여러 개를 넣어도 된다.
     * 마지막에 슬래시가 추가되어도 스프링은 같은 URL로 매핑한다.
     * */
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        return "ok";
    }

    /*
     * @PathVariable
     *
     * 변수 명이 같으면 생략가능: @PathVariable String userId
     * /mapping/userA*/
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /*
    * @PathVariable 다중사용
    * */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /*
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     *
     * 특정 파라미터가 있어야만 메서드가 호출된다(잘 사용할 일은 없다).
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /*
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     * 특정 헤더 정보가 있어야 호출됨(Postman으로 테스트)
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /*
     * Content-Type 헤더 기반 추가 매핑
     * Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     *
     * 헤더 정보를 이용해도되지만 추가 기능이 제공된다.
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /*
     * Accept 헤더 기반 Media Type * produces = "text/html"
     * produces = "!text/html" * produces = "text/*"
     * produces = "*\/*"
     * 이 예제에서는 Accept가 만약 application/json이라면 406 Not Acceptable 반환
    */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
