package com.example.shoppingcart.service;

import com.example.shoppingcart.service.model.RuleDto;

import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:25 AM
 **/
public interface RuleService {
    List<RuleDto> getAll(Long itemId);

    List<RuleDto> addAll(Long itemId, List<RuleDto> rules);

    void removeAll(Long itemId);
}
