package pl.gdynia.umg;

import java.util.List;
import java.util.function.Predicate;
public abstract class AbstractSmartContract {
    // list of verification rules
    protected List<Predicate<AbstractTransaction>> rulesList;
    // checking the smart contract
    public boolean checkSC(AbstractTransaction tr){
        boolean correct = false;
        for (Predicate<AbstractTransaction> vR : rulesList) {
            correct = vR.test(tr);
            if (!correct) break;
        }
        return correct;
    }
}
