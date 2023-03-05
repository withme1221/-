package app.Product.subproduct;

import app.Product.Product;

public class BurgerSet extends Product {
    private Hamburger hamburger;
    private Side side;
    private Drink drink;

  public BurgerSet(String name, int kcal, int price, Hamburger hamburger, Side side, Drink drink) {
    super(name, kcal, price);
    this.hamburger = hamburger;
    this.side = side;
    this.drink = drink;
  }

  public Hamburger getHamburger() {
    return hamburger;
  }

  public Side getSide() {
    return side;
  }

  public Drink getDrink() {
    return drink;
  }
}
