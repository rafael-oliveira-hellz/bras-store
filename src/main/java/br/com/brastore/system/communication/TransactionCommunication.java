package br.com.brastore.system.communication;

import br.com.brastore.system.controller.ProductController;
import br.com.brastore.system.controller.TransactionController;
import br.com.brastore.system.enums.PaymentMethodEnum;
import br.com.brastore.system.exception.InsufficientStockException;
import br.com.brastore.system.exception.ProductNotFoundException;
import br.com.brastore.system.model.Stock;
import br.com.brastore.system.utils.RunTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TransactionCommunication {

    // Inicializa o Scanner
    static Scanner input = new Scanner(System.in);

    // Inicializa o ProductController
    static ProductController productController = new ProductController();

    // Inicializa o TransactionController
    static TransactionController transactionController = new TransactionController();

    // Cria o método de venda
    public static void sale() throws ProductNotFoundException, InsufficientStockException {
        // Cria a lista de produtos em estoque
        List<Stock> cart = new ArrayList<>();
        String option = "s";

        // Enquanto a opção for diferente de "n" o programa continua
        while (option.equalsIgnoreCase("S")) {
            System.out.print("Digite o SKU do produto: ");
            String sku = input.nextLine(); // Recebe o SKU do produto
            int quantity = 0;
            boolean validate = false;

            while (!validate) {
                try {
                    System.out.print("Digite a quantidade que deseja comprar: ");
                    // Recebe a quantidade do produto
                    quantity = Integer.parseInt(input.nextLine());
                    validate = true;
                } catch (Exception e) {
                    System.out.println("Quantidade inválida!");
                }

                try {
                    // Valida se o produto tem quantidade suficiente em estoque e adiciona o produto no carrinho
                    Stock stock = productController.validatePrePurchase(sku, quantity);
                    if (stock != null) {
                        cart.add(stock);
                        validate = true;
                    } else {
                        System.out.println("Quantidade insuficiente ou produto não encontrado!");
                    }
                } catch (Exception e) {
                    System.out.println("Quantidade insuficiente ou produto não encontrado!");
                }
            }

            System.out.print("Deseja continuar? S/N ");
            option = input.nextLine();
        }

        double totalPrice = 0.0;

        for (Stock stock : cart) {
            System.out.println(stock.toString());
            totalPrice += stock.getProduct().getSellPrice() * stock.getQuantity();
        }

        if (totalPrice == 0.0) {
            return;
        }

        System.out.println("Preço total: " + totalPrice);

        System.out.print("\nDigite o nome do cliente: ");
        String name = input.nextLine();

        boolean validate = false;
        String cpf = "";

        while (!validate) {
            System.out.print("\nDeseja inserir o CPF? S/N ");
            char choice = input.nextLine().charAt(0); // Recebe a opção do usuário pegando apenas o primeiro caractere

            if (choice == 's' || choice == 'S') {
                while (!validate) {
                    System.out.print("Digite o CPF: ");
                    cpf = input.nextLine(); // Recebe o CPF do cliente
                    if (cpf.length() == 11) { // Valida se o CPF tem 11 caracteres
                        validate = true;
                    } else {
                        System.err.println("O CPF deve possuir 11 dígitos!");
                        RunTime.ThreadDelay(); // Dá um delay antes d retornar para redigitação do CPF
                    }
                }
            } else if (choice == 'n' || choice == 'N') {
                validate = true;
            } else {
                System.out.println("Opção inválida!");

            }
        }

        // Cria a lista de métodos de pagamento
        for (PaymentMethodEnum payment : PaymentMethodEnum.values()) {
            System.out.println(payment.getPaymentMethodId() + "  " + payment.getPaymentMethod());
        }

        Integer payment = 0;
        String paymentData = null;

        validate = false;

        while (!validate) {
            try {
                System.out.print("Escolha um método de pagamento pelo dígito: ");
                payment = Integer.parseInt(input.nextLine()); // Recebe o método de pagamento

                paymentData = checkPayment(payment); // Valida o método de pagamento
                if (paymentData == null || paymentData.equals("")) {
                    paymentData = Objects.requireNonNull(PaymentMethodEnum.getByPaymentMethodId(payment)).getPaymentMethod();
                }
                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        }

        if (cpf.equals("")) {
            System.out.println(transactionController.purchase(cart, name, payment, paymentData)); // Realiza a compra sem o CPF
        } else {
            System.out.println(transactionController.purchaseWithCpf(cart, name, cpf, payment, paymentData)); // Realiza a compra com o CPF
        }
    }

    public static void listTransactions() {
        System.out.println(transactionController.listAll());
    } // Lista todas as transações

    // Cria o método de validação do método de pagamento
    public static String checkPayment(Integer option) {
        String data = null;
        String temp;
        int cardNumber;
        boolean validator = false;

        switch (option) {
            case 3:
                while (!validator) {
                    try {
                        System.out.print("Digite o numero do cartão");
                        data = "Crédito\n" + "Número do cartão: ";
                        temp = input.nextLine();

                        // Valida se o número do cartão tem 12 caracteres
                        if (temp.length() == 12 && temp.matches("[0-9]+")) {
                            data += temp + "\nCód. de Segurança: ";

                            System.out.print("Digite o código de segurança (CVV): ");
                            temp = input.nextLine();

                            // Valida se o código de segurança tem 3 caracteres
                            if (temp.length() != 3) {
                                System.err.println("Código de segurança inválido! O CVV deve possuir 3 dígitos!");
                            } else {
                                cardNumber = Integer.parseInt(temp);
                                data += cardNumber + "\nVencimento: ";

                                System.out.print("Digite a data de validade: MM/AA");
                                temp = input.nextLine();

                                // Valida se a data de validade tem 5 caracteres
                                if (temp.charAt(2) == '/' && temp.length() == 5) {
                                    String[] date = temp.split("/");
                                    int month = Integer.parseInt(date[0]);
                                    int year = Integer.parseInt(date[1]);

                                    if (month > 0 && month < 13 && year > 21) {
                                        data += temp;
                                        validator = true;
                                        return data;
                                    }
                                }
                            }
                        } else {
                            System.err.println("Número do cartão inválido! Precisa ter 12 digitos");
                        }
                    } catch (Exception e) {
                        System.err.println("Entrada inválida!");
                    }
                }
                break;
            case 4:
                while (!validator) {
                    try {
                        System.out.print("Digite o numero do cartão");
                        data = "Débito\n" + "Número do cartão: ";
                        temp = input.nextLine();

                        if (temp.length() == 12 && temp.matches("[0-9]+")) {
                            data += temp + "\nCód. de Segurança: ";

                            System.out.print("Digite o código de segurança: ");
                            temp = input.nextLine();

                            if (temp.length() != 3) {
                                System.err.println("Código de segurança inválido! O CVV deve possuir 3 dígitos!");
                            } else {
                                cardNumber = Integer.parseInt(temp);
                                data += cardNumber + "\nVencimento: ";

                                System.out.print("Digite a data de validade: MM/AA");
                                temp = input.nextLine();

                                if (temp.charAt(2) == '/' && temp.length() == 5) {
                                    String[] date = temp.split("/");
                                    int month = Integer.parseInt(date[0]);
                                    int year = Integer.parseInt(date[1]);

                                    if (month > 0 && month < 13 && year > 21) {
                                        data += temp;
                                        validator = true;
                                        return data;
                                    }
                                }
                            }
                        } else {
                            System.err.println("Número do cartão inválido!");
                        }
                    } catch (Exception e) {
                        System.err.println("Entrada inválida!");
                    }
                }
                break;
            case 5:

                while (!validator) {
                    System.out.print("Digite sua chave pix: ");
                    temp = input.nextLine();

                    if (!Objects.equals(temp, "")) {
                        data = "Pix\nChave: " + temp;
                        validator = true;
                        return data;
                    } else {
                        System.err.println("Chave Inválida");
                    }
                }
                break;
            case default:
                return data;
        }
        return data;
    }
}
