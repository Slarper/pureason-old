package slarper.pureason.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import slarper.pureason.item.util.UseActionHelper;


// Non-target spell
public class PullableSpellItem extends SpellItem {
    public static final String USE_ACTION_KEY = "UseAction";
    public static final String MAX_PULL_TIME = "MaxPullTime";

    public PullableSpellItem(Settings settings) {
        super(settings);
    }


    // body movement like drawing a bow (won't zoom in on the camera
    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (!stack.hasNbt()){
            return UseAction.NONE;
        }

        NbtCompound nbt = stack.getNbt();
        if(nbt==null || !nbt.contains(USE_ACTION_KEY)){
            return UseAction.NONE;
        }

        String action = nbt.getString(USE_ACTION_KEY);
        if (action.equals("")){
            nbt.remove(USE_ACTION_KEY);
            return UseAction.NONE;
        }

        UseAction useAction = UseActionHelper.getUseAction(action);
        if (useAction == null){
            nbt.remove(USE_ACTION_KEY);
            return UseAction.NONE;
        }
        return useAction;
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
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (! (user instanceof PlayerEntity)){
            return;
        }
        PlayerEntity player = (PlayerEntity)user;

        // fire event.
    }
}
