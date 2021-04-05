package com.example.shoppingcart.controller.bo;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.service.RuleService;
import com.example.shoppingcart.service.model.RuleDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:09 AM
 **/
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/bo/rules/item")
public class BatchRuleController {

    private final RuleService ruleService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<RuleDto> getRules(@NotNull @Positive @PathVariable("id") Long itemId) {
        return new ArrayList<>();
    }


    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRules(@NotNull @Positive @PathVariable("id") Long itemId,@Valid @RequestBody List<RuleDto> rules) {

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRule(@NotNull @Positive @PathVariable("id") Long itemId, @Valid @RequestBody List<RuleDto> rules) {

    }
}
