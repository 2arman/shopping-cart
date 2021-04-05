package com.example.shoppingcart.repository;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.repository.base.SequenceGenerator;
import com.example.shoppingcart.repository.base.SimpleRepositoryBase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:14 AM
 **/
@Repository
@RequiredArgsConstructor
@Getter
public final class ItemRepository extends SimpleRepositoryBase<Item> {
    private final SequenceGenerator sequenceGenerator;
}
