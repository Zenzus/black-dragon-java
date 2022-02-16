package dk.acto.blackdragon.service;


import lombok.SneakyThrows;

import java.net.URL;

class HelloWorldApp {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.

        ImAuthorData subject = new ImAuthorData() {};
        subject.create();


        ImDataFetcher kage = new ImDataFetcher();
        kage.fetchData(new URL("https://dragon.acto.dk/test.csv"));

    }
}