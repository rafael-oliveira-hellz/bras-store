package br.com.brastore.system;

import br.com.brastore.system.communication.MainMenu;
import br.com.brastore.system.exception.InsufficientStockException;
import br.com.brastore.system.exception.InvalidSaleValueException;
import br.com.brastore.system.exception.ProductNotFoundException;

/**
 * @author Rafael de Oliveira (Hellz)
 * @version 1.0
 * @since 11/11/2022
 */

public class Main
{
    public static void main( String[] args ) throws InvalidSaleValueException, ProductNotFoundException, InsufficientStockException {

        MainMenu menu = new MainMenu();
    }
}
