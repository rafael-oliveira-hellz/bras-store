package br.com.brastore.system.utils;

public class ValidateRegex {
    // Função para validar se o valor passado atende ao regex passado
    public Boolean regexValidation(String input, String regex) {
        return input.matches(regex);
    }
}
