package app.discount.discountCondition;

import java.io.IOException;

public interface DiscountCondition {
    void checkDiscountCondition() throws IOException;
    int applyDiscount(int price);
    boolean isSatisfied();
}
