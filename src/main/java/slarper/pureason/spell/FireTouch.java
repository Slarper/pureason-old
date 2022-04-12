package slarper.pureason.spell;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import slarper.pureason.event.SpellUseOnBlockCallback;
import slarper.pureason.event.SpellUseOnEntityCallback;

public class FireTouch implements SpellUseOnEntityCallback, SpellUseOnBlockCallback {
    public static final int TICKS_PER_SECOND = 20;
    public static final String  MAGIC_KEY = "magic";
    public static final String SPELL_KEY = "FireTouch";
    public static final int DEFAULT_LAST_TIME = 1;

    public FireTouch(){}

    public static String getSpellKey(){
        return SPELL_KEY;
    }

    public static void addStackKey(ItemStack stack){
        addStackKey(stack, DEFAULT_LAST_TIME);
    }

    public static void addStackKey(ItemStack stack, int time){
        NbtCompound magic =  stack.getOrCreateSubNbt(MAGIC_KEY);
        if (time > 0){
            magic.putInt(SPELL_KEY,time);
        } else {
            magic.putInt(SPELL_KEY,DEFAULT_LAST_TIME);
        }
    }

    public static int getStackKey(ItemStack stack){
        NbtCompound magic = stack.getSubNbt(MAGIC_KEY);
        if (magic == null){
            return 0;
        }
        return magic.getInt(SPELL_KEY);

    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        int last_time = getStackKey(stack);
        if (last_time<=0){
            return ActionResult.PASS;
        }
        entity.setFireTicks(last_time * TICKS_PER_SECOND);
        return ActionResult.PASS;
    }
}
