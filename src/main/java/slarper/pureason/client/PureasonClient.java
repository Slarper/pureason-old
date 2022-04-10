package slarper.pureason.client;

import net.fabricmc.api.ClientModInitializer;
import slarper.pureason.client.item.ModelPredicates;

public class PureasonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicates.load();
    }
}
