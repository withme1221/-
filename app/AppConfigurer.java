package app;

import app.Product.Product;
import app.Product.ProductRapository;
import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;

public class AppConfigurer {

    private Cart cart = new Cart(productRepository(), menu());

    public ProductRapository productRepository() {
        return new ProductRapository();
    }

    public Menu menu() {
        return new Menu(productRepository().getProducts());
    }

    public Cart cart() {
        return cart;
    }

    public Discount discount() {
        return new Discount(
                new DiscountCondition[]{
                        new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                        new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
                }
        );
    }

    public Order order() {
        return new Order(cart(), discount());
    }
}
