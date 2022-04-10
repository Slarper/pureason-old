package slarper.pureason.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import slarper.pureason.Pureason;
import slarper.pureason.event.CastOnBlockCallback;
import slarper.pureason.event.CastOnEntityCallback;
import slarper.pureason.item.util.UseActionHelper;

// Basic spell type, no restriction, no condition.
// deprecated.
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
        if (nbt==null || !nbt.contains(SPELL_ID_KEY)){
            return super.getTranslationKey(stack);
        }

        // if spell id is not properly specified, we will get ""
        String name = nbt.getString(SPELL_ID_KEY);
        if (name.equals("")){
            nbt.remove(SPELL_ID_KEY);
            Pureason.LOGGER.info("Spell Id is invalid.");
            return super.getTranslationKey(stack);
        }
        return name;
    }

}

