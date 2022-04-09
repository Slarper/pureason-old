package slarper.pureason.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;

public interface CastOnEntityCallback {
    Event<CastOnEntityCallback> EVENT = EventFactory.createArrayBacked(
            CastOnEntityCallback.class,
            (listeners) -> (stack,user,entity,hand,nbt) ->{

                for (CastOnEntityCallback listener : listeners){
                    ActionResult result = listener.onEntity(stack,user,entity,hand,nbt);

                    if(result!=ActionResult.PASS){
                        return result;
                    }
                }

                return ActionResult.PASS;
            }
    );

    ActionResult onEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, @NotNull NbtCompound nbt);
}
