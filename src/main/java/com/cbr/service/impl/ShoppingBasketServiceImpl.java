package com.cbr.service.impl;

/**
 * Created by sureshsharma on 24/05/2016.
 */
import com.cbr.dao.ShoppingBasketDao;
import com.cbr.domain.Grocery;
import com.cbr.exceptions.ShoppingBasketException;
import com.cbr.service.CheckoutService;
import com.cbr.service.ShoppingBasketService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private CheckoutService checkoutService;
    private ShoppingBasketDao shoppingBasketDao;

    public ShoppingBasketServiceImpl(CheckoutService checkoutService, ShoppingBasketDao shoppingBasketDao) {

        this.checkoutService = checkoutService;
        this.shoppingBasketDao = shoppingBasketDao;
    }

    public void add(Grocery... groceries) {
        if (groceries == null)
            throw new ShoppingBasketException();

        shoppingBasketDao.add(asList(groceries));
    }

    @Override
    public void remove(Grocery... groceries) {
        if (groceries == null)
            throw new ShoppingBasketException();
        shoppingBasketDao.remove(asList(groceries));
    }

    @Override
    public List<Grocery> getGroceries() {
        return shoppingBasketDao.getGroceries();
    }

    @Override
    public BigDecimal getTotal() {
        List<Grocery> groceries = shoppingBasketDao.getGroceries();
        return checkoutService.calculateTotalFor(groceries);
    }
}
