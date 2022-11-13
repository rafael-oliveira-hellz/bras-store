package br.com.brastore.system.exception;

public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String sku) {
        super("Nenhum produto encontrado com o SKU: " + sku);
    }
}

