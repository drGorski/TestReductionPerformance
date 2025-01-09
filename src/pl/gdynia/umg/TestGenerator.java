package pl.gdynia.umg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestGenerator {
    public static List<Predicate<AbstractTransaction>> rulesList;
    public static List<List<Predicate<AbstractTransaction>>> configurations;
    public static ArrayList<Transaction>[] trValues;
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public TestGenerator(){}

    public static void setRules(){
        // initiate rule list
        rulesList = Arrays.asList(
                t -> ((Transaction) t).getSourceCommunityID() == ((Transaction) t).getTargetCommunityID(),
                t -> ((Transaction) t).getSourceID() != ((Transaction) t).getTargetID(),
                t -> ((Transaction) t).getQuantity() > 0,
                t -> ((Transaction) t).getSourceSurplus() > 0,
                t -> ((Transaction) t).getSourceSurplus() >= ((Transaction) t).getQuantity(),
                t -> ((Transaction) t).getTargetNeed() > 0,
                t -> ((Transaction) t).getTargetNeed() > ((Transaction) t).getQuantity(),
                t -> ((Transaction) t).getTargetNeed() > ((Transaction) t).getTargetBatteryEnergySurplus());
        // initiate configurations
        configurations = new ArrayList<>();
        for (int i = 0; i < rulesList.size(); i++) configurations.add(new ArrayList<>());
        for (int i = 0; i < rulesList.size(); i++) {
            List<Predicate<AbstractTransaction>> confIn = configurations.get(i);
            for (int j = 0; j <= i; j++){
                confIn.add(rulesList.get(j));
            }
        }
    }

    public static void initiateTransactions(){
        transactions.add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        transactions.add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,100));
        transactions.add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,200, 1,1));
        transactions.add(new Transaction(0.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        transactions.add(new Transaction(500.0,0.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        transactions.add(new Transaction(500.0,50.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        transactions.add(new Transaction(500.0,1000.0,0.0,0.0, 10.0, 200,7000, 1,1));
        transactions.add(new Transaction(500.0,1000.0,50.0,0.0, 10.0, 200,7000, 1,1));
        transactions.add(new Transaction(500.0,1000.0,2500.0,0.0, 7500.0, 200,7000, 1,1));
    }


    public void checkConfigurations(){
        for (int i = 0; i < rulesList.size(); i++){
            System.out.println(" Rozmiar konfiguracji " + i + " wynosi: " + configurations.get(i).size());
        }
    }


    public static void initiateTransactionValues(){
        //public Transaction(double quantity, double sSurplus, double tNeed, double targetProduction, double targetBatteryEnergySurplus, int sID, int tID, int sCID, int tCID) {
        //
        trValues = new ArrayList[configurations.size()];
        for(int i = 0; i < trValues.length; i++){
            trValues[i] = new ArrayList<Transaction>();
        }
        // 1
        trValues[0].add(new Transaction(0.0,0.0,0.0,0.0, 0.0, 0,0, 1,100));
        trValues[0].add(new Transaction(0.0,0.0,0.0,0.0, 0.0, 0,0, 1,1));
        // 2
        trValues[1].add(new Transaction(0.0,0.0,0.0,0.0, 0.0, 200,7000, 1,100));
        trValues[1].add(new Transaction(0.0,0.0,0.0,0.0, 0.0, 200,200, 1,1));
        trValues[1].add(new Transaction(0.0,0.0,0.0,0.0, 0.0, 200,7000, 1,1));
        // 3
        trValues[2].add(new Transaction(500.0,0.0,0.0,0.0, 0.0, 200,7000, 1,100));
        trValues[2].add(new Transaction(500.0,0.0,0.0,0.0, 0.0, 200,200, 1,1));
        trValues[2].add(new Transaction(0.0,0.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[2].add(new Transaction(500.0,0.0,0.0,0.0, 0.0, 200,7000, 1,1));
        // 4
        trValues[3].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,100));
        trValues[3].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,200, 1,1));
        trValues[3].add(new Transaction(0.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[3].add(new Transaction(500.0,0.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[3].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,1));
        // 5
        trValues[4].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,100));
        trValues[4].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,200, 1,1));
        trValues[4].add(new Transaction(0.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[4].add(new Transaction(500.0,0.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[4].add(new Transaction(500.0,50.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[4].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,1));
        // 6
        trValues[5].add(new Transaction(500.0,1000.0,2500.0,0.0, 0.0, 200,7000, 1,100));
        trValues[5].add(new Transaction(500.0,1000.0,2500.0,0.0, 0.0, 200,200, 1,1));
        trValues[5].add(new Transaction(0.0,1000.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        trValues[5].add(new Transaction(500.0,0.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        trValues[5].add(new Transaction(500.0,50.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        trValues[5].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[5].add(new Transaction(500.0,1000.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        // 7
        trValues[6].add(new Transaction(500.0,1000.0,2500.0,0.0, 0.0, 200,7000, 1,100));
        trValues[6].add(new Transaction(500.0,1000.0,2500.0,0.0, 0.0, 200,200, 1,1));
        trValues[6].add(new Transaction(0.0,1000.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        trValues[6].add(new Transaction(500.0,0.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        trValues[6].add(new Transaction(500.0,50.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        trValues[6].add(new Transaction(500.0,1000.0,0.0,0.0, 0.0, 200,7000, 1,1));
        trValues[6].add(new Transaction(500.0,1000.0,50.0,0.0, 0.0, 200,7000, 1,1));
        trValues[6].add(new Transaction(500.0,1000.0,2500.0,0.0, 0.0, 200,7000, 1,1));
        // 8
        trValues[7].add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,100));
        trValues[7].add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,200, 1,1));
        trValues[7].add(new Transaction(0.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        trValues[7].add(new Transaction(500.0,0.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        trValues[7].add(new Transaction(500.0,50.0,2500.0,0.0, 10.0, 200,7000, 1,1));
        trValues[7].add(new Transaction(500.0,1000.0,0.0,0.0, 10.0, 200,7000, 1,1));
        trValues[7].add(new Transaction(500.0,1000.0,50.0,0.0, 10.0, 200,7000, 1,1));
        trValues[7].add(new Transaction(500.0,1000.0,2500.0,0.0, 7500.0, 200,7000, 1,1));
        trValues[7].add(new Transaction(500.0,1000.0,2500.0,0.0, 10.0, 200,7000, 1,1));
    }
    public void generateReduct(int ruleNumber){
        for(int i = 0; i <= ruleNumber; i++){
            System.out.print("Test case "+ i + " : ");
            for(int j = 0; j<=ruleNumber; j++){
                if (i == j) { System.out.print("0 "); }
                else { System.out.print("1 "); }
            }
            System.out.println();
        }
        System.out.print("Test case "+ (ruleNumber + 1) + " : ");
        for(int j = 0; j<=ruleNumber; j++) System.out.print("1 ");
        System.out.println();
    }
    void generateFullCoverage(){

    }
    boolean runTests(ConcreteContract sC, int confNumber){

        return true;
    }
}
