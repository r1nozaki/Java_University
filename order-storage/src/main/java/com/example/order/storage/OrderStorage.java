package com.example.order.storage;

import com.example.order.processing.Order;
import com.example.order.processing.Product;
import com.example.order.storage.internal.StorageValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderStorage {
    private final Map<Integer, Order<? extends Product>> orders =
            new ConcurrentHashMap<>();

    public <T extends Product> void save(Order<T> order) {
        StorageValidator.validate(order);

        if (orders.putIfAbsent(order.getId(), order) != null) {
            throw new IllegalArgumentException("Замовлення з таким ID вже існує");
        }
    }

    public List<Order<? extends Product>> findAll() {
        return new ArrayList<>(orders.values());
    }
}
