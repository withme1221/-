package app.discount;

import app.discount.discountCondition.DiscountCondition;

import java.io.IOException;

public class Discount {
    private DiscountCondition[] discountConditions;

    public Discount(DiscountCondition[] discountConditions) {
        this.discountConditions = discountConditions;
    }
    public int discout(int price) throws IOException {
        int discountedprice = price;
        for (DiscountCondition discountCondition : discountConditions) {
            discountCondition.checkDiscountCondition();
            if(discountCondition.isSatisfied()) discountedprice = discountCondition.applyDiscount(discountedprice);
        }
        return discountedprice;
    }
}
