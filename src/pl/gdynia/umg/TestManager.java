package pl.gdynia.umg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestManager {
    private static List<Predicate<AbstractTransaction>> rulesList;
    private static ArrayList<AbstractTransaction> trValues = trValues = new ArrayList<AbstractTransaction>();
    public static void initiateRuleList(){
        rulesList = Arrays.asList(
                t -> ((Transaction) t).getSourceCommunityID() == ((Transaction) t).getTargetCommunityID(),
                t -> ((Transaction) t).getSourceID() != ((Transaction) t).getTargetID(),
                t -> ((Transaction) t).getQuantity() > 0,
                t -> ((Transaction) t).getSourceSurplus() > 0,
                t -> ((Transaction) t).getSourceSurplus() >= ((Transaction) t).getQuantity(),
                t -> ((Transaction) t).getTargetNeed() > 0,
                t -> ((Transaction) t).getTargetNeed() > ((Transaction) t).getQuantity(),
                t -> ((Transaction) t).getTargetNeed() > ((Transaction) t).getTargetBatteryEnergySurplus());
    }
    public static void initiateTransactionValues(){
        trValues.clear();
        // positive
        trValues.add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        // negative
        trValues.add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,100));
        trValues.add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,200, 1,1));
        trValues.add(new Transaction(0.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        trValues.add(new Transaction(500.0,0.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        trValues.add(new Transaction(500.0,50.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        trValues.add(new Transaction(500.0,1000.0,0.0,0.0, 10.0, 200,7000, 1,1));
        trValues.add(new Transaction(500.0,1000.0,50.0,0.0, 10.0, 200,7000, 1,1));
        trValues.add(new Transaction(500.0,1000.0,2500.0,0.0, 7500.0, 200,7000, 1,1));
    }
    public static Predicate<AbstractTransaction> getRule(int number){
        return rulesList.get(number);
    }
    public static AbstractTransaction getTransaction(int number){
        return trValues.get(number);
    }
}
