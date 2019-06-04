package model;

import java.math.BigDecimal;

public class BasketItem implements Cloneable
{
	private  String itemName;
	private  BigDecimal itemCost;
	private  int quantity;
	
	
	public BigDecimal getItemCost() {
		return itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int soupQuantity) {
		this.quantity = soupQuantity;
	}

	public BasketItem()
	{
	}

	public BasketItem(String itemName, BigDecimal itemCost,
		int quantity)
	{
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.quantity = quantity;
	}
	

}

