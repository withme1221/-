package app.Product.subproduct;

import app.Product.Product;

public class Side extends Product {
   private int ketchup;

    public Side(String name, int kcal, int price, int id, int ketchup) {
        super(name, kcal, price, id);
        this.ketchup = ketchup;
    }
    public Side(Side side) {
        super(side.getName(), side.getKcal(), side.getPrice());
        this.ketchup = side.getKetchup();
    }
    public int getKetchup() {
        return ketchup;
    }

    public void setKetchup(int ketchup) {
        this.ketchup = ketchup;
    }
}
