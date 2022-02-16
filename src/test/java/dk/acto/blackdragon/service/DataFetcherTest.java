package dk.acto.blackdragon.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class DataFetcherTest {

    @Test(groups = "fetch")
    public void testFetchData(ITestContext context) throws Exception {

        ImDataFetcher subject = new ImDataFetcher() {
            @Override
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
        };

        String result = subject.fetchData(new URL("https://dragon.acto.dk/test.csv"));
        assertNotNull(result);
        assertEquals(result.length(), 508);
        context.setAttribute("data", result);
    }
}
