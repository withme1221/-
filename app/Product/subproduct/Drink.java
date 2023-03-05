package app.Product.subproduct;

import app.Product.Product;

public class Drink extends Product {
    private boolean hasStraw;

    public Drink(String name, int kcal, int price, int id, boolean hasStraw) {
        super(name, kcal, price, id);
        this.hasStraw = hasStraw;
    }
    public Drink(Drink drink) {
        super(drink.getName(), drink.getKcal(), drink.getPrice());
        this.hasStraw = drink.hasStraw();
    }

    public boolean hasStraw() {
        return hasStraw;
    }

    public void setHasStraw(boolean hasStraw) {
        this.hasStraw = hasStraw;
    }
}
