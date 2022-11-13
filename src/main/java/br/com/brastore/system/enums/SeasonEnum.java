package br.com.brastore.system.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SeasonEnum {

    SUMMER("40", "Verão"),
    WINTER("41", "Inverno"),
    FALL("42", "Outono"),
    SPRING("43", "Primavera"),
    NA("04", "Não se aplica");

    public final String key;
    public final String description;

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    SeasonEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    private static final Map<String, SeasonEnum> Lookup = new HashMap<>();

    static {
        for (SeasonEnum keyValue : EnumSet.allOf(SeasonEnum.class))
            Lookup.put(keyValue.getKey(), keyValue);
    }

    public static SeasonEnum get(String key) {
        return Lookup.get(key);
    }

    public static SeasonEnum getByDescription(String description) {
        for (SeasonEnum keyValue : EnumSet.allOf(SeasonEnum.class))
            if (keyValue.getDescription().equals(description))
                return keyValue;
        return null;
    }
}

