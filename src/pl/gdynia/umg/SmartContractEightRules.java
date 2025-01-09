package pl.gdynia.umg;

import java.util.Arrays;
public final class SmartContractEightRules extends AbstractSmartContract {
    public SmartContractEightRules(){
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
}
