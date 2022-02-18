package dk.acto.blackdragon.solutions;


import dk.acto.blackdragon.model.Model;
import io.vavr.collection.List;
import lombok.SneakyThrows;

import java.net.URL;

class HelloWorldApp {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.

        SolutionAuthorData subject = new SolutionAuthorData() {};
        subject.create();


        SolutionDataFetcher kage = new SolutionDataFetcher();
        String result = kage.fetchData(new URL("https://dragon.acto.dk/test.csv"));


        SolutionModelFactory model = new SolutionModelFactory();

        List<Model> models = model.parse(result);

        SolutionModelTrans trans = new SolutionModelTrans();

        trans.transform(models);

    }
}