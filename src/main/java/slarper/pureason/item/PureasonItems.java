package slarper.pureason.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slarper.pureason.Pureason;

public class PureasonItems {
    public static void load(){}
    public static final Item SPELL;
    public static final Item BASE_SPELL;
    public static final Item PULLABLE_SPELL;

    private static Item register(String id, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Pureason.MODID, id), item);
    }

    static {
        SPELL = register("spell", new SpellItem(new FabricItemSettings().maxCount(1)));
        PULLABLE_SPELL = register("pullable_spell",new PullableSpellItem(new FabricItemSettings().maxCount(1)));
        BASE_SPELL = register("base_spell",new TargetSpellItem(new FabricItemSettings().maxCount(1)));
    }
}
