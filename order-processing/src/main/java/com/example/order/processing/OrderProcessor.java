package com.example.order.processing;

public class OrderProcessor<T extends Product> {

    public void process(Order<T> order) {
        if (order == null) {
            throw new InvalidOrderException("Замовлення не може бути null");
        }

        try {
            Thread.sleep(250);
            System.out.printf(
                    "%s обробив замовлення №%d: %s%n",
                    Thread.currentThread().getName(),
                    order.getId(),
                    order.getProduct().getName()
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new InvalidOrderException("Обробку замовлення перервано");
        }
    }
}
