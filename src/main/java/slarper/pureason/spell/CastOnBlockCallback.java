package slarper.pureason.spell;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;

public interface CastOnBlockCallback extends Element {
    Event<CastOnBlockCallback> EVENT = EventFactory.createArrayBacked(
            CastOnBlockCallback.class,
            (listeners) -> (context,nbt) ->{

                for (CastOnBlockCallback listener : listeners){

                    // only invoke listener when contains corresponding key.
                    if (nbt.contains(listener.getKey())){
                        ActionResult result = listener.onBlock(context,nbt);
                        // immediately return if the result != PASS.
                        if(result!=ActionResult.PASS){
                            return result;
                        }
                    }
                }

                return ActionResult.PASS;
            }
    );

    ActionResult onBlock(ItemUsageContext context, NbtCompound nbt);
}