package com.cbr.domain;

/**
 * Created by sureshsharma on 24/05/2016.
 */
import com.cbr.exceptions.ShoppingBasketException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

public class ShoppingBasket {

    private List<Grocery> groceries = new ArrayList<>();

    public void add(Grocery... groceries) {
        if (groceries == null)
            throw new ShoppingBasketException();

        this.groceries.addAll(asList(groceries));
    }


    public List<Grocery> getItems() {
        return groceries;
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = groceries.stream()
                .map(grocery -> grocery.getCost())
                .reduce(ZERO, BigDecimal::add);
        return total;
    }
}
