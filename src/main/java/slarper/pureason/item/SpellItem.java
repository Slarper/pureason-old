package slarper.pureason.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import slarper.pureason.Pureason;
import slarper.pureason.event.CastOnBlockCallback;
import slarper.pureason.event.CastOnEntityCallback;

// Basic spell type, no restriction, no condition.
// deprecated.
public class SpellItem extends Item {
    private static final String SPELL_ID_KEY = "SpellId";

    public SpellItem(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if (!stack.hasNbt()){
            return super.getTranslationKey(stack);
        }
        NbtCompound nbt = stack.getNbt();
        if (nbt!=null && nbt.contains(SPELL_ID_KEY)){
            // if spell id is not properly specified, we will get ""
            String name = nbt.getString(SPELL_ID_KEY);
            if (name.equals("")){
                nbt.remove(SPELL_ID_KEY);
                Pureason.LOGGER.info("Spell Id is invalid.");
            } else {
                return name;
            }
        }
        return super.getTranslationKey(stack);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() instanceof ClientPlayerEntity){
            context.getPlayer().swingHand(context.getHand());
        }
        NbtCompound nbt = context.getStack().getOrCreateSubNbt("magic");
        if (nbt!=null && !nbt.isEmpty()){
            ActionResult result = CastOnBlockCallback.EVENT.invoker().onBlock(context,nbt);
            if (result!=ActionResult.PASS){
                return result;
            }
        }

        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user instanceof ClientPlayerEntity){
            user.swingHand(hand);
        }

        NbtCompound nbt = stack.getOrCreateSubNbt("magic");
        if (nbt!=null && !nbt.isEmpty()){
            ActionResult result = CastOnEntityCallback.EVENT.invoker().onEntity(stack,user,entity,hand,nbt);
            if (result!=ActionResult.PASS){
                return result;
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}

