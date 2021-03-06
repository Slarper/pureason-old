package slarper.pureason.client.item;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import slarper.pureason.item.PureasonItems;

public class ModelPredicates {
    public static void load(){}
    static {
        // register "pull" for pullable_spell
        ModelPredicateProviderRegistry.register(
                PureasonItems.PULLABLE_SPELL,
                new Identifier("pull"),
                PullPredicate.PULL_PREDICATE);

        // register "pulling" for pullable_spell
        ModelPredicateProviderRegistry.register(
                PureasonItems.PULLABLE_SPELL,
                new Identifier("pulling"),
                PullingPredicate.PULLING_PREDICATE);
    }
}
