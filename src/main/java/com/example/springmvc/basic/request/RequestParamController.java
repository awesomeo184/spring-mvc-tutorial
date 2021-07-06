package com.example.springmvc.basic.request;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    /*
    * 문자열을 반환할 때 뷰를 찾는게 아니라 HTTP 응답 메시지에 담음
    * */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String memberId,
        @RequestParam("age") int memberAge) {

        log.info("username = {}, age = {}", memberId, memberAge);

        return "ok";
    }

    /*
    * 파라미터 이름과 변수 이름이 같으면 생략 가능
    * */

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    /*
    * 단순 타입인 경우 @RequestParam도 생략 가능
    * */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    /*
    * 인자로 required 설정 가능
    * true가 기본값이고 false로 설정할 경우 파라미터가 없어도 오류가 발생하지 않음
    *
    * */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
        @RequestParam(required = true) String username,
        @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    /*
    * 빈 문자의 경우에도 디폴트값이 들어감
    * */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
        @RequestParam(defaultValue = "guest") String username,
        @RequestParam(defaultValue = "-1") int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }
}
