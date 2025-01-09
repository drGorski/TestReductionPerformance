package pl.gdynia.umg;

import java.util.Arrays;
public final class SmartContractTwoRules extends AbstractSmartContract {
    public SmartContractTwoRules(){
        rulesList = Arrays.asList(
                t -> ((Transaction) t).getSourceCommunityID() == ((Transaction) t).getTargetCommunityID(),
                t -> ((Transaction) t).getSourceID() != ((Transaction) t).getTargetID());
    }
}