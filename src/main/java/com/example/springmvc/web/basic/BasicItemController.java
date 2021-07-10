package com.example.springmvc.web.basic;

import com.example.springmvc.domain.item.Item;
import com.example.springmvc.domain.item.ItemRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
* 여기서 itemRepository의 주입이 어떻게 일어나는가?
* @RequiredArgsConstructor는 final이 붙은 필드의 생성자를 만들어줌 -> 생성자가 하나 있으면 @Autowired가 없어도 주입을 받을 수 있음
* */
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    /*
    * @ModelAttribute는 Item 객체를 생성해주고 요청 파라미터의 값을 세터로 설정해준다.
    * 또한 이름을 넘겨주면 Model에 해당 이름으로 객체를 자동으로 넘겨준다.
    * 이름을 생략하면 객체의 클래스명에 첫글자를 소문자로 바꾼 뒤 이름을 넘긴다.
    * */
//    @PostMapping("/add")
    public String save(@ModelAttribute("item") Item item) {

        itemRepository.save(item);
//        model.addAttribute("item", item); @ModelAttribute에 name 지정해주면 생략가능
        return "basic/item";
    }

//    @PostMapping("/add")
    public String save1(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items /";
    }

    @PostMapping("/add")
    public String save2(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
