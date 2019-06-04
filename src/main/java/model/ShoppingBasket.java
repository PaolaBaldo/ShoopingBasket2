package model;
import java.math.BigDecimal;
import java.util.*;


public class ShoppingBasket
{
	
	private ArrayList<BasketItem> items;
	private BigDecimal subTotal;
	private ArrayList<Offer> offers;
	private BigDecimal totalDiscount; 
	
	
	public ArrayList<BasketItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<BasketItem> items) {
		this.items = items;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public ArrayList<Offer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<Offer> offers) {
		this.offers = offers;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	

	public ShoppingBasket()
	{
		items = new ArrayList<BasketItem>();
		subTotal = new BigDecimal(0.0);
		offers = new ArrayList<Offer>();
	}



	

}