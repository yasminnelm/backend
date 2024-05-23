package com.example.backend.model.enumeration;

import lombok.Data;

public enum CompteType {
    COMPTE_200("Compte 200", 200),
    COMPTE_5000("Compte 5000", 5000),
    COMPTE_20000("Compte 20000", 20000);

    private final String description;
    private final double plafond;

    CompteType(String description, double plafond) {
        this.description = description;
        this.plafond = plafond;
    }

    public String getDescription() {
        return description;
    }

    public double getPlafond() {
        return plafond;
    }

    public static CompteType fromDescription(String description) {
        for (CompteType compteType : CompteType.values()) {
            if (compteType.getDescription().equalsIgnoreCase(description.trim())) {
                return compteType;
            }
        }
        throw new IllegalArgumentException("No such value for CompteType description: " + description);
    }

    public static CompteType fromPlafond(double plafond) {
        for (CompteType compteType : CompteType.values()) {
            if (compteType.getPlafond() == plafond) {
                return compteType;
            }
        }
        throw new IllegalArgumentException("No such value for CompteType plafond: " + plafond);
    }

    public static CompteType fromDescriptionOrPlafond(String descriptionOrPlafond) {
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
