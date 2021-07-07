package com.example.springmvc.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    /*
    * 원래는 동시성 고려해서 ConcurrentHashMap, atomic Long을 사용해야함
    * */
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /*
    * 이 경우 ItemParamDto 객체를 따로 만들어서 이름, 가격, 수량 필드를 갖는 객체를 하나 만드는 것이 좋다.
    * */
    public void update(Long itemId, Item updateParam) {
        Item item = store.get(itemId);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
