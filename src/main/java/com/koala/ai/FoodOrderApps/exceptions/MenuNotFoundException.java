package com.koala.ai.FoodOrderApps.exceptions;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(String message) {
        super(message);
    }
}
