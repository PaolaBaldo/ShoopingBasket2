package service;

import model.BasketItem;
import model.Offer;
import model.ShoppingBasket;
import util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ShoppingBasketServiceImpl implements ShoppingBasketService {
	

	public ArrayList<Offer> applyOffers(ArrayList<BasketItem> items) {
		ArrayList<Offer> offers = new ArrayList<Offer>();
		for(BasketItem item : items) {
			if(item.getItemName().equals(Constants.APPLES) && item.getQuantity()>=1){
				BigDecimal applesDiscount = this.calculateDiscount(item, Constants.APPLES_PERCENTAGE_OFF);
				Offer offer = new Offer(item, Constants.APPLES_PERCENTAGE_OFF, applesDiscount);
				offers.add(offer);
			}
			if(item.getItemName().equals(Constants.SOUP) && item.getQuantity()>=2) {
                BasketItem bread = this.verifyBreadItem(items);
				if(bread != null) {
					BigDecimal breadDiscount = this.calculateDiscount(bread, Constants.BREAD_PERCENTAGE_OFF);
					Offer offer = new Offer(bread, Constants.BREAD_PERCENTAGE_OFF, breadDiscount);
					offers.add(offer);
				}
			}
		}
		return offers;
	}

    public BasketItem verifyBreadItem(ArrayList<BasketItem> items){
      for(BasketItem item : items){
          if(item.getItemName().equals(Constants.BREAD)){
              return item;
          }
      }
        return null;
    }

	@Override
	public BigDecimal calculateDiscount(BasketItem item, BigDecimal discount) {
		return item.getItemCost().multiply(new BigDecimal(item.getQuantity())).multiply(discount).setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public BigDecimal calculateSubTotal(ArrayList<BasketItem> items) {
		BigDecimal subTotal = new BigDecimal(0.0);
		for(BasketItem item : items) {
			subTotal = subTotal.add(item.getItemCost().multiply(new BigDecimal(item.getQuantity())));
		}
		return subTotal.setScale(2, RoundingMode.HALF_EVEN);
	}

	@Override
	public BigDecimal calculateTotal(BigDecimal subTotal, BigDecimal totalDiscount) {
		BigDecimal total  = subTotal.subtract(totalDiscount);
		return total.setScale(2, RoundingMode.HALF_EVEN);
	}
	

	@Override
	public BigDecimal calculateTotalDiscount(ArrayList<Offer> offers) {
		BigDecimal totalDiscount = new BigDecimal(0.0);
		for(Offer offer : offers) {
			totalDiscount = totalDiscount.add(offer.getDiscount());
		}
		return totalDiscount.setScale(2, RoundingMode.HALF_EVEN);
	}


	@Override
	public ShoppingBasket generateBasketItems(String[] args, ShoppingBasket shoppingBasket) {
		List<String> priceBasket = Arrays.asList(args); 
		int soupQuantity = 0;
		int applesQuantity = 0;
		int milkQuantity = 0;
		int breadQuantity = 0;
		
		for(String item : priceBasket) {
			if(item.equals(Constants.APPLES)) {
				applesQuantity++;
			}
			if(item.equals(Constants.MILK)) {
				milkQuantity++;
            }
			if(item.equals(Constants.BREAD)) {
				breadQuantity++;
			}
			if(item.equals(Constants.SOUP)) {
				soupQuantity++;
			}
		}
		
		
		if(applesQuantity>0) {
			BasketItem apples_ = new BasketItem();
			apples_.setQuantity(applesQuantity);
			apples_.setItemCost(Constants.APPLES_PRICE);
			apples_.setItemName(Constants.APPLES);
			shoppingBasket.getItems().add(apples_);		}
		
		
		if(milkQuantity>0) {
			BasketItem milk_ = new BasketItem();
			milk_.setQuantity(milkQuantity);
			milk_.setItemCost(Constants.MILK_PRICE);
			milk_.setItemName(Constants.MILK);
			shoppingBasket.getItems().add(milk_);		}
		
		if(breadQuantity >0) {
			BasketItem bread_ = new BasketItem();
			bread_.setQuantity(breadQuantity);
			bread_.setItemCost(Constants.BREAD_PRICE);
			bread_.setItemName(Constants.BREAD);
			shoppingBasket.getItems().add(bread_);		}
		
		if(soupQuantity > 0) {
			BasketItem soup_ = new BasketItem();
			soup_.setQuantity(soupQuantity);
			soup_.setItemCost(Constants.SOUP_PRICE);
			soup_.setItemName(Constants.SOUP);
			shoppingBasket.getItems().add(soup_);
		}
		return shoppingBasket;

	}


}
