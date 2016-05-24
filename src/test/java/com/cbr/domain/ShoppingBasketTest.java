package com.cbr.domain;

/**
 * Created by sureshsharma on 24/05/2016.
 */
import com.cbr.exceptions.ShoppingBasketException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ShoppingBasketTest {

    private ShoppingBasket shoppingBasket = new ShoppingBasket();

    @Test(expected = ShoppingBasketException.class)
    public void should_throw_exception_when_invalid_item_added_to_shopping_basket(){
        shoppingBasket.add(null);
    }

    @Test
    public void should_be_able_to_add_groceries_in_shopping_basket(){
        //given
        Grocery grocery1 = mock(Grocery.class);
        Grocery grocery2 = mock(Grocery.class);

        //when
        shoppingBasket.add(grocery1);
        shoppingBasket.add(grocery2);
        List<Grocery> items = shoppingBasket.getItems();

        //then
        assertThat(items, containsInAnyOrder(grocery1, grocery2));
    }

    @Test
    public void should_calculate_total_cost_as_0_for_empty_shopping_basket(){
        //given
        assertThat(shoppingBasket.getItems().isEmpty(), is(true));

        //when
        BigDecimal subTotal = shoppingBasket.calculateTotal();

        //then
        assertThat(subTotal, is(valueOf(0)));
    }

    @Test
    public void should_calculate_total_cost_as_for_two_items(){
        //given
        Grocery grocery1 = mock(Grocery.class);
        Grocery grocery2 = mock(Grocery.class);
        given(grocery1.getCost()).willReturn(valueOf(0.20));
        given(grocery2.getCost()).willReturn(valueOf(0.20));
        shoppingBasket.add(grocery1, grocery2);

        //when
        BigDecimal total = shoppingBasket.calculateTotal();

        //then
        assertThat(total, is(valueOf(0.40)));
    }
}
