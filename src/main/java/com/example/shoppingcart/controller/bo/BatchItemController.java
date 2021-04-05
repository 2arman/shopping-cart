package com.example.shoppingcart.controller.bo;

import com.example.shoppingcart.service.ItemService;
import com.example.shoppingcart.service.model.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Arman
 * Date: 4/5/21
 * Time: 1:09 AM
 **/
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/bo/items")
public class BatchItemController {
    private final ItemService itemService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public List<ItemDto> addItems(@Valid @RequestBody List<ItemDto> items) {
        return itemService.addAll(items);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteItems(@Valid @RequestBody List<ItemDto> items) {
        itemService.removeAll(items);
    }
}
