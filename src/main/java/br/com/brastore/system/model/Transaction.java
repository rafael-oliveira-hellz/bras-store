package br.com.brastore.system.model;

import java.util.HashMap;

public class Transaction {
    private static Integer superId = 0;
    private final Integer id;
    private final HashMap<Product, Integer> products;
    private final Costumer costumer;
    private Double totalPrice;

    public Transaction(Costumer costumer) {
        superId++;
        this.id = superId;
        this.costumer = costumer;
        this.totalPrice = 0.0;
        products = new HashMap<>();
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public Integer getId() {
        return id;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setTotalPrice() {
        products.forEach((key, value) -> {
            this.totalPrice += key.getSellPrice() * value;
        });

    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void addProducts(Product product, Integer quantityProduct) {
        this.products.put(product, quantityProduct);
    }

    @Override
    public String toString() {
        return "Transação #" + id + ": Cliente " + costumer.toString() + "\nValor da venda: "
                + String.format("R$%.2f", getTotalPrice());
    }
}
