package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.repository.ItemRepository;
import com.example.shoppingcart.service.ItemService;
import com.example.shoppingcart.service.exception.ItemNotFoundException;
import com.example.shoppingcart.service.mapper.ItemMapper;
import com.example.shoppingcart.service.model.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 7:04 PM
 **/
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    @Override
    public List<ItemDto> getAllItems() {
        return mapper.mapItems(itemRepository.findAll());
    }

    @Override
    public List<ItemDto> addAll(List<ItemDto> items) {
        return mapper.mapItems(
                itemRepository.saveAll(
                        mapper.mapItemsDto(items)));
    }

    @Override
    public void removeAll(List<ItemDto> items) {
        itemRepository.deleteAll(
                mapper.mapItemsDto(items));
    }
}
