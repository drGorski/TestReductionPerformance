package pl.gdynia.umg;

import java.util.Arrays;
public final class SmartContractOneRule extends AbstractSmartContract {
    public SmartContractOneRule(){
        rulesList = Arrays.asList(
                t -> ((Transaction) t).getSourceCommunityID() == ((Transaction) t).getTargetCommunityID());
    }
}