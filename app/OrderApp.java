package app;

import app.Product.Product;
import app.Product.ProductRapository;
import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderApp {
    private ProductRapository productRapository;
    private Menu menu;
    private Cart cart;
    private Order order;

    public OrderApp(ProductRapository productRapository, Menu menu, Cart cart, Order order) {
        this.productRapository = productRapository;
        this.menu = menu;
        this.cart = cart;
        this.order = order;
    }

    public void start() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("🍔 BurgerQueen Order Service");

        while (true) {
            menu.printMenu();
            String str = br.readLine();
            if (str.equals("+")) {
                order.makeOrder();
                break;
            } else if (str.equals("0")) {
                cart.printCart();
            } else if (1 <= Integer.parseInt(str) && Integer.parseInt(str) <= productRapository.getProducts().length) {
                cart.addToCart(Integer.parseInt(str));
            }
        }
    }


}
