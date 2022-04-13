package slarper.pureason.spell;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import slarper.pureason.event.SpellUsableCallback;
import slarper.pureason.event.SpellUseOnBlockCallback;
import slarper.pureason.event.SpellUseOnEntityCallback;

public class Spell implements SpellUseOnEntityCallback, SpellUseOnBlockCallback, SpellUsableCallback {
    private final String key;
    public Spell(String key){
        this.key = key;
    }

    public static Spell register(Spell spell){
        SpellUsableCallback.EVENT.register(spell);
        SpellUseOnEntityCallback.EVENT.register(spell);
        SpellUseOnBlockCallback.EVENT.register(spell);
        return spell;
    }

    public String getKey(){
        return key;
    }

    public void putKey(ItemStack stack){}

    public boolean hasKey(ItemStack stack){
        if (stack.hasNbt()){
            assert stack.getNbt() != null;
            return stack.getNbt().contains(key);
        } else {
            return false;
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return ActionResult.PASS;
    }

    @Override
    public boolean isUsable(ItemStack stack, PlayerEntity user) {
        return true;
    }
}
