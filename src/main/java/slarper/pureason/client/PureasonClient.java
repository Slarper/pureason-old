package slarper.pureason.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import slarper.pureason.Pureason;
import slarper.pureason.client.item.ModelPredicates;
import slarper.pureason.item.PureasonItems;

public class PureasonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicates.load();
    }
}
