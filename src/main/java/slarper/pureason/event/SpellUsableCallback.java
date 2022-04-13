package slarper.pureason.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface SpellUsableCallback {

    Event<SpellUsableCallback> EVENT = EventFactory.createArrayBacked(SpellUsableCallback.class,
            (listeners) -> (stack, user) -> {
                for (SpellUsableCallback event : listeners) {
                    boolean result = event.isUsable(stack, user);

                    if (!result) {
                        return false;
                    }
                }

                return true;
            }
    );

    boolean isUsable(ItemStack stack, PlayerEntity user);
}
