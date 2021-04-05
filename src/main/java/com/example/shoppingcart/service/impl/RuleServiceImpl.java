package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.service.RuleService;
import com.example.shoppingcart.service.model.RuleDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 7:07 PM
 **/
@Service
public class RuleServiceImpl implements RuleService {

    @Override
    public List<RuleDto> getAll(Long itemId) {
        return null;
    }

    @Override
    public void addAll(Long itemId, List<RuleDto> rules) {

    }

    @Override
    public void removeAll(Long itemId, List<RuleDto> rules) {

    }
}
