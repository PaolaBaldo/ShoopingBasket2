package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.BasketItem;
import model.Offer;
import model.ShoppingBasket;
import util.Constants;


public class ShoppingBasketServiceTest_ApplesAndBreadDiscount {

	private ArrayList<BasketItem> mockedBasketItemList;
	private ArrayList<Offer> expectedOfferList;

	@Before
	public void init() {
		mockedBasketItemList = this.getMockedBasketItemList();
		expectedOfferList = this.getExpectedOfferList();
	}
	
	public ArrayList<Offer> getExpectedOfferList() {
		ArrayList<Offer> offerList = new ArrayList<Offer>();
        BasketItem apple = new BasketItem(Constants.APPLES, Constants.APPLES_PRICE, 1 );
        BasketItem bread = new BasketItem(Constants.BREAD, Constants.BREAD_PRICE, 1 );
        offerList.add(new Offer(apple, Constants.APPLES_PERCENTAGE_OFF, new BigDecimal(0.1).setScale(2, RoundingMode.HALF_EVEN)));
        offerList.add(new Offer(bread, Constants.BREAD_PERCENTAGE_OFF, new BigDecimal(0.4).setScale(2, RoundingMode.HALF_EVEN)));
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
		
		BasketItem soup = new BasketItem();
        soup.setQuantity(2);
        soup.setItemCost(Constants.SOUP_PRICE);
        soup.setItemName(Constants.SOUP);
		shoppingBasket.getItems().add(soup);

		return shoppingBasket.getItems();
	}
	
	@Test
	public void shoppingCartApplyOffers_applesAndBreadDiscount() {
		ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl();
		ArrayList<Offer> actualOffers = shoppingBasketService.applyOffers(this.mockedBasketItemList);
		ArrayList<Offer> expectedOffers = this.expectedOfferList;

		for(Offer mockerOffer: expectedOffers) {
			for(Offer actualOffer: actualOffers ) {
                if(actualOffer.getAffectedItem().getItemName().equals(mockerOffer.getAffectedItem().getItemName())){
                    Assert.assertEquals(mockerOffer.getDiscount(), actualOffer.getDiscount());
                }
			}
		}
	}

    @Test
    public void calculateSubTotal() {
        ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl();
        ArrayList<BasketItem> items = this.mockedBasketItemList;
        BigDecimal subTotal = shoppingBasketService.calculateSubTotal(items);

        Assert.assertEquals(new BigDecimal(4.40).setScale(2, RoundingMode.HALF_EVEN), subTotal);
    }

    @Test
    public void calculateTotal() {
        ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl();
        BigDecimal totalDiscount = new BigDecimal(0.50).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal subTotal = new BigDecimal(4.40).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal total = shoppingBasketService.calculateTotal(subTotal, totalDiscount);
        Assert.assertEquals(new BigDecimal(3.90).setScale(2, RoundingMode.HALF_EVEN), total);
    }
	
	

}
