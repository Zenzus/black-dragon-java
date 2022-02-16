package dk.acto.blackdragon.service;

import okhttp3.*;


import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ImDataFetcher implements DataFetcher{

    public String fetchData(URL url) {
        String path = "d:\\tmp.csv";
        String allText="";
        try {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Failed to download file:  "+ response);
        }
        FileOutputStream fos = new FileOutputStream("d:/tmp.csv");
        fos.write(response.body().bytes());

        fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Charset encoding = Charset.defaultCharset();
            List<String> lines = Files.readAllLines(Paths.get(path), encoding);
            allText = lines.stream().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allText;
    }
}
