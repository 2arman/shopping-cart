package com.example.shoppingcart.service.mapper;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.service.model.CartDto;
import com.example.shoppingcart.service.model.ItemQuantityDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 7:13 PM
 **/
@Mapper(componentModel = "spring")
@Component
@RequiredArgsConstructor
public abstract class CartMapper {
    private ItemMapper itemMapper;

    @Autowired
    public CartMapper setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
        return this;
    }

    @Mapping(source = "itemQuantityMap", target = "itemQuantity", qualifiedByName = "itemQuantityMapper")
    public abstract CartDto map(Cart cart);

    @Named("itemQuantityMapper")
    public List<ItemQuantityDto> map(Map<Item, Cart.QuantityPrice> itemQuantityMap) {
        return itemQuantityMap.entrySet()
                .stream()
                .filter(itemQuantityPriceEntry -> itemQuantityPriceEntry.getValue().getQuantity() > 0)
                .map(itemQuantityPriceEntry -> ItemQuantityDto.builder()
                        .item(itemMapper.map(itemQuantityPriceEntry.getKey()))
                        .quantity(itemQuantityPriceEntry.getValue().getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}
