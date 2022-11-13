package br.com.brastore.system.model;

public class Stock {
    private final Product product;
    private Integer quantity;

    public Stock(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Stock stock = (Stock) obj;
        return product.equals(stock.product);
    }

    @Override
    public String toString() {
        return "Estoque: [Produto: " + product.toString() + ", quantidade: " + quantity + "]";
    }
}
