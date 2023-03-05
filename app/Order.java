package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.DiscountPolicy;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;

import java.io.IOException;

public class Order {
    private Cart cart;

    private Discount discount;

    public Order(Cart cart, Discount discount) {
        this.cart = cart;
        this.discount = discount;
    }

    public void makeOrder() throws IOException {
//        CozDiscountCondition cozDiscountCondition = new CozDiscountCondition(new FixedRateDiscountPolicy(10));
//        KidDiscountCondition kidDiscountCondition = new KidDiscountCondition(new FixedAmountDiscountPolicy(500));
//
//        cozDiscountCondition.checkDiscountCondition();
//        kidDiscountCondition.checkDiscountCondition();
//
//        int totalPrice = cart.calculateTotalPrice();
//
//        int finalPrice = totalPrice;
//        if (cozDiscountCondition.isSatisfied()) finalPrice = cozDiscountCondition.applyDiscount(finalPrice);
//        if (kidDiscountCondition.isSatisfied()) finalPrice = kidDiscountCondition.applyDiscount(finalPrice);
        int totalPrice = cart.calculateTotalPrice();
        int finalPrice = discount.discout(totalPrice);

        System.out.println("[🎁] 주문이 완료되었습니다.");
        System.out.println("[🎁] 주문 내역은 다음과 같습니다.");
        System.out.println("-".repeat(50));
        cart.printCartItemDetails();
        System.out.println("-".repeat(50));
        System.out.printf("금액 합계     : %d원 \n", totalPrice);
        System.out.printf("할인 적용 금액 : %d원 \n", finalPrice);
        System.out.println("[🎁] 이용해주셔서 감사합니다.");
    }
}
