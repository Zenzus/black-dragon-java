package dk.acto.blackdragon.solutions;

import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.model.Stats;
import dk.acto.blackdragon.service.ModelTransformer;
import io.vavr.collection.List;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SolutionModelTrans implements ModelTransformer {


    @Override
    public Stats transform(List model) {


        //System.out.println(model);

        BigDecimal meanCost = BigDecimal.ZERO;

        BigDecimal weightedInventory = BigDecimal.ZERO;

        BigInteger totalInventory = BigInteger.ZERO;

        int evenids = 0;
        int oddids = 0;


        for (int i = 0; i < model.length(); i++) {
            Model m = (Model) model.get(i);


            if (m.getId() %2==0){
                evenids++;

            }else{
                oddids++;
            }

            BigDecimal cost = BigDecimal.valueOf(m.getCost()/100);
            meanCost = meanCost.add(cost);


            Double inW = m.getInventory()* m.getWeight();
            weightedInventory = weightedInventory.add(BigDecimal.valueOf(inW));

            int inventory = (int) (m.getInventory());
            totalInventory = totalInventory.add(BigInteger.valueOf(inventory));

        }

        BigInteger eIds = BigInteger.valueOf(evenids);
        BigInteger oIds = BigInteger.valueOf(oddids);

        BigDecimal mean = new BigDecimal(model.length());
        meanCost = meanCost.divide(mean);



        Stats data = Stats.builder().
                evenIds(eIds).
                oddIds(oIds).
                meanCost(meanCost).
                weightedInventory(weightedInventory).
                totalInventory(totalInventory).
                build();


/*
        System.out.println("This is Even ids :"+evenids);
        System.out.println("This is odd ids :"+oddids);
        System.out.println("This is cost Sum :"+meanCost);
        System.out.println("This is weightedInventory Sum :"+weightedInventory);
        System.out.println("This is Totalt Inventory Sum :"+totalInventory);
*/
        return data;
    }
}
