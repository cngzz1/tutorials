package com.baeldung.concurrentrequest;

public class Stock<E> implements IStock<E> {
    private E stockType;
    private final int inStockItems;

    public Stock(int inStockItems) {
        this.inStockItems = inStockItems;
    }

    public int getInStockItems() {
        return inStockItems;
    }
    public E getType(){
        return stockType;
    }
}
