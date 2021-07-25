package com.carsys.carsharing.persistanceLayer.model;

public enum FuelAircondition {
    R("R"),

    N("N"),

    D("D"),

    Q("Q"),

    H("H"),

    I("I"),

    E("E"),

    C("C"),

    L("L"),

    S("S"),

    A("A"),

    B("B"),

    M("M"),

    F("F"),

    V("V"),

    Z("Z"),

    U("U"),

    X("X");

    private String value;

    private FuelAircondition(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
