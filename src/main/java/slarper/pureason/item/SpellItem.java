package slarper.pureason.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import slarper.pureason.Pureason;

public class SpellItem extends Item {
    private static final String SPELL_ID_KEY = "SpellId";

    public SpellItem(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if (!stack.hasNbt()){
            return super.getTranslationKey(stack);
        }
        NbtCompound nbt = stack.getNbt();
        if (nbt!=null && nbt.contains(SPELL_ID_KEY)){
            // if spell id is not properly specified, we will get ""
            String name = nbt.getString(SPELL_ID_KEY);
            if (name.equals("")){
                nbt.remove(SPELL_ID_KEY);
                Pureason.LOGGER.info("Spell Id is invalid.");
            } else {
                return name;
            }
        }
        return super.getTranslationKey(stack);
    }
}
