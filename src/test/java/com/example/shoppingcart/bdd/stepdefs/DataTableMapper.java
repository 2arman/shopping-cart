package com.example.shoppingcart.bdd.stepdefs;

import com.example.shoppingcart.service.model.ItemDto;
import com.example.shoppingcart.service.model.RuleDto;
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
    public static DataTable toItemDataTable(List<ItemDto> items) {
        List<List<String>> listOfList = new ArrayList<>();
        listOfList.add(Arrays.asList("name", "unitPrice"));
        listOfList.addAll(items.stream().
                map(item ->
                        Arrays.asList(item.getName(),
                                String.valueOf(item.getUnitPrice())))
                .collect(Collectors.toList()));
        return DataTable.create(listOfList);
    }

    public static DataTable toRuleDataTable(List<RuleDto> rules) {
        List<List<String>> listOfList = new ArrayList<>();
        listOfList.add(Arrays.asList("quantity", "price"));
        listOfList.addAll(
                rules.stream()
                        .map(rule ->
                                Arrays.asList(String.valueOf(rule.getQuantity()),
                                        String.valueOf(rule.getPrice())))
                        .collect(Collectors.toList()));
        return DataTable.create(listOfList);
    }
}
