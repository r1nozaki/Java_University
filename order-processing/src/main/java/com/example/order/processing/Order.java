package com.example.order.processing;

public final class Order<T extends Product> {
    private final int id;
    private final T product;

    public Order(int id, T product) {
        if (id <= 0) {
            throw new InvalidOrderException("ID замовлення має бути додатним");
        }
        if (product == null) {
            throw new InvalidOrderException("Товар не може бути null");
        }
        if (product.getName() == null || product.getName().isBlank()) {
            throw new InvalidOrderException("Назва товару не може бути порожньою");
        }
        if (!Double.isFinite(product.getPrice()) || product.getPrice() <= 0) {
            throw new InvalidOrderException("Ціна товару має бути додатною");
        }

        this.id = id;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public T getProduct() {
        return product;
    }
}
