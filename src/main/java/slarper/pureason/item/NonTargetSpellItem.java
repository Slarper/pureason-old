package slarper.pureason.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import slarper.pureason.spell.NonTargetCallback;

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

        ItemStack stack = user.getStackInHand(hand);
        NbtCompound nbt = stack.getOrCreateSubNbt(SpellItem.MAGIC_KEY);
        if (nbt==null || nbt.isEmpty()){
            return TypedActionResult.pass(stack);
        }

        return new TypedActionResult<>(
                NonTargetCallback.EVENT.invoker().cast(world,user,hand,nbt),
                stack
                ) ;
    }
}
