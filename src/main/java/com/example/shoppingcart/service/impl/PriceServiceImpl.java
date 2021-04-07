package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.domain.QuantityRule;
import com.example.shoppingcart.service.PriceService;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 2:44 PM
 **/
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private static final double MAX_VALUE = Integer.MAX_VALUE;

    @Override
    public Double calculatePrice(Item item, int quantity) {
        if (quantity == 0) {
            return 0.0;
        }
        double[] values = new double[quantity * 2];
        int[] weights = new int[quantity * 2];
        for (int i = 0; i <= quantity; i++) {
            weights[i] = 1;
            values[i] = item.getUnitPrice();

        }
        var started = quantity;
        for (QuantityRule rule : item.getRules()) {
            final var ruleNeeded = Math.floor((double) quantity / rule.getQuantity());
            for (int i = started +1 ; i <= started + ruleNeeded; i++) {
                weights[i] = rule.getQuantity();
                values[i] = rule.getPrice();
            }
            started += ruleNeeded ;
        }
        return calculateBestPrice(values, weights, quantity);
    }

    private double calculateBestPrice(double[] values, int[] weights, int weightLimit) {
        int N = weights.length; // Get the total number of items. Could be weights.length or values.length. Doesn't matter
        double[][] V = new double[N + 1][weightLimit + 1]; //Create a matrix. Items are in rows and weight at in columns +1 on each side
        //What if the knapsack's capacity is 0 - Set all columns at row 0 to be 0
        for (int col = 0; col <= weightLimit; col++) {
            V[0][col] = MAX_VALUE;
        }
        //What if there are no items at home.  Fill the first row with 0
        for (int row = 0; row <= N; row++) {
            V[row][0] = 0;
        }
        for (int item = 1; item <= N; item++) {
            //Let's fill the values row by row
            for (int weight = 1; weight <= weightLimit; weight++) {
                //Is the current items weight less than or equal to running weight
                if (weights[item - 1] <= weight) {
                    //Given a weight, check if the value of the current item + value of the item that we could afford with the remaining weight
                    //is less than the value without the current item itself
                    V[item][weight] = Math.min(values[item - 1] + V[item - 1][weight - weights[item - 1]], V[item - 1][weight]);
                } else {
                    //If the current item's weight is more than the running weight, just carry forward the value without the current item
                    V[item][weight] = V[item - 1][weight];
                }
            }
        }
        return V[N][weightLimit];
    }
}
