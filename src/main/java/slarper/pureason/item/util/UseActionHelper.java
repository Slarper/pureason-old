package slarper.pureason.item.util;

import net.minecraft.util.UseAction;

import java.util.HashMap;

public class UseActionHelper {
    private static final HashMap<String, UseAction> map = new HashMap<>(){
        {
            put("none",UseAction.NONE);
            put("eat",UseAction.EAT);
            put("drink",UseAction.DRINK);
            put("block",UseAction.BLOCK);
            put("bow",UseAction.BOW);
            put("spear",UseAction.SPEAR);
            put("crossbow",UseAction.CROSSBOW);
            put("spyglass",UseAction.SPYGLASS);

        }
    };

    public static UseAction getUseAction(String key){
        // return null when missing key.
        return map.get(key);
    }
}
