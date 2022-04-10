package slarper.pureason.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import slarper.pureason.spell.event.CastOnBlockCallback;
import slarper.pureason.spell.event.CastOnEntityCallback;

// Cantrips
public class TargetSpellItem extends SpellItem{

    public TargetSpellItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() instanceof ClientPlayerEntity){
            context.getPlayer().swingHand(context.getHand());
        }

        NbtCompound nbt = context.getStack().getOrCreateSubNbt(SpellItem.MAGIC_KEY);
        if (nbt==null || nbt.isEmpty()){
            return super.useOnBlock(context);
        }

        return CastOnBlockCallback.EVENT.invoker().onBlock(context,nbt);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user instanceof ClientPlayerEntity){
            user.swingHand(hand);
        }

        NbtCompound nbt = stack.getOrCreateSubNbt(SpellItem.MAGIC_KEY);
        if (nbt.isEmpty()){
            return super.useOnEntity(stack, user, entity, hand);
        }

        return CastOnEntityCallback.EVENT.invoker().onEntity(stack,user,entity,hand,nbt);
    }
}