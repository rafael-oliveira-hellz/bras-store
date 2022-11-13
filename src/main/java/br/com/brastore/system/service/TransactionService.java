package br.com.brastore.system.service;

import br.com.brastore.system.data.TransactionData;
import br.com.brastore.system.exception.InsufficientStockException;
import br.com.brastore.system.exception.ProductNotFoundException;
import br.com.brastore.system.model.Transaction;

import java.util.List;

public class TransactionService {
    private final TransactionData transactionData;
    private final StockService stockService;

    public TransactionService() {
        this.transactionData = new TransactionData();
        this.stockService = new StockService();
    }

    public String createTransaction(Transaction transaction)
            throws InsufficientStockException, ProductNotFoundException {
        if (stockService.validatePurchase(transaction)) {
            Transaction savedTransaction = setTransaction(transaction);
            return createMessageResponse(savedTransaction.getId(), " criada.");
        }

        return "Transação não criada!";
    }

    public List<Transaction> listAll() {
        return transactionData.findAll();
    }

    private String createMessageResponse(Integer id, String message) {
        return "Transação com a ID: " + id + message;
    }

    private Transaction setTransaction(Transaction transaction) {
        transactionData.save(transaction);
        return transaction;
    }
}
