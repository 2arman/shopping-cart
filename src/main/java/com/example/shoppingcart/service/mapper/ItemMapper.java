package com.example.shoppingcart.service.mapper;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.service.model.ItemDto;
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
public interface ItemMapper {

    ItemDto map(Item item);

    Item map(ItemDto item);

    List<ItemDto> mapItems(Collection<Item> items);

    List<Item> mapItemsDto(Collection<ItemDto> items);

}
