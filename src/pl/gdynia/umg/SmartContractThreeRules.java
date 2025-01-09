package pl.gdynia.umg;

import java.util.Arrays;
public final class SmartContractThreeRules extends AbstractSmartContract {
    public SmartContractThreeRules(){
        rulesList = Arrays.asList(
                t -> ((Transaction) t).getSourceCommunityID() == ((Transaction) t).getTargetCommunityID(),
                t -> ((Transaction) t).getSourceID() != ((Transaction) t).getTargetID(),
                t -> ((Transaction) t).getQuantity() > 0);
    }
}