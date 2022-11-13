package br.com.brastore.system.controller;

import br.com.brastore.system.exception.InsufficientStockException;
import br.com.brastore.system.exception.ProductNotFoundException;
import br.com.brastore.system.model.Costumer;
import br.com.brastore.system.model.Product;
import br.com.brastore.system.model.Stock;
import br.com.brastore.system.model.Transaction;
import br.com.brastore.system.service.TransactionService;

import java.util.List;

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController() {
        this.transactionService = new TransactionService();
    }

    public String purchase(List<Stock> stocks, String name, Integer payment, String paymentData)
            throws InsufficientStockException, ProductNotFoundException {
        Costumer costumer = new Costumer(name, payment, paymentData);
        Transaction transaction = new Transaction(costumer);
        for (Stock stock : stocks) {
            Product product = stock.getProduct();
            Integer quantity = stock.getQuantity();
            transaction.addProducts(product, quantity);
        }

        String msg = transactionService.createTransaction(transaction);
        transaction.setTotalPrice();
        String totalPrice = String.format("R$%.2f", transaction.getTotalPrice());
        msg += "\nCompra realizada com sucesso!\nValor total: " + totalPrice;
        return msg;
    }

    public String purchaseWithCpf(List<Stock> stocks, String name, String cpf, Integer payment, String paymentData)
            throws InsufficientStockException, ProductNotFoundException {
        Costumer costumer = new Costumer(name, cpf, payment, paymentData);
        Transaction transaction = new Transaction(costumer);
        for (Stock stock : stocks) {
            Product product = stock.getProduct();
            Integer quantity = stock.getQuantity();
            transaction.addProducts(product, quantity);
        }
        String msg = transactionService.createTransaction(transaction);
        transaction.setTotalPrice();
        String totalPrice = String.format("R$%.2f", transaction.getTotalPrice());
        msg += "\nCompra realizada com sucesso!\nValor total: " + totalPrice;
        return msg;
    }

    public String listAll() {
        Double totalProfit = 0.0;
        StringBuilder totalList = new StringBuilder();
        List<Transaction> list = transactionService.listAll();
        for (Transaction transaction : list) {
            totalList.append(transaction.toString()).append("\n\n");
            totalProfit += transaction.getTotalPrice();
        }
        totalList.append(String.format("\nSoma do valor das vendas: R$ %.2f", totalProfit));
        return totalList.toString();
    }
}
