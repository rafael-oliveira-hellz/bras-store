package br.com.brastore.system.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BrandEnum {
    HEM("HEM", "H&M", 1),
    ZARA("ZAR", "Zara", 2),
    UNIQLO("UNQ", "Uniqlo", 3),
    CARTIER("CTR", "Cartier", 4),
    HERMES("HMS", "Hermes", 5),
    GUCCI("GUC", "Gucci", 6),
    SNEAKERS("SNK", "Sneakers", 7),
    OXFORD("OXD", "Oxford", 8),
    SLIPONS("SLS", "Slip-ons", 9),
    MIZUNO("MZN", "Mizuno", 10),
    ADIDAS("ADD", "Adidas", 11),
    NEWBALANCE("NBC", "New Balance", 12),
    CHANEL5("CNL", "Chanel 5", 13),
    CLINIQUE("CNQ", "Clinique", 14),
    DIOR("DIR", "Dior", 15),
    LAMER("LMR", "Lamer", 16),
    NARS("NAR", "Nars", 17),
    OLAY("OLY", "Olay", 18),
    IZASOLER("ISL", "Iza Sóler", 19),
    ZIOVARA("ZVR", "Ziovara", 20),
    GEMMA("GMM", "Gemma", 21),
    LINAPRADES("LNS", "Lina Prades", 22),
    JOULES("JLS", "13 Joules", 23),
    LEANSTUDIO("LST", "Lean Studio", 24);

    private final String key;
    private final String description;
    private final Integer option;

    BrandEnum(String key, String description, Integer option) {
        this.key = key;
        this.description = description;
        this.option = option;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }


    public Integer getOption() {
        return option;
    }

    private static final Map<String, BrandEnum> Lookup = new HashMap<>();

    static {
        for (BrandEnum keyValue : EnumSet.allOf(BrandEnum.class))
            Lookup.put(keyValue.getKey(), keyValue);
    }

    public static BrandEnum get(String key) {
        return Lookup.get(key);
    }

    public static BrandEnum getBrandByDescription(String description) {
        for (BrandEnum keyValue : EnumSet.allOf(BrandEnum.class))
            if (keyValue.getDescription().equals(description))
                return keyValue;
        return null;
    }

    public static BrandEnum getBrandByOption(Integer option) {
        for (BrandEnum keyValue : EnumSet.allOf(BrandEnum.class))
            if (keyValue.getOption().equals(option))
                return keyValue;
        return null;
    }
}
