package slarper.pureason.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public interface SpellUseOnEntityCallback {
    Event<SpellUseOnEntityCallback> EVENT = EventFactory.createArrayBacked(SpellUseOnEntityCallback.class,
            (listeners) -> ( stack,  user,  entity,  hand) -> {
                for (SpellUseOnEntityCallback event : listeners) {
                    ActionResult result = event.useOnEntity(stack,  user,  entity,  hand);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            }
    );
    ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand);

}
