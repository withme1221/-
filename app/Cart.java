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
        System.out.println("๐ ์ฅ๋ฐ๊ตฌ๋");
        System.out.println("-".repeat(50));
        //์ฅ๋ฐ๊ตฌ๋์ ๋ด๊ธด ์ํ ์ถ๋ ฅ
        printCartItemDetails();
        System.out.println("-".repeat(50));
        System.out.printf("ํฉ๊ณ : %d \n", calculateTotalPrice());

        System.out.println("์ด์ ์ผ๋ก ๋์๊ฐ๋ ค๋ฉด ์ํฐ๋ฅผ ๋๋ฅด์ธ์.");
        br.readLine();

    }

    protected void printCartItemDetails() {
        for (Product product : items) {
            if (product instanceof BurgerSet) {
                BurgerSet burgerSet = (BurgerSet) product;
                System.out.printf("%s %7d์ (%s(์ผ์ฒฉ %d๊ฐ), %s(๋นจ๋ %s))\n"
                        , product.getName(), product.getPrice(), burgerSet.getSide().getName(), burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(), burgerSet.getDrink().hasStraw() ? "์์" : "์์");
            } else if (product instanceof Hamburger) {
                System.out.printf("%-8s %6d์ (๋จํ)\n", product.getName(), product.getPrice());
            } else if (product instanceof Side) {
                System.out.printf("%-8s %6d์ (์ผ์ฒฉ %d๊ฐ)\n", product.getName(), product.getPrice(), ((Side) product).getKetchup());
            } else if (product instanceof Drink) {
                System.out.printf("%-8s %6d์ (๋นจ๋ %s)\n", product.getName(), product.getPrice(), ((Drink) product).hasStraw() ? "์์" : "์์");
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

        System.out.printf("[๐] %s๋ฅผ(์) ์ฅ๋ฐ๊ตฌ๋์ ๋ด์์ต๋๋ค \n", product.getName());
    }

    private void chooseOption(Product product) throws IOException {
        String input;
        if (product instanceof Hamburger) {
            System.out.printf("๋จํ์ผ๋ก ์ฃผ๋ฌธํ์๊ฒ ์ด์? (1)_๋จํ(%d์) (2)_์ธํธ(%d์) \n", product.getPrice(), ((Hamburger) product).getBurgerSetPrice());
            input = br.readLine();
            if (input.equals("1")) ((Hamburger) product).setBurgerSet(false);
            else if (input.equals("2")) ((Hamburger) product).setBurgerSet(true);
        } else if (product instanceof Side) {
            System.out.println("์ผ์ฒฉ์ ๋ช๊ฐ๊ฐ ํ์ํ์ ๊ฐ์?");
            input = br.readLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        } else if (product instanceof Drink) {
            System.out.println("๋นจ๋๊ฐ ํ์ํ์ ๊ฐ์? (1)_์ (2)_์๋์ค");
            input = br.readLine();
            if (input.equals("1")) ((Drink) product).setHasStraw(true);
            else if (input.equals("2")) ((Drink) product).setHasStraw(false);
        }
    }

    private BurgerSet composeSet(Hamburger hamburger) throws IOException {

        System.out.println("์ฌ์ด๋๋ฅผ ๊ณจ๋ผ์ฃผ์ธ์");
        menu.printSide(false);

        String sideId = br.readLine();
        Side side = (Side) productRapository.findById(Integer.parseInt(sideId));
        Side newSide = new Side(side);
        chooseOption(newSide);

        System.out.println("์๋ฃ๋ฅผ ๊ณจ๋ผ์ฃผ์ธ์");
        menu.printDrink(false);

        String drinkId = br.readLine();
        Drink drink = (Drink) productRapository.findById(Integer.parseInt(drinkId));
        Drink newDrink = new Drink(drink);
        chooseOption(newDrink);

        String name = hamburger.getName() + "์ธํธ";
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerSet(name, kcal, price, hamburger, newSide, newDrink);
    }

}
