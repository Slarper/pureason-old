package slarper.pureason.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public interface SpellUseOnBlockCallback {
    Event<SpellUseOnBlockCallback> EVENT = EventFactory.createArrayBacked(SpellUseOnBlockCallback.class,
            (listeners) -> (context) -> {
                for (SpellUseOnBlockCallback event : listeners) {
                    ActionResult result = event.useOnBlock(context);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            }
    );

    ActionResult useOnBlock(ItemUsageContext context);
}
