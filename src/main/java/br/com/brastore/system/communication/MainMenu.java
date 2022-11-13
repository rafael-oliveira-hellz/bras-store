package br.com.brastore.system.communication;

import br.com.brastore.system.controller.ProductController;
import br.com.brastore.system.enums.MenuEnum;
import br.com.brastore.system.exception.InsufficientStockException;
import br.com.brastore.system.exception.InvalidSaleValueException;
import br.com.brastore.system.exception.ProductNotFoundException;
import br.com.brastore.system.utils.RunTime;

import java.util.Scanner;

public class MainMenu {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InvalidSaleValueException, ProductNotFoundException, InsufficientStockException {
        new MainMenu();

    }

    public MainMenu() throws InvalidSaleValueException, ProductNotFoundException, InsufficientStockException {
        {
            String fakeUser = "Admin";
            String fakePassword = "*****";

            while (true) {
                System.out.println("### FAÇA SEU LOGIN ###");
                System.out.println("----------------------");
                System.out.print("Digite seu login: ");
                String user = input.nextLine();
                System.out.print("Digite sua senha: ");
                String password = input.nextLine();

                if ((!user.equals(fakeUser) || (!password.equals(fakePassword)))) {
                    System.out.println("Acesso negado!!!");
                } else {
                    menu();
                    break;
                }
            }
        }
    }

    public void menu() throws InvalidSaleValueException, ProductNotFoundException, InsufficientStockException {
        boolean validator = true;
        boolean initialStock = false;
        Scanner input = new Scanner(System.in);
        String option = " ";
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
                case "1" -> transactionMenu();
                case "2" -> initialStock = stockMenu(initialStock);
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
                option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1 -> StockMenuCommunication.createProduct();
                    case 2 -> StockMenuCommunication.searchForSku();
                    case 3 -> StockMenuCommunication.listAllStock();
                    case 4 -> stockMenuUpdate();
                    case 5 -> {break;}
                    case 6 -> {
                        if(!initialStock) {
                            initialStock();
                            return true;
                        }
                    }
                    case 7 -> {
                        System.out.println("### OBRIGADO... VOLTE SEMPRE!!! ###");
                        System.exit(0);
                        break;
                    }
                    case default -> System.out.println("Opção inválida!");
                }
                validate = true;
            } catch (Exception e) {
                validate = false;
            }

        }
        return true;
    }

    private void transactionMenu() throws ProductNotFoundException, InsufficientStockException {
        int option;
        boolean validate = false;

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
                option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1 -> TransactionCommunication.purchase();
                    case 2 -> TransactionCommunication.listTransactions();
                    // se não funcionar - mudar para: {break;}
                    case 3 -> stockMenu(validate);
                    case 4 -> {
                        System.out.println("### OBRIGADO... VOLTE SEMPRE!!! ###");
                        System.exit(0);
                    }
                    case default -> System.out.println("Opção inválida!");
                }

                validate = true;
            } catch (Exception e) {
                continue;
            }
        }

    }

    private void stockMenuUpdate() throws InvalidSaleValueException, ProductNotFoundException {
        int option;
        boolean validate = false;

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
                option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1 -> StockMenuCommunication.updateProductQuantity();
                    case 2 -> StockMenuCommunication.updateProductPrice();
                    case 3 -> StockMenuCommunication.deleteProduct();
                    case 4 -> stockMenu(validate);
                    case 5 -> System.exit(0);
                    case default -> System.out.println("Opção inválida!");
                }

                validate = true;
            } catch (Exception e) {
                continue;
            }
        }
    }

    public void initialStock() throws InvalidSaleValueException {
        ProductController controller = new ProductController();
        controller.insertProduct("ADI2710231405050", "Calça masculina Adidas vermelha, tamanho XG, estação Verão", 10, 38.72, 220.00);
        controller.insertProduct("CNL0111238045205", "Perfume Chanel 5 feminino ", 55, 38.50, 250.00);
        controller.insertProduct("GUC1512239415353", "Cachecol branco Gucci Bebê, tamanho M", 55, 20.50, 85.40);
    }
}
