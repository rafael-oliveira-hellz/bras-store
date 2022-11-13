package br.com.brastore.system.exception;

public class CustomerNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long id) {
        super("Nenhum cliente encontrado com o CPF: " + id);
    }
}
