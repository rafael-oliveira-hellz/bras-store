package br.com.brastore.system.utils;

public class ValidateRegex {
    public Boolean regexValidation(String input, String regex) {
        return input.matches(regex);
    }
}
