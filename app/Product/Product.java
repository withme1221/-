package app.Product;

public class Product {
    // 햄버거, 사이드, 음료 모두가 공통으로 가지고있는 정보
    private String name;
    private int kcal;
    private int price;
    private int id;

    public Product(String name, int kcal, int price, int id) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
        this.id = id;
    }

    public Product(String name, int kcal, int price) {
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
