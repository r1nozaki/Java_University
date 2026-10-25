package com.example.order.threading;

import com.example.order.processing.Clothing;
import com.example.order.processing.Electronics;
import com.example.order.processing.InvalidOrderException;
import com.example.order.processing.Order;
import com.example.order.processing.OrderProcessor;
import com.example.order.processing.Product;
import com.example.order.storage.OrderStorage;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();
        OrderStorage storage = new OrderStorage();
        OrderProcessor<Product> processor = new OrderProcessor<>();
        OrderThreadManager manager = new OrderThreadManager();
        List<Order<Product>> orders = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            Product product;

            if (i % 2 == 0) {
                product = Electronics.builder()
                        .name(faker.commerce().productName())
                        .price(100 + i * 50)
                        .warrantyMonths(12)
                        .build();
            } else {
                product = Clothing.builder()
                        .name(faker.commerce().department())
                        .price(20 + i * 10)
                        .size("M")
                        .build();
            }

            orders.add(new Order<>(i, product));
        }

        orders.forEach(storage::save);
        System.out.println("Збережено замовлень: " + storage.findAll().size());

        System.out.println("\nЕлектроніка:");
        orders.stream()
                .filter(order -> order.getProduct() instanceof Electronics)
                .forEach(order -> System.out.println(order.getProduct().getName()));

        System.out.println("\nБагатопотокова обробка:");
        manager.processAll(orders, processor::process);

        try {
            Clothing invalid = Clothing.builder()
                    .name("Test")
                    .price(-100)
                    .size("L")
                    .build();
            new Order<>(99, invalid);
        } catch (InvalidOrderException e) {
            System.out.println("Перевірка виключень: " + e.getMessage());
        }

        System.out.println("Програму завершено!");
    }
}
