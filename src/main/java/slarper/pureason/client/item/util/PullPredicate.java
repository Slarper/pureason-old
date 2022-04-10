package slarper.pureason.client.item.util;

import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;
import slarper.pureason.Pureason;
import slarper.pureason.item.PullableSpellItem;

public class PullPredicate implements UnclampedModelPredicateProvider {

    // a second = 20 ticks
    private static final float TICKS = 20.0F;

    public static final PullPredicate PULL_PREDICATE = new PullPredicate();

    @Override
    public float unclampedCall(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed) {
        if (entity == null || entity.getActiveItem()!=stack) {
            return 0.0F;
        }
        int currenTime = (stack.getMaxUseTime() - entity.getItemUseTimeLeft());
        int maxTime;

        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(PullableSpellItem.MAX_PULL_TIME)){
            maxTime = 1;
            return currenTime/(maxTime * TICKS);
        }
        maxTime = nbt.getInt(PullableSpellItem.MAX_PULL_TIME);

        if (maxTime<=0){
            nbt.remove(PullableSpellItem.MAX_PULL_TIME);
            Pureason.LOGGER.info("Invalid maxTime");
            maxTime = 1;
            return currenTime/(maxTime * TICKS);
        }

        return currenTime/(maxTime * TICKS);
    }
}
