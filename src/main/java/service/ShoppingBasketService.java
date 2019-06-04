package service;

import model.BasketItem;
import model.Offer;
import model.ShoppingBasket;

import java.math.BigDecimal;
import java.util.ArrayList;



public interface ShoppingBasketService {
	
    ArrayList<Offer> applyOffers(ArrayList<BasketItem> items);
	
	BigDecimal calculateDiscount(BasketItem item, BigDecimal discount);

	BigDecimal calculateSubTotal(ArrayList<BasketItem> items);

	BigDecimal calculateTotal(BigDecimal subTotal, BigDecimal totalDiscount);

	BigDecimal calculateTotalDiscount(ArrayList<Offer> offers);

	ShoppingBasket generateBasketItems(String[] args, ShoppingBasket shoppingBasket);

}
