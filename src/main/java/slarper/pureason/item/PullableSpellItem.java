package slarper.pureason.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class PullableSpellItem extends Item {
    public PullableSpellItem(Settings settings) {
        super(settings);
    }

    // body movement like drawing a bow (won't zoom in on the camera
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }


    // you can draw a bow for up to 72000 ticks
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        // must call this method here to allow pulling.
        // because it will set entity.itemUseTimeLeft to stack.getMaxUseTime()
        user.setCurrentHand(hand);

        return super.use(world, user, hand);
    }
}
