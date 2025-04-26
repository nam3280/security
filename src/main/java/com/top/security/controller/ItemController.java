package com.top.security.controller;

import com.top.security.domain.Item;
import com.top.security.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items")
    public List<Item> getItems(){
        return itemService.getList();
    }
}
