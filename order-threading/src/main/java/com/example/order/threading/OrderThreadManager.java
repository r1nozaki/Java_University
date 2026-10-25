package com.example.order.threading;

import com.example.order.processing.Order;
import com.example.order.processing.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class OrderThreadManager {

    public <T extends Product> void processAll(
            List<Order<T>> orders,
            Consumer<Order<T>> handler
    ) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            List<Future<?>> futures = new ArrayList<>();

            for (Order<T> order : orders) {
                Future<?> future = executor.submit(
                        () -> handler.accept(order)
                );

                futures.add(future);
            }

            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("Очікування перервано", e);
                } catch (ExecutionException e) {
                    throw new IllegalStateException("Помилка обробки замовлення", e.getCause());
                }
            }
        } finally {
            executor.shutdown();

            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
