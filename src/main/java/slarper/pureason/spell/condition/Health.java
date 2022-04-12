package slarper.pureason.spell.condition;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import slarper.pureason.spell.CastOnBlockCallback;
import slarper.pureason.spell.CastOnEntityCallback;
import slarper.pureason.spell.NonTargetCallback;

public class Health implements CastOnEntityCallback, CastOnBlockCallback, NonTargetCallback {

    private ActionResult canUse(PlayerEntity entity){
        if (entity.getHealth() > 6){
            return ActionResult.PASS;
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    public ActionResult onBlock(ItemUsageContext context, NbtCompound nbt) {
        return context.getPlayer() == null? canUse(context.getPlayer()) : ActionResult.FAIL;
    }

    @Override
    public ActionResult onEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, @NotNull NbtCompound nbt) {
        return canUse(user);
    }

    @Override
    public String getKey() {
        return "health";
    }

    @Override
    public ActionResult cast(World world, PlayerEntity user, Hand hand, NbtCompound nbt) {
        return canUse(user);
    }
}
