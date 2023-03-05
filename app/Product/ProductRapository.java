package app.Product;

import app.Product.subproduct.Drink;
import app.Product.subproduct.Hamburger;
import app.Product.subproduct.Side;

public class ProductRapository {
    private final Product[] products = new Product[]{
            new Hamburger("새우버거", 500, 3500, 1, false, 4500),
            new Hamburger("치킨버거", 600, 4000, 2, false, 5000 ),
            new Side("감자튀김", 300, 1000, 3, 1),
            new Side("어니언링", 300, 1000, 4, 1),
            new Drink("코카콜라", 200, 1000, 5, true),
            new Drink("제로콜라", 0, 1000, 6, true)
    };

    public Product[] getProducts() { // Menu에서 접근 할 수 있도록 게터를 만들어줌
        return products;
    }
    public Product findById(int productId){
        for(Product product : products){
            if(product.getId() == productId) return product;
        }
        return null;
    }
}
