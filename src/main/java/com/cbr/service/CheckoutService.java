package com.cbr.service;

import com.cbr.domain.Grocery;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sureshsharma on 24/05/2016.
 */
public interface CheckoutService {
    BigDecimal calculateTotalFor(List<Grocery> groceries);
}
