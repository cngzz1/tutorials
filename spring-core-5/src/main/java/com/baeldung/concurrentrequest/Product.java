package com.baeldung.concurrentrequest;

public class Product<E> {

    private final int id;
    private final String name;
    private final E stock;

    public Product(int id, String name, E stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public E get() {
        return stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
