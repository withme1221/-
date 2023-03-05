package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;
;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KidDiscountCondition implements DiscountCondition {
    private boolean isSatisfied;
    private DiscountPolicy discountPolicy;

    public KidDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public boolean isSatisfied() {
        return isSatisfied;

    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }
    public void checkDiscountCondition() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("나이가 어떻게 되십니까 ?");
        int input = Integer.parseInt(br.readLine());
        setSatisfied(input < 20);
    }
    public int applyDiscount(int price){
        return discountPolicy.calculateDiscountedPrice(price);
    }
}