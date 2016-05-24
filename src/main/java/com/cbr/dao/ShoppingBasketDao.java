package com.cbr.dao;

import com.cbr.domain.Grocery;

import java.util.List;


/**
 * Created by sureshsharma on 24/05/2016.
 */
public interface ShoppingBasketDao {

    void add(List<Grocery> groceries);

    void remove(List<Grocery> groceries);

    List<Grocery> getGroceries();
}
