package com.cognizant.truyum.dao;

public class CartEmptyException extends Exception{
    public CartEmptyException() {
    }

    public CartEmptyException(String message) {
        super(message);
    }
}
