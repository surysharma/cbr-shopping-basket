package com.cbr.service.impl;

import com.cbr.domain.Grocery;
import com.cbr.service.CheckoutService;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sureshsharma on 24/05/2016.
 */
public class CheckoutServiceImpl implements CheckoutService {

    @Override
    public BigDecimal calculateTotalFor(List<Grocery> groceries) {
        return groceries.stream()
                .map(Grocery::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
