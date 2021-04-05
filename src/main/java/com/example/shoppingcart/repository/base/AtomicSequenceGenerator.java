package com.example.shoppingcart.repository.base;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope("prototype")
public class AtomicSequenceGenerator implements SequenceGenerator {

    private AtomicLong value = new AtomicLong(1);

    @Override
    public long getNext() {
        return value.getAndIncrement();
    }
}