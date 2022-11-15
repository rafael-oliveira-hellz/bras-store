package br.com.brastore.system.communication;

import br.com.brastore.system.controller.ProductController;
import br.com.brastore.system.enums.*;
import br.com.brastore.system.exception.InvalidSaleValueException;
import br.com.brastore.system.exception.ProductNotFoundException;

import java.util.Objects;
import java.util.Scanner;

public class StockMenuCommunication {
    static ProductController productController = new ProductController();
    static Scanner input = new Scanner(System.in);

    public static void createProduct() throws InvalidSaleValueException {
        String sku = "";
        String optionString = "";
        int firstOption;
        String option;
        boolean validate = false;

        while (!validate) {
            System.out.println("\nMarcas\n");

            for (BrandEnum brands : BrandEnum.values()) {
                System.out.println(brands.getOption() + " - " + brands.getDescription());
            }

            try {
                System.out.print("Escolha o dígito de uma marca: ");
                firstOption = Integer.parseInt(input.nextLine());
                optionString = Objects.requireNonNull(BrandEnum.getBrandByOption(firstOption)).getKey();

                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }
        }

        sku += optionString;
        validate = false;

        while (!validate) {
            System.out.println("\nTamanho\n");

            for (SizeEnum sizes : SizeEnum.values()) {
                System.out.println(sizes.getKey() + " - " + sizes.getDescription());
            }

            try {
                System.out.print("Escolha a opção desejada: ");
                option = input.nextLine();

                optionString = SizeEnum.get(option).getKey();

                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }
        }

        sku += optionString;
        validate = false;

        while (!validate) {
            System.out.println("\nCategoria\n");

            for (CategoryEnum categories : CategoryEnum.values()) {
                System.out.println(categories.getKey() + " - " + categories.getDescription());
            }

            try {
                System.out.print("Escolha a opção desejada: ");
                option = input.nextLine();

                optionString = CategoryEnum.get(option).getKey();

                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }
        }

        sku += optionString;
        validate = false;

        while (!validate) {
            System.out.println("\nEstação\n");

            for (SeasonEnum seasons : SeasonEnum.values()) {
                System.out.println(seasons.getKey() + " - " + seasons.getDescription());
            }

            try {
                System.out.print("Escolha a opção desejada: ");
                option = input.nextLine();

                optionString = SeasonEnum.get(option).getKey();

                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }
        }

        sku += optionString;
        validate = false;

        while (!validate) {
            System.out.println("\nDepartamento\n");

            for (DepartmentEnum departments : DepartmentEnum.values()) {
                System.out.println(departments.getKey() + " - " + departments.getDescription());
            }

            try {
                System.out.print("Escolha a opção desejada: ");
                option = input.nextLine();

                optionString = DepartmentEnum.get(option).getKey();

                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }
        }

        sku += optionString;
        validate = false;

        while (!validate) {
            System.out.println("\nTipo\n");

            for (TypeOfProductEnum types : TypeOfProductEnum.values()) {
                System.out.println(types.getKey() + " - " + types.getDescription());
            }

            try {
                System.out.print("Escolha a opção desejada: ");
                option = input.nextLine();

                optionString = TypeOfProductEnum.get(option).getKey();

                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }
        }

        sku += optionString;
        validate = false;

        while (!validate) {
            System.out.println("\nCor\n");

            for (ColorEnum types : ColorEnum.values()) {
                System.out.println(types.getKey() + " - " + types.getDescription());
            }

            try {
                System.out.print("Escolha a opção desejada: ");
                option = input.nextLine();

                optionString = ColorEnum.get(option).getKey();

                validate = true;
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }
        }

        sku += optionString;

        validate = false;
        int quantity = 0;

        while (!validate) {
            try {
                System.out.print("Digite a quantidade que deseja adicionar ao estoque: ");
                quantity = Integer.parseInt(input.nextLine());
                validate = true;
            } catch (Exception e) {
                System.out.println("Quantidade inválida");
                continue;
            }
        }

        System.out.print("\nDigite a descrição: ");
        String description = input.nextLine();

        validate = false;
        double buyPrice = 0.0;
        double sellPrice = 0.0;

        while (!validate) {
            try {
                System.out.print("\nDigite o preço de compra: ");
                buyPrice = Double.parseDouble(input.nextLine());

                System.out.print("\nDigite o preço de venda: ");
                sellPrice = Double.parseDouble(input.nextLine());

                validate = true;
            } catch (Exception e) {
                System.out.println("Preços inválidos");
                continue;
            }
        }

        System.out.println(productController.insertProduct(sku, description, quantity, buyPrice, sellPrice) + ", SKU: " + sku);
    }

    public static void searchForSku() throws ProductNotFoundException {
        System.out.println("Informe o SKU do produto: ");
        String sku = input.next();
        System.out.println(productController.findSku(sku));
    }

    public static void listAllStock() {
        System.out.println(productController.listAll());

    }

    public static void updateProductQuantity() {
        boolean validate = false;
        while (!validate) {
            System.out.println("\nAtualizar Quantidade do Estoque\n");
            try {
                System.out.print("Digite o SKU do produto: ");
                String sku = input.nextLine();
                System.out.print("Digite a quantidade do produto: ");
                Integer quantity = input.nextInt();
                validate = true;
                productController.update(sku, quantity);
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }

        }

    }

    public static void updateProductPrice() {
        boolean validate = false;
        while (!validate) {
            System.out.println("\nAtualizar Preço do Estoque\n");
            try {
                System.out.print("Digite o SKU do produto: ");
                String sku = input.nextLine();
                System.out.print("Digite o preço de compra do produto: ");
                Double purchasePrice = input.nextDouble();
                System.out.print("Digite o preço de venda do produto: ");
                Double salePrice = input.nextDouble();
                validate = true;
                productController.update(sku, purchasePrice, salePrice);
            } catch (Exception e) {
                System.out.println("Opção inválida");
                continue;
            }

        }

    }

    public static void deleteProduct() throws ProductNotFoundException {
        System.out.println("Digite o SKU do produto que será removido: ");
        String sku = input.nextLine();
        boolean validate = false;

        System.out.print("Deseja mesmo remover? S/N");
        char option = input.nextLine().charAt(0);

        if (option == 'S' || option == 's') {
            System.out.println(productController.delete(sku));
            validate = true;
        } else {
            return;
        }
    }
}
