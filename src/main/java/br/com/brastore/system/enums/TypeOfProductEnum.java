package br.com.brastore.system.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TypeOfProductEnum {
    PANTS("231", "Calça"),
    TSHIRT("232", "Camisa"),
    BLOUSE("233", "Blusa"),
    SHOES("234", "Tênis"),
    FLIPFLOPS("235", "Chinelos"),
    INTIMATE("236", "Roupa Íntima"),
    MAKEUP("237", "Maquiagem"),
    COSMETICS("238", "Cosméticos"),
    SCARF("239", "Cachecol"),
    TIE("240", "Gravata");

    public final String key;
    public final String description;

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    TypeOfProductEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    private static final Map<String, TypeOfProductEnum> Lookup = new HashMap<>();

    static {
        for (TypeOfProductEnum keyValue : EnumSet.allOf(TypeOfProductEnum.class))
            Lookup.put(keyValue.getKey(), keyValue);
    }

    public static TypeOfProductEnum get(String key) {
        return Lookup.get(key);
    }

    public static TypeOfProductEnum getTypeaByDescription(String description) {
        for (TypeOfProductEnum keyValue : EnumSet.allOf(TypeOfProductEnum.class)) {
            if (keyValue.getDescription().equals(description))
                return keyValue;
        }
        return null;
    }
}

