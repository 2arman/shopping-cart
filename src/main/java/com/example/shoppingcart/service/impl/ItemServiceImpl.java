package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.domain.Item;
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
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getAllItems() {
        return itemMapper.mapItems(itemRepository.findAll());
    }

    @Override
    public List<ItemDto> addAll(List<ItemDto> items) {
        return itemMapper.mapItems(
                itemRepository.saveAll(
                        itemMapper.mapItemsDto(items)));
    }

    @Override
    public void removeAll(List<ItemDto> items) {
        itemRepository.deleteAll(
                itemMapper.mapItemsDto(items));
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
    }
}
