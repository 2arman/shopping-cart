package com.example.shoppingcart.controller.bo;

import com.example.shoppingcart.service.model.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:09 AM
 **/
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/bo/items")
public class BatchItemController {

    private Set<ItemDto> storedItems;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getStoredItems() {
        return new ArrayList<>(storedItems);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addItems(@Valid @RequestBody List<ItemDto> items) {
        this.storedItems = new HashSet<>(items);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteItems(@Valid @RequestBody List<ItemDto> items) {
        storedItems.removeAll(items);
    }
}
