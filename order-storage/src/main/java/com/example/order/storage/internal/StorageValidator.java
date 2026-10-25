package com.example.order.storage.internal;

import com.example.order.processing.Order;
import com.example.order.processing.Product;

public final class StorageValidator {

    private StorageValidator() {
    }

    public static void validate(Order<? extends Product> order) {
        if (order == null) {
            throw new IllegalArgumentException("Неможливо зберегти null-замовлення");
        }
    }
}
