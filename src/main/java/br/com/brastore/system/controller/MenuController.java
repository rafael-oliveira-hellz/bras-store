package br.com.brastore.system.controller;

import br.com.brastore.system.utils.ValidateMenu;

public class MenuController {
    public int validationRegexMenu(String entry, String verifyMenu) {

        ValidateMenu validate = new ValidateMenu();
        return validate.menuValidation(entry, verifyMenu);
    }
}
