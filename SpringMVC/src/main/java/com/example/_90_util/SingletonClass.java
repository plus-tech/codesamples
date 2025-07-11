package com.example._90_util;

import org.springframework.stereotype.Component;

/*
 * 
 */
@Component
public class SingletonClass {
    private static SingletonClass instance; // Private static instance

    private SingletonClass() {
        // Private constructor to prevent external instantiation
    }

    public static SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass(); // Create instance only if null
        }
        return instance;
    }

    // Other methods and fields of the singleton class
    public void showMessage() {
        System.out.println("Hello from the Singleton!");
    }
}
