package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Constants {
	
	public static final BigDecimal APPLES_PERCENTAGE_OFF = new BigDecimal(0.10).setScale(2, RoundingMode.HALF_EVEN);
	public static final BigDecimal BREAD_PERCENTAGE_OFF = new BigDecimal(0.50).setScale(2, RoundingMode.HALF_EVEN);
	
	public static final BigDecimal APPLES_PRICE = new BigDecimal(1.00).setScale(2, RoundingMode.HALF_EVEN);
	public static final BigDecimal BREAD_PRICE = new BigDecimal(0.80).setScale(2, RoundingMode.HALF_EVEN);
	public static final BigDecimal MILK_PRICE = new BigDecimal(1.30).setScale(2, RoundingMode.HALF_EVEN);
	public static final BigDecimal SOUP_PRICE = new BigDecimal(0.65).setScale(2, RoundingMode.HALF_EVEN);
	
	public static final String APPLES = "Apples";
	public static final String BREAD = "Bread";
	public static final String MILK = "Milk";
	public static final String SOUP = "Soup";

}
