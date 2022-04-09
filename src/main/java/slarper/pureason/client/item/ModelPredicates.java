package slarper.pureason.client.item;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import slarper.pureason.item.PureasonItems;

public class ModelPredicates {
    public static void load(){}
    static {
        ModelPredicateProviderRegistry.register(PureasonItems.PULLABLE_SPELL, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.getActiveItem() != stack ? 0.0F : (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
        });


        ModelPredicateProviderRegistry.register(PureasonItems.PULLABLE_SPELL, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
    }
}
