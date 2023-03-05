package app;

import app.Product.Product;
import app.Product.subproduct.Drink;
import app.Product.subproduct.Hamburger;
import app.Product.subproduct.Side;

public class Menu {
    private Product[] products; // 전체 상품에 대한 정보를 가지고 있어야함

    public Menu(Product[] products) {
        this.products = products;
    }

    public void printMenu() {
        System.out.println("[🔻] 메뉴");
        System.out.println("-".repeat(50));

        printHamburgers(true);
        printSide(true);
        printDrink(true);

        System.out.println();
        System.out.println("🛒 (0) 장바구니");
        System.out.println("💰 (+) 주문하기");
        System.out.println("-".repeat(50));
        System.out.println("[🎁] 메뉴를 선택해 주세요 : ");
    }

    public void printDrink(boolean printPrice) {
        System.out.println("🥤 음료");
        //음료 출력
        for (Product product : products) {
            if (product instanceof Drink) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
        System.out.println();
    }

    protected void printSide(boolean printPrice) {
        System.out.println("🍟 사이드");
        //사이드 출력
        for (Product product : products) {
            if (product instanceof Side) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    protected void printHamburgers(boolean printPrice) {
        System.out.println("🍔 햄버거");
        //햄버거 출력
        for (Product product : products) {
            if (product instanceof Hamburger) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(Product product, boolean printPrice) {
        if(printPrice == true){
            System.out.printf("(%d) %s %5dKcal %d원 \n", product.getId(), product.getName(), product.getKcal(), product.getPrice());
        } else System.out.printf("(%d) %s %5dKcal \n", product.getId(), product.getName(), product.getKcal());

    }
}
