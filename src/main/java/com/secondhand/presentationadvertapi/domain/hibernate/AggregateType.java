package com.secondhand.presentationadvertapi.domain.hibernate;

public enum AggregateType {
    Advert("advert", (short) 1),
    Category("category", (short) 2),
    ;

    private final String name;

    private final Short id;

    AggregateType(String name, Short id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Short getId() {
        return id;
    }
}
