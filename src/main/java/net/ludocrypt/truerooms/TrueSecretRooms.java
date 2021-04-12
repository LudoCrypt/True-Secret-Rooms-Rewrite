package net.ludocrypt.truerooms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.ludocrypt.truerooms.init.TSRBlocks;
import net.minecraft.util.Identifier;

public class TrueSecretRooms implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("True Secret Rooms");

	@Override
	public void onInitialize() {
		TSRBlocks.init();
	}

	public static final Identifier id(String id) {
		return new Identifier("truerooms", id);
	}

}
