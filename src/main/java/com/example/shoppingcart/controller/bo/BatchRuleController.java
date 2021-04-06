package com.example.shoppingcart.controller.bo;

import com.example.shoppingcart.service.RuleService;
import com.example.shoppingcart.service.model.RuleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:09 AM
 **/
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/bo/items")
public class BatchRuleController {

    private final RuleService ruleService;

    @GetMapping("/{id}/rules")
    @ResponseStatus(HttpStatus.OK)
    public List<RuleDto> getRules(@NotNull @Positive @PathVariable("id") Long itemId) {
        return ruleService.getAll(itemId);
    }


    @PostMapping("/{id}/rules")
    @ResponseStatus(HttpStatus.CREATED)
    public List<RuleDto> addAllRules(@NotNull @Positive @PathVariable("id") Long itemId, @Valid @RequestBody List<RuleDto> rules) {
        return ruleService.addAll(itemId, rules);
    }

    @DeleteMapping("/{id}/rules")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllRules(@NotNull @Positive @PathVariable("id") Long itemId) {
        ruleService.removeAll(itemId);
    }
}
