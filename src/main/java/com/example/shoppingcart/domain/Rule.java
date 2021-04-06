package com.example.shoppingcart.domain;

import com.example.shoppingcart.domain.base.BaseEntity;
import lombok.Data;

/**
 * @author Arman
 * Date: 4/5/21
 * Time: 10:15 AM
 **/
@Data
public abstract class Rule implements BaseEntity {
    private Long Id;
}
