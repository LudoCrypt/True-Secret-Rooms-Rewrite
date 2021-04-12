package net.ludocrypt.truerooms.client.resource;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.ludocrypt.truerooms.TrueSecretRooms;
import net.ludocrypt.truerooms.client.model.CamouflageBlockModel;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;

public class TSRResourceProvider implements ModelResourceProvider {

	public static final Identifier CAMOUFLAGE_BLOCK_ID = TrueSecretRooms.id("block/camouflage_block");

	@Override
	public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) throws ModelProviderException {
		if (identifier.equals(CAMOUFLAGE_BLOCK_ID)) {
			return new CamouflageBlockModel();
		} else {
			return null;
		}
	}
}
