package com.carsys.carsharing.persistanceLayer.model;

public enum Discount {
    STUDENT("",5),
    EXCLUSIVE("",10);

    private final String description;

    private final int percentage;


    Discount(String description,final int percentage) {
        this.description = description;
        this.percentage = percentage;
    }

    public String getDescription() {
        return description;
    }

    public int getPercentage() {
        return percentage;
    }
}
