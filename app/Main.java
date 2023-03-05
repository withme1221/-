package app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //상품들의 공통점을 담아둘 추상클래스 Product를 만든다.
        AppConfigurer appConfigurer = new AppConfigurer();
        OrderApp orderApp = new OrderApp(appConfigurer.productRepository(),
                                        appConfigurer.menu(),
                                        appConfigurer.cart(),
                                        appConfigurer.order());
        orderApp.start();

    }

}