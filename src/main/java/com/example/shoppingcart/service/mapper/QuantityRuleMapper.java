package com.example.shoppingcart.service.mapper;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.domain.QuantityRule;
import com.example.shoppingcart.domain.Rule;
import com.example.shoppingcart.service.model.ItemDto;
import com.example.shoppingcart.service.model.RuleDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 7:13 PM
 **/
@Mapper(componentModel = "spring")
@Component
public interface QuantityRuleMapper {

    RuleDto map(QuantityRule item);

    QuantityRule map(RuleDto item);

    List<RuleDto> mapRules(Collection<? extends QuantityRule> items);

    List<QuantityRule> mapRulesDto(Collection<? extends RuleDto> items);

}
