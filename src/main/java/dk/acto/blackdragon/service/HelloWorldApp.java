package dk.acto.blackdragon.service;


import dk.acto.blackdragon.model.Model;
import io.vavr.collection.List;
import lombok.SneakyThrows;

import java.net.URL;

class HelloWorldApp {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.

        ImAuthorData subject = new ImAuthorData() {};
        subject.create();


        ImDataFetcher kage = new ImDataFetcher();
        String result = kage.fetchData(new URL("https://dragon.acto.dk/test.csv"));


        ImModel model = new ImModel();

        List<Model> models = model.parse(result);

        ImModelTrans trans = new ImModelTrans();

        trans.transform(models);

    }
}