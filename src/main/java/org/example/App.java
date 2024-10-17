package org.example;

import storage.Storage;

import static service.Service.init;

public class App {
    public static void main( String[] args ) {
        init(new Storage());
    }
}
