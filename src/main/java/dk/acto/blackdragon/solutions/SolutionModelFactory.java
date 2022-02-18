package dk.acto.blackdragon.solutions;

import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.service.ModelFactory;
import io.vavr.collection.List;
import lombok.SneakyThrows;

import java.util.*;

public class SolutionModelFactory<T>  implements ModelFactory {

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
