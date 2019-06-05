package service;


import model.BasketItem;
import model.Offer;
import model.ShoppingBasket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class TestShoppingBasketServiceTest_ApplesDiscount {
	

    private ArrayList<BasketItem> mockedBasketItemList;
    private ArrayList<Offer> expectedOfferList;
 
    @Before
    public void init() {
    	mockedBasketItemList = this.getMockedBasketItemList();
    	expectedOfferList = this.getExpectedOfferList_applesDiscount();
    }
    
	public ArrayList<Offer> getExpectedOfferList_applesDiscount() {
		ArrayList<Offer> offerList = new ArrayList<Offer>();
		for(BasketItem item : this.mockedBasketItemList) {
			if(item.getItemName().equals(Constants.APPLES)) {
				offerList.add(new Offer(item, Constants.APPLES_PERCENTAGE_OFF, new BigDecimal(0.1).setScale(2, RoundingMode.HALF_EVEN)));
			}
		}
		return offerList;
	}
	
	private ArrayList<BasketItem> getMockedBasketItemList() {
		ShoppingBasket shoppingBasket = new ShoppingBasket();
		BasketItem apples_ = new BasketItem();
		apples_.setQuantity(1);
		apples_.setItemCost(Constants.APPLES_PRICE);
		apples_.setItemName(Constants.APPLES);
		shoppingBasket.getItems().add(apples_);
		
		BasketItem milk_ = new BasketItem();
		milk_.setQuantity(1);
		milk_.setItemCost(Constants.MILK_PRICE);
		milk_.setItemName(Constants.MILK);
		shoppingBasket.getItems().add(milk_);
		
		BasketItem bread_ = new BasketItem();
		bread_.setQuantity(1);
		bread_.setItemCost(Constants.BREAD_PRICE);
		bread_.setItemName(Constants.BREAD);
		shoppingBasket.getItems().add(bread_);
		
		return shoppingBasket.getItems();
	}
	
	
	@Test
	public void calculateApplesDiscount() {
		ShoppingBasketService priceBasketService = new ShoppingBasketServiceImpl();
		BasketItem item = new BasketItem(Constants.APPLES, Constants.APPLES_PRICE,1);
		BigDecimal itemDiscount = priceBasketService.calculateDiscount(item, Constants.APPLES_PERCENTAGE_OFF);
		Assert.assertEquals(itemDiscount, new BigDecimal(0.10).setScale(2, RoundingMode.HALF_EVEN));
	}
	
	
	@Test
	public void calculateBreadDiscount() {
		ShoppingBasketService priceBasketService = new ShoppingBasketServiceImpl();
		BasketItem item = new BasketItem(Constants.BREAD, Constants.BREAD_PRICE,1);
		BigDecimal itemDiscount = priceBasketService.calculateDiscount(item, Constants.BREAD_PERCENTAGE_OFF);
		Assert.assertEquals(itemDiscount, new BigDecimal(0.4).setScale(2, RoundingMode.HALF_EVEN));
	}
	
	@Test
	public void calculateSubTotal() {
		ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl();
		ArrayList<BasketItem> items = this.mockedBasketItemList;
		BigDecimal subTotal = shoppingBasketService.calculateSubTotal(items);
		
		Assert.assertEquals(subTotal, new BigDecimal(3.10).setScale(2, RoundingMode.HALF_EVEN));
	}
	
	

	@Test
	public void calculateTotalDiscount() {
		ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl();
		ArrayList<Offer> offers = this.expectedOfferList;
		BigDecimal totalDiscount = shoppingBasketService.calculateTotalDiscount(offers);
		Assert.assertEquals(totalDiscount, new BigDecimal(0.1).setScale(2, RoundingMode.HALF_EVEN));
	}
	
	@Test
	public void calculateTotal() {
		ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl();
		BigDecimal totalDiscount = new BigDecimal(0.10).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal subTotal = new BigDecimal(3.10).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal total = shoppingBasketService.calculateTotal(subTotal, totalDiscount);
		Assert.assertEquals(total, new BigDecimal(3.00).setScale(2, RoundingMode.HALF_EVEN));
	}


	@Test
	public void shoppingCartApplyOffers_applesDiscount() {
		ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl();
		ArrayList<Offer> actualOffers = shoppingBasketService.applyOffers(this.mockedBasketItemList);
		ArrayList<Offer> expectedOffers = this.expectedOfferList;
		for(Offer mockerOffer: expectedOffers) {
			for(Offer actualOffer: actualOffers ) {
				Assert.assertEquals(mockerOffer.getDiscount(), actualOffer.getDiscount());	
			}
		}
	}
		
	
}
