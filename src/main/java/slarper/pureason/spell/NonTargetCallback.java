package slarper.pureason.spell;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public interface NonTargetCallback extends Element {
    Event<NonTargetCallback> EVENT = EventFactory.createArrayBacked(
            NonTargetCallback.class,
            (listeners) -> (world,user,hand,nbt) ->{

                for (NonTargetCallback listener : listeners){
                    if (nbt.contains(listener.getKey())){
                        ActionResult result = listener.cast(world,user,hand,nbt);
                        // immediately return if the result != PASS.
                        if(result!=ActionResult.PASS){
                            return result;
                        }
                    }
                }

                return ActionResult.PASS;
            }
    );

    ActionResult cast(World world, PlayerEntity user, Hand hand, NbtCompound nbt);
}
