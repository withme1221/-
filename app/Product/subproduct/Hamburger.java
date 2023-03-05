package app.Product.subproduct;

import app.Product.Product;

public class Hamburger extends Product {
    //햄버거가 가지고 있는 정보 -> 버거세트유무 , 버거세트 가격
    private boolean isBurgerSet;
    private int burgerSetPrice;

    public Hamburger(String name, int kcal, int price, int id, boolean isBurgerSet, int burgerSetPrice) {
        super(name, kcal, price, id);
        this.isBurgerSet = isBurgerSet;
        this.burgerSetPrice = burgerSetPrice;
    }
    public Hamburger(Hamburger hamburger) {
        super(hamburger.getName(), hamburger.getKcal(), hamburger.getPrice());
        this.isBurgerSet = hamburger.isBurgerSet;
        this.burgerSetPrice = getBurgerSetPrice();
    }



    public boolean isBurgerSet() {
        return isBurgerSet;
    }

    public void setBurgerSet(boolean burgerSet) {
        isBurgerSet = burgerSet;
    }

    public int getBurgerSetPrice() {
        return burgerSetPrice;
    }

    public void setBurgerSetPrice(int burgerSetPrice) {
        this.burgerSetPrice = burgerSetPrice;
    }
}
