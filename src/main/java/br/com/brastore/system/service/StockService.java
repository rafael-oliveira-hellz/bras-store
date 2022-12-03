package br.com.brastore.system.service;

import br.com.brastore.system.data.ProductData;
import br.com.brastore.system.exception.InsufficientStockException;
import br.com.brastore.system.exception.InvalidSaleValueException;
import br.com.brastore.system.exception.ProductNotFoundException;
import br.com.brastore.system.model.Product;
import br.com.brastore.system.model.Stock;
import br.com.brastore.system.model.Transaction;

import java.util.HashMap;
import java.util.List;

// Service serve para fazer a lógica de negócio e chamar os métodos do Data para salvar e buscar os dados
public class StockService {
    private final ProductData productData;

    public StockService() {
        this.productData = new ProductData();
    }

    public String createProductStock(Product product, Integer quantity) throws InvalidSaleValueException {
        validateProfit(product);
        return setProduct(product, quantity);
    }

    public List<Stock> listAll() {
        return productData.findAll();
    }

    public boolean validatePurchase(Transaction transaction)
            throws InsufficientStockException, ProductNotFoundException {
        return deductFromStock(transaction);
    }

    public String highStockWarning(Product product) {
        Integer quantity = productData.getQuantity(product.getSku());
        if (quantity >= 100) {
            return "Estoque alto: " + quantity + " unidades";
        } else {
            return null;
        }
    }

    public void deleteBySku(String sku) throws ProductNotFoundException {
        verifyIfExists(sku);
        productData.deleteBySku(sku);
    }

    public Product verifyIfExists(String sku) throws ProductNotFoundException {
        if (productData.findBySku(sku).equals(null)) {
            throw new ProductNotFoundException(sku);
        } else {
            return productData.findBySku(sku);
        }
    }

    public boolean deductFromStock(Transaction transaction)
            throws ProductNotFoundException, InsufficientStockException {
        checkStock(transaction.getProducts());
        return true;
    }

    public void reStock(String sku, Integer quantity) {
        Product product = productData.findBySku(sku);
        Stock stock = new Stock(product, productData.getQuantity(sku) + quantity);
        productData.setQuantity(stock);
    }

    public void checkStock(HashMap<Product, Integer> products) throws InsufficientStockException {
        products.forEach((requestedProduct, requestedQuantity) -> {
            String sku = requestedProduct.getSku();
            Product product = productData.findBySku(sku);
            Stock stock = new Stock(product, productData.getQuantity(sku) - requestedQuantity);
            productData.setQuantity(stock);
        });

    }

    public Product validateIndividualPurchase(String sku, Integer quantity) throws ProductNotFoundException {
        List<Stock> products = productData.findAll();
        Product product = verifyIfExists(sku);
        for (Stock stock : products) {
            if (stock.getProduct().equals(product) && stock.getQuantity() > quantity) {
                return product;
            }
        } return null;
    }

    public void validateProfit(Product product) throws InvalidSaleValueException {
        if ((product.getBuyPrice() * 1.25) > product.getSellPrice()) {
            throw new InvalidSaleValueException();
        }
    }

    private String setProduct(Product product, Integer quantity) {
        Stock stock = new Stock(product, quantity);
        productData.save(stock);
        return quantity + " unidades adicionadas com sucesso!";
    }

    public Product findBySku(String sku) throws ProductNotFoundException {
        return verifyIfExists(sku);
    }
}
