package br.com.brastore.system.data;

import br.com.brastore.system.data.interfaces.DataInterface;
import br.com.brastore.system.model.Product;
import br.com.brastore.system.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class ProductData implements DataInterface {

    public static List<Stock> productList = new ArrayList<>();

    @Override
    public void save(Object object) {

        Stock stock = (Stock) object;
        productList.add(stock);

    }

    public void deleteBySku(String sku) {
        for (Stock stock : productList) {
            String product = stock.getProduct().getSku();
            if (product.equals(sku)) {
                productList.remove(stock);
            }
        }
    }

    @Override
    public List<Stock> findAll() {
        return productList;
    }

    public Integer getQuantity(String sku) {

        for (Stock stock : productList) {
            if (stock.getProduct().getSku().equals(sku)) {
                return stock.getQuantity();
            }
        }
        return null;

    }

    public Product findBySku(String sku) {
        for (Stock stock : productList) {
            if (stock.getProduct().getSku().equals(sku)) {
                return stock.getProduct();
            }
        }
        return null;
    }

    public void setQuantity(Object object) {
        for (Stock product : productList) {
            Stock stock = (Stock) object;
            String sku = stock.getProduct().getSku();
            Integer updatedQuantity = stock.getQuantity();
            Integer quantity = product.getQuantity();
            if (product.getProduct().getSku().equals(sku)) {
                product.setQuantity(updatedQuantity);
            }
        }
    }
}

