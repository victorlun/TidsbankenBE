package com.example.tidsbanken.enumerator;

public enum VacationType {
    PARENTAL_LEAVE("Parental Leave"),
    SICK_LEAVE("Sick Leave"),
    VACATION_LEAVE("Vacation Leave"),
    PUBLIC_HOLIDAY("Public Holiday");

    private String displayValue;

    private VacationType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
