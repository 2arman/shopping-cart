package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.repository.ItemRepository;
import com.example.shoppingcart.service.RuleService;
import com.example.shoppingcart.service.exception.ItemNotFoundException;
import com.example.shoppingcart.service.mapper.QuantityRuleMapper;
import com.example.shoppingcart.service.model.RuleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 7:07 PM
 **/
@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {
    private final ItemRepository itemRepository;
    private final QuantityRuleMapper quantityRuleMapper;

    @Override
    public List<RuleDto> getAll(Long itemId) {
        var item = getItemById(itemId);
        return quantityRuleMapper.mapRules(item.getRules());
    }

    @Override
    public List<RuleDto> addAll(Long itemId, List<RuleDto> rules) {
        var item = getItemById(itemId);
        item.getRules().addAll(quantityRuleMapper.mapRulesDto(rules));
        return quantityRuleMapper.mapRules(item.getRules());
    }

    @Override
    public void removeAll(Long itemId) {
        var item = getItemById(itemId);
        item.getRules().removeAll(item.getRules());
    }

    private Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));
    }
}
