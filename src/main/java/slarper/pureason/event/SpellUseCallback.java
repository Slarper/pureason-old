package slarper.pureason.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface SpellUseCallback {
    Event<SpellUseCallback> EVENT = EventFactory.createArrayBacked(SpellUseCallback.class,
            listeners -> (world, user, hand) -> {
                for (SpellUseCallback event : listeners) {
                    TypedActionResult<ItemStack> result = event.use(world, user, hand);

                    if (result.getResult() != ActionResult.PASS) {
                        return result;
                    }
                }

                return TypedActionResult.pass(user.getStackInHand(hand));
            }
    );
    TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);
}
