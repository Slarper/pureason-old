package slarper.pureason.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import slarper.pureason.spell.event.NonTargetCallback;

public class Cure implements NonTargetCallback{
    private static final Cure SPELL = new Cure();
    private static final String KEY = "Cure";

    public static void load(){
        NonTargetCallback.EVENT.register(SPELL);
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public ActionResult cast(World world, PlayerEntity user, Hand hand, NbtCompound nbt) {

        float cureHealth = nbt.getFloat(KEY);
        if (cureHealth<=0.0F){
            nbt.remove(KEY);
            return ActionResult.PASS;
        }

        user.setHealth(Float.min(user.getMaxHealth(),user.getHealth() + cureHealth));

        return ActionResult.PASS;
    }
}
