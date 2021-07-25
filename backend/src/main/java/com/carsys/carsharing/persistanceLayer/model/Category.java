package com.carsys.carsharing.persistanceLayer.model;

import com.carsys.carsharing.service.service.model.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    M("M"),

    N("N"),

    E("E"),

    H("H"),

    C("C"),

    D("D"),

    I("I"),

    J("J"),

    S("S"),

    R("R"),

    F("F"),

    G("G"),

    P("P"),

    U("U"),

    L("L"),

    W("W"),

    O("O"),

    X("X");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
