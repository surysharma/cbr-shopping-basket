package com.cbr.service.impl;

import com.cbr.domain.Grocery;
import com.cbr.service.CheckoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by sureshsharma on 24/05/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    @InjectMocks
    private CheckoutService checkoutService = new CheckoutServiceImpl();

    @Test
    public void should_calculate_total_cost_as_0_for_empty_shopping_basket(){
        //when
        BigDecimal subTotal = checkoutService.calculateTotalFor(asList());

        //then
        assertThat(subTotal, is(valueOf(0)));
    }

    @Test
    public void should_calculate_total_cost_as_for_two_items(){
        //given
        Grocery grocery1 = mock(Grocery.class);
        Grocery grocery2 = mock(Grocery.class);
        //and
        given(grocery1.getCost()).willReturn(valueOf(0.20));
        given(grocery2.getCost()).willReturn(valueOf(0.30));

        //when
        BigDecimal total = checkoutService.calculateTotalFor(asList(grocery1, grocery2));

        //then
        assertThat(total, is(valueOf(0.50)));
    }
}
