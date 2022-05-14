package com.baeldung.concurrentrequest;

public interface IStock<E> {
    int getInStockItems();
    E getType();
}
