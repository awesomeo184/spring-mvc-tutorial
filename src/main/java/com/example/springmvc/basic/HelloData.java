package com.example.springmvc.basic;

import lombok.Data;


/*
* @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode를 적용해준다.
* */
@Data
public class HelloData {

    private String username;
    private int age;

}
