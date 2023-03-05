package app;

import app.Product.Product;
import app.Product.ProductRapository;
import app.Product.subproduct.BurgerSet;
import app.Product.subproduct.Drink;
import app.Product.subproduct.Hamburger;
import app.Product.subproduct.Side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cart {
    ProductRapository productRapository = new ProductRapository();
    private Menu menu;


    public Cart(ProductRapository productRapository, Menu menu) {
        this.productRapository = productRapository;
        this.menu = menu;
    }

    private Product[] items = new Product[]{};
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void printCart() throws IOException {
        System.out.println("🛒 장바구니");
        System.out.println("-".repeat(50));
        //장바구니에 담긴 상품 출력
        printCartItemDetails();
        System.out.println("-".repeat(50));
        System.out.printf("합계 : %d \n", calculateTotalPrice());

        System.out.println("이전으로 돌아가려면 엔터를 누르세요.");
        br.readLine();

    }

    protected void printCartItemDetails() {
        for (Product product : items) {
            if (product instanceof BurgerSet) {
                BurgerSet burgerSet = (BurgerSet) product;
                System.out.printf("%s %7d원 (%s(케첩 %d개), %s(빨대 %s))\n"
                        , product.getName(), product.getPrice(), burgerSet.getSide().getName(), burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(), burgerSet.getDrink().hasStraw() ? "있음" : "없음");
            } else if (product instanceof Hamburger) {
                System.out.printf("%-8s %6d원 (단품)\n", product.getName(), product.getPrice());
            } else if (product instanceof Side) {
                System.out.printf("%-8s %6d원 (케첩 %d개)\n", product.getName(), product.getPrice(), ((Side) product).getKetchup());
            } else if (product instanceof Drink) {
                System.out.printf("%-8s %6d원 (빨대 %s)\n", product.getName(), product.getPrice(), ((Drink) product).hasStraw() ? "있음" : "없음");
            }
        }
    }

    protected int calculateTotalPrice() {
        int totalPrice = 0;
        for (Product product : items) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public void addToCart(int productId) throws IOException {
        Product product = productRapository.findById(productId);

        chooseOption(product);

        if (product instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) product;
            if (hamburger.isBurgerSet()) product = composeSet(hamburger);
        }

        Product newProduct;
        if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else if (product instanceof Drink) newProduct = new Drink((Drink) product);
        else newProduct = product;


        Product[] newItems = new Product[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[newItems.length - 1] = newProduct;
        items = newItems;

        System.out.printf("[🎁] %s를(을) 장바구니에 담았습니다 \n", product.getName());
    }

    private void chooseOption(Product product) throws IOException {
        String input;
        if (product instanceof Hamburger) {
            System.out.printf("단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원) \n", product.getPrice(), ((Hamburger) product).getBurgerSetPrice());
            input = br.readLine();
            if (input.equals("1")) ((Hamburger) product).setBurgerSet(false);
            else if (input.equals("2")) ((Hamburger) product).setBurgerSet(true);
        } else if (product instanceof Side) {
            System.out.println("케첩은 몇개가 필요하신가요?");
            input = br.readLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        } else if (product instanceof Drink) {
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
            input = br.readLine();
            if (input.equals("1")) ((Drink) product).setHasStraw(true);
            else if (input.equals("2")) ((Drink) product).setHasStraw(false);
        }
    }

    private BurgerSet composeSet(Hamburger hamburger) throws IOException {

        System.out.println("사이드를 골라주세요");
        menu.printSide(false);

        String sideId = br.readLine();
        Side side = (Side) productRapository.findById(Integer.parseInt(sideId));
        Side newSide = new Side(side);
        chooseOption(newSide);

        System.out.println("음료를 골라주세요");
        menu.printDrink(false);

        String drinkId = br.readLine();
        Drink drink = (Drink) productRapository.findById(Integer.parseInt(drinkId));
        Drink newDrink = new Drink(drink);
        chooseOption(newDrink);

        String name = hamburger.getName() + "세트";
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerSet(name, kcal, price, hamburger, newSide, newDrink);
    }

}
