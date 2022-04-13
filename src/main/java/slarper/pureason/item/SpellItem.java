package slarper.pureason.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import slarper.pureason.Pureason;
import slarper.pureason.event.SpellUsableCallback;
import slarper.pureason.event.SpellUseCallback;
import slarper.pureason.event.SpellUseOnBlockCallback;
import slarper.pureason.event.SpellUseOnEntityCallback;

// Basic spell type, no restriction, no condition.
// deprecated.
public class SpellItem extends Item {
    private static final String SPELL_ID_KEY = "SpellId";
    public static final String MAGIC_KEY = "magic";

    public SpellItem(Settings settings) {
        super(settings);
    }

    public boolean isUsable(ItemStack stack, PlayerEntity user){
        return SpellUsableCallback.EVENT.invoker().isUsable(stack, user);
    }

    public void setStackSpellId(String spell_id, ItemStack stack){
        if (!(spell_id == null)){
            stack.getOrCreateNbt().putString(SPELL_ID_KEY,spell_id);
        }
    }


    @Override
    public String getTranslationKey(ItemStack stack) {
        if (!stack.hasNbt()){
            return super.getTranslationKey(stack);
        }
        NbtCompound nbt = stack.getNbt();
        if (nbt==null || !nbt.contains(SPELL_ID_KEY)){
            return super.getTranslationKey(stack);
        }

        // if spell id is not properly specified, we will get ""
        String name = nbt.getString(SPELL_ID_KEY);
        if (name.equals("")){
            nbt.remove(SPELL_ID_KEY);
            Pureason.LOGGER.info("Spell Id is invalid.");
            return super.getTranslationKey(stack);
        }
        return name;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (isUsable(context.getStack(),context.getPlayer())){
            return SpellUseOnBlockCallback.EVENT.invoker().useOnBlock(context);
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (isUsable(user.getStackInHand(hand),user)){
            return SpellUseCallback.EVENT.invoker().use(world,user,hand);
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (isUsable(stack,user)){
            return SpellUseOnEntityCallback.EVENT.invoker().useOnEntity(stack, user, entity, hand);
        } else {
            return ActionResult.PASS;
        }
    }
}

