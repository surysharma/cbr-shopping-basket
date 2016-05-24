package com.cbr.service.impl;

/**
 * Created by sureshsharma on 24/05/2016.
 */

import com.cbr.dao.ShoppingBasketDao;
import com.cbr.domain.Grocery;
import com.cbr.exceptions.ShoppingBasketException;
import com.cbr.service.CheckoutService;
import com.cbr.service.ShoppingBasketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingBasketServiceImplTest {

    @Mock
    private ShoppingBasketDao shoppingBasketDao;
    @Mock
    private CheckoutService checkoutService;

    @Captor
    ArgumentCaptor<List<Grocery>> groceryArgumentCaptor;

    @InjectMocks
    private ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl(checkoutService, shoppingBasketDao);

    @Test(expected = ShoppingBasketException.class)
    public void should_throw_exception_when_invalid_item_added_to_shopping_basket(){
        shoppingBasketService.add(null);
    }

    @Test
    public void should_be_able_to_add_groceries_in_shopping_basket(){
        //given
        Grocery grocery1 = mock(Grocery.class);
        Grocery grocery2 = mock(Grocery.class);

        //when
        shoppingBasketService.add(grocery1, grocery2);

        //then
        verify(shoppingBasketDao).add(groceryArgumentCaptor.capture());
        assertThat(groceryArgumentCaptor.getValue(), containsInAnyOrder(grocery1, grocery2));
    }

    @Test
    public void should_get_groceries_from_shopping_basket(){
        //given
        Grocery grocery1 = mock(Grocery.class);
        Grocery grocery2 = mock(Grocery.class);
        //and
        given(shoppingBasketDao.getGroceries()).willReturn(asList(grocery1, grocery2));


        //when
        List<Grocery> groceries = shoppingBasketService.getGroceries();

        //then
        assertThat(groceries, containsInAnyOrder(grocery1, grocery2));
    }

    @Test(expected = ShoppingBasketException.class)
    public void should_throw_exception_when_invalid_item_removed_from_shopping_basket(){
        shoppingBasketService.remove(null);
    }

    @Test
    public void should_be_able_to_remove_groceries_in_shopping_basket(){
        //when
        shoppingBasketService.remove(Matchers.any(Grocery.class));

        //then
        verify(shoppingBasketDao).remove(anyList());
    }

    @Test
    public void should_compute_total_in_shopping_basket(){
        //given
        List<Grocery> groceries = asList(mock(Grocery.class), mock(Grocery.class));
        given(shoppingBasketDao.getGroceries()).willReturn(groceries);
        //and
        BigDecimal someTotal = valueOf(20);
        given(checkoutService.calculateTotalFor(groceries)).willReturn(someTotal);

        // when
        BigDecimal totalCost = shoppingBasketService.getTotal();

        // then
        assertThat(totalCost, is(someTotal));
    }
}
