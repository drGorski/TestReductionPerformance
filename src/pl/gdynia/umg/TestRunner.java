package pl.gdynia.umg;

public class TestRunner {
    private static AbstractSmartContract[] smartContracts = new AbstractSmartContract[8];
    private static void createSmartContracts(){
        smartContracts[0] = new SmartContractOneRule();
        smartContracts[1] = new SmartContractTwoRules();
        smartContracts[2] = new SmartContractThreeRules();
        smartContracts[3] = new SmartContractFourRules();
        smartContracts[4] = new SmartContractFiveRules();
        smartContracts[5] = new SmartContractSixRules();
        smartContracts[6] = new SmartContractSevenRules();
        smartContracts[7] = new SmartContractEightRules();
    }

    private static boolean runTest(int sC, int tR){
        return smartContracts[sC].checkSC(TestManager.getTransaction(tR));
    }

    private static long runTest(int sC, int repetitions, int tR){
        long sum = 0;
        long startTime, endTime;
        // smart contract evaluation time
        for (int j = 1; j <= repetitions; j++){
            startTime = System.nanoTime();
            smartContracts[sC].checkSC(TestManager.getTransaction(tR));
            endTime = System.nanoTime();
            sum = sum + (endTime - startTime);
        }
        return sum/repetitions;
    }

    private static void runSmartContractEvaluation(int repetitions){
        long executionTime = 0;
        // smart contract evaluation time
        System.out.println("Smart contract evaluation time");
        for (int i = 0; i < smartContracts.length; i++) {
            executionTime = runTest(i,repetitions,0);
            System.out.println("Contract: " + (i+1) + ", execution time: " + executionTime);
        }
    }

    private static long runTestSuite(int sC){
        long startTime = 0, endTime = 0;
        boolean correct = false;
        long executionTime = 0;
        // test suite execution time
        System.out.println("Test suite execution time for SC: " + sC);
        startTime = System.nanoTime();
        for (int j = 0; j < sC + 2; j++) {
            correct = runTest(sC, j);
        }
        endTime = System.nanoTime();
        executionTime = endTime - startTime;
        return executionTime;
    }

    public static void main(String[] args) {
        boolean correct = false;
        long executionTime = 0;
        int repetitions = 50;
        createSmartContracts();
        TestManager.initiateTransactionValues();
        //checkSuites();
        runTest(0,repetitions,0);

        runSmartContractEvaluation(repetitions);
        System.out.println("Repetitions: " +repetitions);

        System.out.println(runTestSuite(0));
        for(int i = 0; i < smartContracts.length; i++) System.out.println(runTestSuite(i));

    }
}
