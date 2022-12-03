package br.com.brastore.system.communication;

import br.com.brastore.system.controller.ProductController;
import br.com.brastore.system.enums.MenuEnum;
import br.com.brastore.system.exception.InsufficientStockException;
import br.com.brastore.system.exception.InvalidSaleValueException;
import br.com.brastore.system.exception.ProductNotFoundException;
import br.com.brastore.system.utils.RunTime;

import java.util.Scanner;

public class MainMenu {
    // Inicializa o SCANNER para leitura de dados
    static Scanner input = new Scanner(System.in);

    // Inicializa o controller do menu
    public static void main(String[] args) throws InvalidSaleValueException, ProductNotFoundException, InsufficientStockException {
        new MainMenu();

    }

    // Construtor do menu
    public MainMenu() throws InvalidSaleValueException, ProductNotFoundException, InsufficientStockException {
        {
            System.out.println("Bem vindo ao sistema de vendas da Brastore!");

            menu();
        }
    }

    // Método que exibe o menu e chama o método de cada opção
    public void menu() throws InvalidSaleValueException, ProductNotFoundException, InsufficientStockException {
        boolean validator = true;
        boolean initialStock = false;

        // Inicializa o Scanner para leitura de dados
        Scanner input = new Scanner(System.in);
        String option = " ";

        // Mostra o Menu Principal. Enquanto o usuário não digitar a opção de sair, o menu é exibido
        while (validator) {
            System.out.println("\n## MENU PRINCIPAL ##");
            System.out.println("|------------------|");
            for (MenuEnum navigation : MenuEnum.values()) {
                System.out.println("  " + navigation.getOption() + " - " + navigation.getDescription());
            }
            System.out.println("|------------------|");
            System.out.print("Opção: ");
            option = input.nextLine();
            System.out.println();

            switch (option) {
                case "0" -> {
                    System.out.println("### OBRIGADO... VOLTE SEMPRE!!! ###");
                    System.exit(0);
                }
                case "1" -> transactionMenu(); // Chama o menu de transações
                case "2" -> initialStock = stockMenu(initialStock); // Chama o menu de estoque
                default -> {
                    System.err.println("Digite uma opção válida");
                    RunTime.ThreadDelay();
                }
            }
        }

    }

    private boolean stockMenu(boolean initialStock) throws InvalidSaleValueException, ProductNotFoundException {
        int option;
        boolean validate = false;

        // Mostra o Menu de Estoque. Enquanto o usuário não digitar a opção de sair, o menu é exibido
        while (!validate) {
            System.out.println();
            System.out.println("########  MENU ESTOQUE ########");
            System.out.println("+------------------------------+");
            System.out.println("| 1 - CADASTRAR PRODUTO        |");
            System.out.println("| 2 - BUSCAR POR SKU           |");
            System.out.println("| 3 - LISTAR TODO ESTOQUE      |");
            System.out.println("| 4 - ATUALIZAR PRODUTO        |");
            System.out.println("| 5 - VOLTAR AO MENU ANTERIOR  |");
            System.out.println("| 6 - INICIALIZAR ESTOQUE      |");
            System.out.println("+------------------------------+");
            System.out.println("| 7 - SAIR                     |");
            System.out.println("+------------------------------+");
            System.out.print("\nOpção: ");

            try {
                option = Integer.parseInt(input.nextLine()); // Lê a opção digitada pelo usuário
                switch (option) {
                    case 1 -> StockMenuCommunication.createProduct(); // Chama o método de cadastro de produto
                    case 2 -> StockMenuCommunication.searchForSku(); // Chama o método de busca de produto por SKU
                    case 3 -> StockMenuCommunication.listAllStock(); // Chama o método de listagem de todo o estoque
                    case 4 -> stockMenuUpdate(); // Chama o menu de atualização de produto no estoque
                    case 5 -> {break;} // Sai do menu de estoque
                    case 6 -> {
                        if(!initialStock) { // Verifica se o estoque já foi inicializado
                            initialStock(); // Chama o método de inicialização de estoque
                            return true; // Retorna true para a variável que verifica se o estoque já foi inicializado
                        }
                    }
                    case 7 -> { // Sai do sistema
                        System.out.println("### OBRIGADO... VOLTE SEMPRE!!! ###");
                        System.exit(0);
                        break;
                    }
                    case default -> System.out.println("Opção inválida!");
                }
                validate = true;
            } catch (Exception e) { // Caso o usuário digite uma opção inválida, o sistema exibe uma mensagem de erro e redireciona o usuário para o menu de estoque novamente
                validate = false;
            }

        }
        return true;
    }

