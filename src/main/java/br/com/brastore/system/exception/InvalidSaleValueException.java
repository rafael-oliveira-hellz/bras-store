package br.com.brastore.system.exception;

public class InvalidSaleValueException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidSaleValueException() {
        // TODO - Verificar se é necessário mudar a mensagem da exceção
        super("Lucro menor que 25%. Operação inválida!");
    }
}
