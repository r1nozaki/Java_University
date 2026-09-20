package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> catalog = new ArrayList<>();
        catalog.add(product1);
        catalog.add(product2);
        catalog.add(product3);

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int addId = scanner.nextInt();
                    if (addId == 1) cart.addProduct(product1);
                    else if (addId == 2) cart.addProduct(product2);
                    else if (addId == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;

                case 3:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int removeId = scanner.nextInt();
                    if (removeId == 1) cart.removeProduct(product1);
                    else if (removeId == 2) cart.removeProduct(product2);
                    else if (removeId == 3) cart.removeProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;

                case 4:
                    System.out.println(cart);
                    break;

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("=== Історія замовлень ===");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("Замовлення #" + (i + 1) + ":");
                            System.out.println(orderHistory.get(i));
                            System.out.println("--------------------");
                        }
                    }
                    break;

                case 7:
                    System.out.println("Введіть назву або категорію для пошуку:");
                    String query = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    for (Product p : catalog) {
                        String productName = p.getName().toLowerCase();
                        String categoryName = p.getCategory().getName().toLowerCase();
                        if (productName.contains(query) || categoryName.contains(query)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Товарів за вашим запитом не знайдено.");
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}