    // Método que exibe o menu de vendas e chama o método de cada opção
    private void transactionMenu() throws ProductNotFoundException, InsufficientStockException {
        int option;
        boolean validate = false;

        // Mostra o Menu de Vendas. Enquanto o usuário não digitar a opção de sair, o menu é exibido
        while (!validate) {
            System.out.println();
            System.out.println("########  MENU VENDAS ########");
            System.out.println("+------------------------------+");
            System.out.println("| 1 - REALIZAR VENDAS          |");
            System.out.println("| 2 - HISTÓRICO DE VENDAS      |");
            System.out.println("| 3 - VOLTAR AO MENU ANTERIOR  |");
            System.out.println("+------------------------------+");
            System.out.println("| 4 - SAIR                     |");
            System.out.println("+------------------------------+");
            System.out.print("\nOpção: ");

            try {
                option = Integer.parseInt(input.nextLine()); // Lê a opção digitada pelo usuário
                switch (option) {
                    case 1 -> TransactionCommunication.sale(); // Chama o método de venda
                    case 2 -> TransactionCommunication.listTransactions(); // Chama o método de listagem de vendas
                    case 3 -> {break;} // stockMenu(validate); --> Sai do menu de vendas e volta para o menu de estoque
                    case 4 -> {
                        System.out.println("### OBRIGADO... VOLTE SEMPRE!!! ###");
                        System.exit(0); // Sai do sistema
                    }
                    case default -> System.out.println("Opção inválida!");
                }

                validate = true;
            } catch (Exception e) {
                continue;
            }
        }

    }

    // Método que exibe o menu de atualização de produto no estoque e chama o método de cada opção
    private void stockMenuUpdate() throws InvalidSaleValueException, ProductNotFoundException {
        int option;
        boolean validate = false;

        // Mostra o Menu de Atualização de Produto. Enquanto o usuário não digitar a opção de sair, o menu é exibido
        while (!validate) {
            System.out.println();
            System.out.println("########  ATUALIZAR ESTOQUE ########");
            System.out.println("+------------------------------+");
            System.out.println("| 1 - ATUALIZAR QUANTIDADE     |");
            System.out.println("| 2 - ATUALIZAR PREÇO          |");
            System.out.println("| 3 - EXCLUIR PRODUTO          |");
            System.out.println("| 4 - VOLTAR AO MENU ANTERIOR  |");
            System.out.println("+------------------------------+");
            System.out.println("| 5 - SAIR                     |");
            System.out.println("+------------------------------+");
            System.out.print("\nOpção: ");

            try {
                option = Integer.parseInt(input.nextLine()); // Lê a opção digitada pelo usuário
                switch (option) {
                    case 1 -> StockMenuCommunication.updateProductQuantity(); // Chama o método de atualização de quantidade de produto
                    case 2 -> StockMenuCommunication.updateProductPrice(); // Chama o método de atualização de preço de produto
                    case 3 -> StockMenuCommunication.deleteProduct(); // Chama o método de exclusão de produto
                    case 4 -> stockMenu(validate); // Sai do menu de atualização de produto e volta para o menu de estoque
                    case 5 -> System.exit(0); // Sai do sistema
                    case default -> System.out.println("Opção inválida!"); // Caso o usuário digite uma opção inválida, o sistema exibe uma mensagem de erro e redireciona o usuário para o menu de atualização de produto novamente
                }

                validate = true;
            } catch (Exception e) {
                continue;
            }
        }
    }

    // Método que inicializa o estoque com alguns produtos
    public void initialStock() throws InvalidSaleValueException {
        ProductController controller = new ProductController(); // Instancia um objeto da classe ProductController

        // Cria alguns produtos e adiciona no estoque
        controller.insertProduct("ADD4310405023150", "Calça masculina Adidas vermelha, tamanho XG, estação Verão", 150, 38.72, 149.90);
        controller.insertProduct("ZAR4110435023256", "Camiseta masculina Zara marrom, tamanho G, estação Primavera", 200, 100.00, 400.00);
        controller.insertProduct("CNL0111045223805", "Perfume Chanel 5 feminino ", 55, 38.50, 250.00);
        controller.insertProduct("GUC1013415323953", "Cachecol branco Gucci Bebê, tamanho M", 60, 99.99, 348.50);
        controller.insertProduct("JLS3513425023354", "Blusa 13 Joules Bebê, tamanho RN, estação Outono", 100, 90.00, 280.00);
        controller.insertProduct("MZN4511045123452", "Calçado Mizuno feminino azul, tamanho 43/44", 50, 82.00, 372.90);
    }
}
