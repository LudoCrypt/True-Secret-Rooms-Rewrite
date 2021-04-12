package net.ludocrypt.truerooms.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.ludocrypt.truerooms.client.resource.TSRResourceProvider;

public class TrueSecretRoomsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new TSRResourceProvider());
	}

}
