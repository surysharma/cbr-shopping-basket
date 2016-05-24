package com.cbr.service;

import com.cbr.domain.Grocery;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sureshsharma on 24/05/2016.
 */
public interface ShoppingBasketService {

    void add(Grocery... groceries);

    void remove(Grocery... grocery);

    List<Grocery> getGroceries();

    BigDecimal getTotal();
}
