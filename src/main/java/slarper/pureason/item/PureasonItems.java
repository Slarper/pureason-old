package slarper.pureason.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slarper.pureason.Pureason;

public class PureasonItems {
    public static void load(){}
    public static final Item Spell;

    private static Item register(String id, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Pureason.MODID, id), item);
    }

    static {
        Spell = register("spell", new SpellItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
    }
}
