package slarper.pureason.event;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.NotNull;
import slarper.pureason.Pureason;

public class FireTouch implements CastOnEntityCallback,CastOnBlockCallback {
    private static final FireTouch SPELL = new FireTouch();
    private static final String KEY = "FireTouch";

    public static void load(){
        CastOnEntityCallback.EVENT.register(SPELL);
        CastOnBlockCallback.EVENT.register(SPELL);
    }


    @Override
    public ActionResult onEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, @NotNull NbtCompound nbt) {

        // first : is nbt contains key.
        if (!nbt.contains(KEY)){
            return ActionResult.PASS;
        }
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

        if (!nbt.contains(KEY)){
            return ActionResult.PASS;
        }

        // copy from FlintAndSteelItem
        PlayerEntity playerEntity = context.getPlayer();
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
}
