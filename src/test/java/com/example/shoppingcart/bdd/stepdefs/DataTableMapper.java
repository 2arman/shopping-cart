package com.example.shoppingcart.bdd.stepdefs;

import com.example.shoppingcart.service.model.ItemDto;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arman
 * Date: 4/5/21
 * Time: 3:17 PM
 **/
public class DataTableMapper {
    public static DataTable toItemDataTable(List<ItemDto> itemList) {
        List<List<String>> listOfList = new ArrayList<>();
        listOfList.add(Arrays.asList("name", "unitPrice"));
        listOfList.addAll(itemList.stream().
                map(itemDto ->
                        Arrays.asList(itemDto.getName(),
                                String.valueOf(itemDto.getUnitPrice())))
                .collect(Collectors.toList()));
        return DataTable.create(listOfList);
    }
}
