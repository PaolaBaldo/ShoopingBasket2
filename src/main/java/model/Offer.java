package model;

import java.math.BigDecimal;


public class Offer{

	private BasketItem affectedItem;
	private BigDecimal percentageOff;
	private BigDecimal discount;
	

	public Offer(BasketItem affectedItem, BigDecimal percentageOff, BigDecimal discount) {
		super();
		this.affectedItem = affectedItem;
		this.percentageOff = percentageOff;
		this.discount = discount;
	}
	
	
	public BasketItem getAffectedItem() {
		return affectedItem;
	}
	public void setAffectedItem(BasketItem affectedItem) {
		this.affectedItem = affectedItem;
	}
	public BigDecimal getPercentageOff() {
		return percentageOff;
	}
	public void setPercentageOff(BigDecimal percentageOff) {
		this.percentageOff = percentageOff;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		return "Offer [affectedItem=" + affectedItem + ", percentageOff=" + percentageOff + ", discount=" + discount
				+ "]";
	}

}
