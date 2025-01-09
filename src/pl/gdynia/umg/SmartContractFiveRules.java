package pl.gdynia.umg;

import java.util.Arrays;
public final class SmartContractFiveRules extends AbstractSmartContract {
    public SmartContractFiveRules(){
        rulesList = Arrays.asList(
                t -> ((Transaction) t).getSourceCommunityID() == ((Transaction) t).getTargetCommunityID(),
                t -> ((Transaction) t).getSourceID() != ((Transaction) t).getTargetID(),
                t -> ((Transaction) t).getQuantity() > 0,
                t -> ((Transaction) t).getSourceSurplus() > 0,
                t -> ((Transaction) t).getSourceSurplus() >= ((Transaction) t).getQuantity());
    }
}
