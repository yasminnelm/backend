package com.example.backend.model.enumeration;

import lombok.Data;

public enum AccountType {
    COMPTE_200("Compte 200", 200),
    COMPTE_5000("Compte 5000", 5000),
    COMPTE_20000("Compte 20000", 20000),
    COMPTE_PROFESSIONNEL("Professional Account", Double.MAX_VALUE);
    private final String description;
    private final double plafond;

    AccountType(String description, double plafond) {
        this.description = description;
        this.plafond = plafond;
    }

    public String getDescription() {
        return description;
    }

    public double getPlafond() {
        return plafond;
    }

    public static AccountType fromDescription(String description) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.getDescription().equalsIgnoreCase(description.trim())) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("No such value for CompteType description: " + description);
    }

    public static AccountType fromPlafond(double plafond) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.getPlafond() == plafond) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("No such value for CompteType plafond: " + plafond);
    }

    public static AccountType fromDescriptionOrPlafond(String descriptionOrPlafond) {
        // Convertir la chaîne en double
        try {
            double plafond = Double.parseDouble(descriptionOrPlafond.trim());
            return fromPlafond(plafond);
        } catch (NumberFormatException e) {
            // Donc La chaîne est une description
            return fromDescription(descriptionOrPlafond.trim());
        }
    }
}
