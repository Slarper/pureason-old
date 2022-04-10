package slarper.pureason.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

// Cantrips
public class NonTargetSpellItem extends SpellItem{
    public NonTargetSpellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ClientPlayerEntity){
            user.swingHand(hand);
        }

        //fire event
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
