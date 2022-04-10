package slarper.pureason.spell.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;

public interface CastOnBlockCallback {
    Event<CastOnBlockCallback> EVENT = EventFactory.createArrayBacked(
            CastOnBlockCallback.class,
            (listeners) -> (context,nbt) ->{

                for (CastOnBlockCallback listener : listeners){
                    ActionResult result = listener.onBlock(context,nbt);
                    // immediately return if the result != PASS.
                    if(result!=ActionResult.PASS){
                        return result;
                    }
                }

                return ActionResult.PASS;
            }
    );

    ActionResult onBlock(ItemUsageContext context, NbtCompound nbt);
}
