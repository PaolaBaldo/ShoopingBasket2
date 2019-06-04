import model.BasketItem;
import model.Offer;
import model.ShoppingBasket;
import service.ShoppingBasketService;
import service.ShoppingBasketServiceImpl;
import util.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;


public class Application {
	
	public static void main(String[] args) {
		if(args.length == 0)
	    {
	        //System.out.println("Correct usage example: PriceBasket Apples Milk Soup Soup");
	        //System.exit(0);
            args = new String[5];
            args[0]= Constants.APPLES;
            args[1]= Constants.MILK;
            args[2]= Constants.BREAD;
            args[3]= Constants.SOUP;
            args[4]= Constants.SOUP;
	    }
		
		ShoppingBasketService shoppingCartService = new ShoppingBasketServiceImpl();
		ShoppingBasket shoppingBasket = new ShoppingBasket();

		shoppingBasket = shoppingCartService.generateBasketItems(args, shoppingBasket);
		
		ArrayList<Offer> offers = shoppingCartService.applyOffers(shoppingBasket.getItems());
		
		BigDecimal subTotal = shoppingCartService.calculateSubTotal(shoppingBasket.getItems());
		BigDecimal totalDiscount = shoppingCartService.calculateTotalDiscount(offers);
		BigDecimal total = shoppingCartService.calculateTotal(subTotal, totalDiscount);

		System.out.println("Subtotal: " + "\u00a3"+subTotal);
		for(Offer offer : offers) {
			System.out.println(offer.getAffectedItem().getItemName() +" " +
					offer.getPercentageOff().multiply(new BigDecimal(100)).intValue()+"% off: " + 
					"-"+offer.getDiscount().multiply(new BigDecimal(100)).intValue()+"p");
		}
		
		System.out.println("Total: " + "\u00a3"+total);
	}
	

}
