package br.com.brastore.system.controller;

import br.com.brastore.system.exception.InvalidSaleValueException;
import br.com.brastore.system.exception.ProductNotFoundException;
import br.com.brastore.system.model.Product;
import br.com.brastore.system.model.Stock;
import br.com.brastore.system.service.StockService;

import java.util.List;

public class ProductController {
    private final StockService stock;

    public ProductController() {
        this.stock = new StockService();
    }

    public Stock validatePrePurchase(String sku, int quantity) throws ProductNotFoundException {
        Product product = stock.validateIndividualPurchase(sku, quantity);
        return new Stock(product, quantity);

    }

    public String insertProduct(String sku, String description, int quantity, double buyPrice, double sellPrice)
            throws InvalidSaleValueException {
        if (sku.length() != 16) {
            return " SKU inválida ";
        }
        if (description == null || quantity <= 0 || buyPrice <= 0.0 || sellPrice <= 0.0) {
            return " Digite os valores corretos de acordo com cada campo ";
        }
        Product product = new Product(sku, description, buyPrice, sellPrice);
        return stock.createProductStock(product, quantity);
    }

    public String findSku(String sku) throws ProductNotFoundException {
        return stock.verifyIfExists(sku).toString();
    }

    public String listAll() {
        List<Stock> stocks = stock.listAll();
        String message = "";
        for (Stock stock : stocks) {
            message += stock.getProduct().toString() + ". Quantidade: " + stock.getQuantity() + " ]\n";
        }
        return message;
    }

    public String delete(String sku) throws ProductNotFoundException {
        stock.deleteBySku(sku);
        return "Produto excluído com sucesso";
    }

    public String update(String sku, Integer quantity) throws ProductNotFoundException {
        Product product = stock.verifyIfExists(sku);
        if (quantity <= 0) {
            return "Digite uma quantidade maior que 0";
        }

        if (product == null) {
            return " Produto não encontrado!";
        } else {
            stock.reStock(sku, quantity);
            return " Produto atualizado com sucesso ";
        }
    }

    public String update(String sku, Double buyPrice, Double sellPrice)
            throws ProductNotFoundException, InvalidSaleValueException {
        Product product = stock.verifyIfExists(sku);
        if (product == null) {
            return " Produto não encontrado!";
        } else {
            product.setBuyPrice(buyPrice);
            product.setSellPrice(sellPrice);
            stock.validateProfit(product);
            return "Produto atualizado com sucesso ";
        }
    }
}
