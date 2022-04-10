package slarper.pureason.spell;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import slarper.pureason.Pureason;
import slarper.pureason.spell.event.CastOnBlockCallback;
import slarper.pureason.spell.event.CastOnEntityCallback;

public class FireTouch implements CastOnEntityCallback, CastOnBlockCallback {
    private static final FireTouch SPELL = new FireTouch();
    private static final String KEY = "FireTouch";

    public static void load(){
        CastOnEntityCallback.EVENT.register(SPELL);
        CastOnBlockCallback.EVENT.register(SPELL);
    }


    @Override
    public ActionResult onEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, @NotNull NbtCompound nbt) {

        int lasting = nbt.getInt(KEY);

        // excluding bad value
        if (lasting<=0){
            nbt.remove(KEY);
            Pureason.LOGGER.info("Invalid spell removed : " + KEY);
            return ActionResult.PASS;
        }

        entity.setFireTicks(20 * lasting);

        return ActionResult.PASS;
    }

    @Override
    public ActionResult onBlock(ItemUsageContext context, NbtCompound nbt) {

        // copy from FlintAndSteelItem
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState)) {
            BlockPos blockPos2 = blockPos.offset(context.getSide());
            if (AbstractFireBlock.canPlaceAt(world, blockPos2, context.getPlayerFacing())) {
                BlockState blockState2 = AbstractFireBlock.getState(world, blockPos2);
                world.setBlockState(blockPos2, blockState2, 11);
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public String getKey() {
        return KEY;
    }
}
