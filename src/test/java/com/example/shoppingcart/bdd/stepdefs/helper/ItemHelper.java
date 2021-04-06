package com.example.shoppingcart.bdd.stepdefs.helper;

import com.example.shoppingcart.bdd.config.CucumberTestContext;
import com.example.shoppingcart.service.model.ItemDto;

import java.util.List;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 9:41 PM
 **/
public class ItemHelper {
    public static Long getItemFromContext(Long itemId, String contextKey) {
        Long id = -1L;
        if (getTextContext().get(contextKey) instanceof List<?>) {
            final Object item = ((List<?>) getTextContext().get(contextKey)).get(itemId.intValue() - 1);
            id = ((ItemDto) item).getId();
        }
        return id;
    }

    private static CucumberTestContext getTextContext() {
        return CucumberTestContext.CONTEXT;
    }
}
