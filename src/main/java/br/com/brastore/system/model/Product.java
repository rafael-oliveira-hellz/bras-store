package br.com.brastore.system.model;

import br.com.brastore.system.enums.*;

public class Product {
    private String sku;
    private String brand;
    private String type;
    private String size;
    private String color;
    private String category;
    private String season;
    private String department;
    private Double buyPrice;
    private Double sellPrice;
    private String description;

    public Product() {
    }

    public Product(String sku, String description, double buyPrice, double sellPrice) {
        this.description = description;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        parseSku(sku);
        this.sku = sku;
    }

    private void parseSku(String sku) {
        this.brand = BrandEnum.get(sku.substring(0, 3)).getDescription();
        this.size = SizeEnum.get(sku.substring(3, 5)).getDescription();
        this.category = CategoryEnum.get(sku.substring(5, 7)).getDescription();
        this.season = SeasonEnum.get(sku.substring(7, 9)).getDescription();
        this.department = DepartmentEnum.get(sku.substring(9, 11)).getDescription();
        this.type = TypeOfProductEnum.get(sku.substring(11, 14)).getDescription();
        this.color = ColorEnum.get(sku.substring(14, 16)).getDescription();
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSku() {
        return sku;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Produto " + description + ", com Sku " + sku + " e pre�o de " + String.format("R$%.2f", sellPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return sku.equals(product.sku);
    }
}
