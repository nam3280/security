package com.top.security.service.serviceImpl;

import com.top.security.domain.Item;
import com.top.security.repository.ItemRepository;
import com.top.security.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getList() {
        return itemRepository.findAll();
    }
}
