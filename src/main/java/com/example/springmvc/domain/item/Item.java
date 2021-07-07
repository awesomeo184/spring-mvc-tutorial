package com.example.springmvc.domain.item;

import lombok.Data;

/*
* 일반적으로 주요 도메인에는 @Data 애너테이션을 붙이지 않는 것이 좋다.
* DTO의 경우 충분히 문제가 없는지 고려해서 사용
* */
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {}

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
