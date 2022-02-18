package dk.acto.blackdragon.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import dk.acto.blackdragon.model.AuthorData;
import dk.acto.blackdragon.model.Model;
import io.vavr.collection.List;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Array;
import java.util.*;

public class ImModel<T>  implements ModelFactory{

    @SneakyThrows
    @Override
    public List<Model> parse(String string) {
        final int SIZE = 4;
        String[] arrOfStr = string.split(",|\\R");
        String[] modifiedArray = Arrays.copyOfRange(arrOfStr, 4, arrOfStr.length);

        List<Model> mList = List.empty();



        String[] result = new String[(int) Math.ceil((double) modifiedArray.length / SIZE)];
        for (int i = 0, j = 0; i < modifiedArray.length; i += SIZE, j++) {
            result[j] = String.join(",", Arrays.copyOfRange(modifiedArray, i, Integer.min(modifiedArray.length, i + SIZE)));
        }

        ArrayList<String> thelines = new ArrayList<String>();
        String[] lines= new String[0];

        ArrayList<String[]> ar = new ArrayList<String[]>();

        for(String item : result) {
            String[] allLines = item.split(",");

            for(String line : allLines) {
                lines = line.split(",");
                for(String d : lines) {
                    thelines.add(d.trim());
                }
            }
            long newid =  Long.parseLong(allLines[0]);
            double newweight = Double.parseDouble(allLines[1]);
            int newcost = Integer.parseInt(allLines[2].substring(1));
            long newinventory = Long.parseLong(allLines[3].substring(1));
            allLines = new String[0];
            Model data = Model.builder().
                    id(newid).
                    cost(newcost).
                    inventory(newinventory).
                    weight(newweight).
                    build();
            mList = mList.append(data);
        }
        return mList;
    }

}
